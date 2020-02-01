package com.nailuj29gaming.countdown

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountdownDao {
    @Query("SELECT * FROM countdown")
    fun getAll(): List<Countdown>

    @Query("SELECT * FROM countdown WHERE id IN (:ids)")
    fun getById(ids: IntArray): List<Countdown>

    @Query("SELECT * FROM countdown ORDER BY date")
    fun sortByDates(): List<Countdown>

    @Insert
    fun insertAll(vararg countdowns: Countdown)

    @Delete
    fun delete(countdown: Countdown)
}