package com.nailuj29gaming.countdown

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountdownDao {
    @Query("SELECT * FROM countdown")
    fun getAll(): LiveData<List<Countdown>>

    @Query("SELECT * FROM countdown ORDER BY date ASC")
    fun sortByDates(): LiveData<List<Countdown>>

    @Insert
    suspend fun insertAll(vararg countdowns: Countdown)

    @Insert
    suspend fun insert(countdown: Countdown)

    @Delete
    suspend fun delete(countdown: Countdown)
}