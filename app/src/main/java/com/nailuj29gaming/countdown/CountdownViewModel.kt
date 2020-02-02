package com.nailuj29gaming.countdown

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountdownViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: CountdownRepo
    val countdowns: LiveData<List<Countdown>>

    init {
        val countdownDao = AppDatabase.getDatabase(application).countdownDao()
        repo = CountdownRepo(countdownDao)
        countdowns = repo.countdowns

    }

    fun insert(countdown: Countdown) = viewModelScope.launch {
        repo.insert(countdown)
    }

    fun delete(countdown: Countdown) = viewModelScope.launch {
        repo.delete(countdown)
    }
}