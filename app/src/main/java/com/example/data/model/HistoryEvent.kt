package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class HistoryEra(val displayName: String, val code: String) {
    ANCIENT_VN("Việt Nam Cổ Đại", "ancient_vn"),
    FEUDAL_VN("Phong Kiến Việt Nam", "feudal_vn"),
    MODERN_VN("Việt Nam Hiện Đại", "modern_vn"),
    WORLD_HISTORY("Lịch Sử Thế Giới", "world"),
    FAMOUS_FIGURES("Nhân Vật Lịch Sử", "figures")
}

@Entity(tableName = "history_events")
data class HistoryEvent(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,
    val yearOrPeriod: String,
    val sortYear: Int, // for timeline sorting
    val eraCode: String,
    val dayOfMonth: Int, // 1-31, for "Hôm nay trong lịch sử"
    val month: Int,      // 1-12, for "Hôm nay trong lịch sử"
    val summary: String,
    val fullContent: String,
    val historicalSignificance: String,
    val keyFigures: String, // comma separated figures
    val location: String,
    val isFeatured: Boolean = false,
    val isBookmarked: Boolean = false,
    val quizQuestion: String? = null,
    val quizOptionA: String? = null,
    val quizOptionB: String? = null,
    val quizOptionC: String? = null,
    val quizOptionD: String? = null,
    val quizCorrectAnswer: Int? = null, // 0=A, 1=B, 2=C, 3=D
    val quizExplanation: String? = null
)

@Entity(tableName = "user_bookmarks")
data class UserBookmark(
    @PrimaryKey val eventId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val customNote: String = ""
)

@Entity(tableName = "search_history")
data class SearchQuery(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class QuizState(
    val currentQuestionIndex: Int = 0,
    val score: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isCompleted: Boolean = false,
    val totalQuestions: Int = 0
)
