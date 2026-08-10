package com.example.data.local

import androidx.room.*
import com.example.data.model.HistoryEvent
import com.example.data.model.SearchQuery
import com.example.data.model.UserBookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_events ORDER BY sortYear ASC")
    fun getAllEvents(): Flow<List<HistoryEvent>>

    @Query("SELECT * FROM history_events WHERE id = :id")
    suspend fun getEventById(id: String): HistoryEvent?

    @Query("SELECT * FROM history_events WHERE id = :id")
    fun getEventFlowById(id: String): Flow<HistoryEvent?>

    @Query("SELECT * FROM history_events WHERE eraCode = :eraCode ORDER BY sortYear ASC")
    fun getEventsByEra(eraCode: String): Flow<List<HistoryEvent>>

    @Query("SELECT * FROM history_events WHERE isFeatured = 1 ORDER BY sortYear ASC")
    fun getFeaturedEvents(): Flow<List<HistoryEvent>>

    @Query("SELECT * FROM history_events WHERE dayOfMonth = :day AND month = :month")
    fun getEventsOnThisDay(day: Int, month: Int): Flow<List<HistoryEvent>>

    @Query("""
        SELECT * FROM history_events 
        WHERE title LIKE '%' || :query || '%' 
           OR summary LIKE '%' || :query || '%' 
           OR keyFigures LIKE '%' || :query || '%' 
           OR yearOrPeriod LIKE '%' || :query || '%'
        ORDER BY sortYear ASC
    """)
    fun searchEvents(query: String): Flow<List<HistoryEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<HistoryEvent>)

    @Update
    suspend fun updateEvent(event: HistoryEvent)

    // Bookmarks
    @Query("SELECT b.eventId, b.timestamp, b.customNote FROM user_bookmarks b ORDER BY b.timestamp DESC")
    fun getBookmarkEntities(): Flow<List<UserBookmark>>

    @Query("""
        SELECT e.* FROM history_events e 
        INNER JOIN user_bookmarks b ON e.id = b.eventId 
        ORDER BY b.timestamp DESC
    """)
    fun getBookmarkedEvents(): Flow<List<HistoryEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: UserBookmark)

    @Query("DELETE FROM user_bookmarks WHERE eventId = :eventId")
    suspend fun removeBookmark(eventId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM user_bookmarks WHERE eventId = :eventId)")
    fun isBookmarked(eventId: String): Flow<Boolean>

    // Search History
    @Query("SELECT * FROM search_history ORDER BY timestamp DESC LIMIT 10")
    fun getRecentSearches(): Flow<List<SearchQuery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(query: SearchQuery)

    @Query("DELETE FROM search_history")
    suspend fun clearSearchHistory()
}
