package com.example.a30_days_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StockCacheEntity::class], version = 1, exportSchema = false)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var Instance: StockDatabase? = null

        fun getDatabase(context: Context): StockDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StockDatabase::class.java, "stock_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
