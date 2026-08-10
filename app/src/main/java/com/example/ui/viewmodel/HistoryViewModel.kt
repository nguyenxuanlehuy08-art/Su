package com.example.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.HistoryDatabase
import com.example.data.model.HistoryEvent
import com.example.data.model.QuizState
import com.example.data.remote.GeminiClient
import com.example.data.repository.HistoryRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AiChatMessage(
    val sender: String, // "user" or "ai"
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HistoryRepository

    val searchQuery = MutableStateFlow("")
    val selectedEra = MutableStateFlow<String?>(null)

    val featuredEvents: StateFlow<List<HistoryEvent>>
    val todayInHistory: StateFlow<List<HistoryEvent>>
    val bookmarkedEvents: StateFlow<List<HistoryEvent>>
    val filteredEvents: StateFlow<List<HistoryEvent>>

    val quizQuestions = MutableStateFlow<List<HistoryEvent>>(emptyList())
    val quizState = MutableStateFlow(QuizState())

    val aiChatMessages = MutableStateFlow<List<AiChatMessage>>(
        listOf(
            AiChatMessage(
                sender = "ai",
                text = "Xin chào! Tôi là Trợ Lý Lịch Sử AI. Bạn muốn tìm hiểu về triều đại, nhân vật hay sự kiện lịch sử nào hôm nay?"
            )
        )
    )
    val isAiThinking = MutableStateFlow(false)

    init {
        val database = HistoryDatabase.getDatabase(application)
        repository = HistoryRepository(database.historyDao())

        featuredEvents = repository.featuredEvents.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        todayInHistory = repository.getEventsForToday().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        bookmarkedEvents = repository.bookmarkedEvents.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        @OptIn(FlowPreview::class)
        filteredEvents = combine(
            repository.allEvents,
            searchQuery.debounce(200),
            selectedEra
        ) { events, query, era ->
            events.filter { event ->
                val matchesQuery = if (query.isBlank()) true else {
                    event.title.contains(query, ignoreCase = true) ||
                    event.summary.contains(query, ignoreCase = true) ||
                    event.keyFigures.contains(query, ignoreCase = true) ||
                    event.yearOrPeriod.contains(query, ignoreCase = true)
                }
                val matchesEra = if (era == null) true else event.eraCode == era
                matchesQuery && matchesEra
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        // Load quiz questions
        viewModelScope.launch {
            repository.allEvents.collect { events ->
                val withQuiz = events.filter { it.quizQuestion != null }
                quizQuestions.value = withQuiz
                if (quizState.value.totalQuestions == 0 && withQuiz.isNotEmpty()) {
                    quizState.value = QuizState(totalQuestions = withQuiz.size)
                }
            }
        }
    }

    fun getEventFlowById(id: String): Flow<HistoryEvent?> = repository.getEventFlowById(id)

    fun isBookmarked(eventId: String): Flow<Boolean> = repository.isBookmarked(eventId)

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        if (query.isNotBlank()) {
            viewModelScope.launch {
                repository.saveSearchQuery(query)
            }
        }
    }

    fun setSelectedEra(eraCode: String?) {
        selectedEra.value = eraCode
    }

    fun toggleBookmark(eventId: String, note: String = "") {
        viewModelScope.launch {
            repository.toggleBookmark(eventId, note)
        }
    }

    // Quiz Functions
    fun selectQuizOption(optionIndex: Int) {
        if (!quizState.value.isAnswerSubmitted) {
            quizState.value = quizState.value.copy(selectedOptionIndex = optionIndex)
        }
    }

    fun submitQuizAnswer() {
        val currentQuestions = quizQuestions.value
        val currentIndex = quizState.value.currentQuestionIndex
        val selectedOption = quizState.value.selectedOptionIndex ?: return

        if (currentIndex < currentQuestions.size) {
            val currentEvent = currentQuestions[currentIndex]
            val isCorrect = selectedOption == currentEvent.quizCorrectAnswer
            val newScore = if (isCorrect) quizState.value.score + 1 else quizState.value.score

            quizState.value = quizState.value.copy(
                score = newScore,
                isAnswerSubmitted = true
            )
        }
    }

    fun nextQuizQuestion() {
        val currentQuestions = quizQuestions.value
        val nextIndex = quizState.value.currentQuestionIndex + 1

        if (nextIndex < currentQuestions.size) {
            quizState.value = quizState.value.copy(
                currentQuestionIndex = nextIndex,
                selectedOptionIndex = null,
                isAnswerSubmitted = false
            )
        } else {
            quizState.value = quizState.value.copy(
                isCompleted = true
            )
        }
    }

    fun restartQuiz() {
        quizState.value = QuizState(
            currentQuestionIndex = 0,
            score = 0,
            selectedOptionIndex = null,
            isAnswerSubmitted = false,
            isCompleted = false,
            totalQuestions = quizQuestions.value.size
        )
    }

    // Gemini AI Assistant
    fun sendAiQuestion(userPrompt: String) {
        if (userPrompt.isBlank()) return

        val userMessage = AiChatMessage(sender = "user", text = userPrompt.trim())
        aiChatMessages.value = aiChatMessages.value + userMessage
        isAiThinking.value = true

        viewModelScope.launch {
            val responseText = GeminiClient.answerHistoryQuestion(userPrompt)
            val aiMessage = AiChatMessage(sender = "ai", text = responseText)
            aiChatMessages.value = aiChatMessages.value + aiMessage
            isAiThinking.value = false
        }
    }
}
