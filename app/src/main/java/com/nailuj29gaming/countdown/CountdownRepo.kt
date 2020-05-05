package com.nailuj29gaming.countdown

import androidx.lifecycle.LiveData

class CountdownRepo(private val dao: CountdownDao) {
    var countdowns: LiveData<List<Countdown>> = dao.getAll()

    suspend fun insert(countdown: Countdown) {
        dao.insertAll(countdown)
    }

    suspend fun delete(countdown: Countdown) {
        dao.delete(countdown)
    }
}