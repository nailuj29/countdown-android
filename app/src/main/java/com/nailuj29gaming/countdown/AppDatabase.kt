package com.nailuj29gaming.countdown

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(Countdown::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countdownDao(): CountdownDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance: AppDatabase? = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance =  Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "countdowns"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}