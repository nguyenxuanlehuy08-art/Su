package com.example.data.repository

import com.example.data.local.HistoryDao
import com.example.data.model.HistoryEvent
import com.example.data.model.SearchQuery
import com.example.data.model.UserBookmark
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class HistoryRepository(private val dao: HistoryDao) {

    val allEvents: Flow<List<HistoryEvent>> = dao.getAllEvents()
    val featuredEvents: Flow<List<HistoryEvent>> = dao.getFeaturedEvents()
    val bookmarkedEvents: Flow<List<HistoryEvent>> = dao.getBookmarkedEvents()
    val recentSearches: Flow<List<SearchQuery>> = dao.getRecentSearches()

    fun getEventFlowById(id: String): Flow<HistoryEvent?> = dao.getEventFlowById(id)

    suspend fun getEventById(id: String): HistoryEvent? = dao.getEventById(id)

    fun getEventsByEra(eraCode: String): Flow<List<HistoryEvent>> = dao.getEventsByEra(eraCode)

    fun searchEvents(query: String): Flow<List<HistoryEvent>> = dao.searchEvents(query)

    fun isBookmarked(eventId: String): Flow<Boolean> = dao.isBookmarked(eventId)

    fun getEventsOnThisDay(day: Int, month: Int): Flow<List<HistoryEvent>> = dao.getEventsOnThisDay(day, month)

    fun getEventsForToday(): Flow<List<HistoryEvent>> {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar months are 0-based
        return dao.getEventsOnThisDay(day, month)
    }

    suspend fun toggleBookmark(eventId: String, customNote: String = "") {
        val isCurrentlyBookmarked = dao.getEventById(eventId)?.isBookmarked ?: false
        if (isCurrentlyBookmarked) {
            dao.removeBookmark(eventId)
            val event = dao.getEventById(eventId)
            if (event != null) {
                dao.updateEvent(event.copy(isBookmarked = false))
            }
        } else {
            dao.addBookmark(UserBookmark(eventId = eventId, customNote = customNote))
            val event = dao.getEventById(eventId)
            if (event != null) {
                dao.updateEvent(event.copy(isBookmarked = true))
            }
        }
    }

    suspend fun saveSearchQuery(query: String) {
        if (query.isNotBlank()) {
            dao.insertSearchQuery(SearchQuery(query = query.trim()))
        }
    }

    suspend fun clearSearchHistory() {
        dao.clearSearchHistory()
    }
}
