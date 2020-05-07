package com.nailuj29gaming.countdown

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Countdown::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countdownDao(): CountdownDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance: AppDatabase? = INSTANCE
            if(tempInstance != null) {
                Log.i(TAG, "Database got; database already created")
                return tempInstance
            }
            synchronized(this) {
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "countdowns"
                ).build()
                INSTANCE = instance
                Log.i(TAG, "Database instance created")
                return instance
            }
        }
    }
}