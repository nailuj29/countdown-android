package com.nailuj29gaming.countdown

import android.provider.UserDictionary
import androidx.lifecycle.LiveData

class CountdownRepo(private val dao: CountdownDao) {
    var countdowns: LiveData<List<Countdown>> = dao.sortByDates()
    
}