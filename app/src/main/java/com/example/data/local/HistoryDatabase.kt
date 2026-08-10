package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.HistoryEvent
import com.example.data.model.SearchQuery
import com.example.data.model.UserBookmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [HistoryEvent::class, UserBookmark::class, SearchQuery::class],
    version = 1,
    exportSchema = false
)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getDatabase(context: Context): HistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    "history_lookup_db"
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Pre-populate database on creation
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                database.historyDao().insertEvents(InitialData.sampleEvents)
                            }
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                // Extra safety check: if database was already created, ensure initial items exist
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = instance.historyDao()
                    val existing = dao.getEventById("hung_vuong_van_lang")
                    if (existing == null) {
                        dao.insertEvents(InitialData.sampleEvents)
                    }
                }
                instance
            }
        }
    }
}
