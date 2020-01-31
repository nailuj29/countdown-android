package com.nailuj29gaming.countdown

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountdownDao {
    @Query("SELECT * FROM countdown")
    fun getAll(): List<Countdown>

    @Query("SELECT * FROM countdown WHERE id IN (:ids)")
    fun getById(uids: IntArray): List<Countdown>

    @Query("SELECT * FROM countdown SORT BY ")
}