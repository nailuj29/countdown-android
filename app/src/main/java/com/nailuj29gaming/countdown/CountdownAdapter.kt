package com.nailuj29gaming.countdown

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class CountdownAdapter(val viewModelOwner: ViewModelStoreOwner) : RecyclerView.Adapter<CountdownAdapter.ViewHolder>() {

    inner class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
    var countdowns: List<Countdown>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv: CardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_countdown, parent, false) as CardView
        return ViewHolder(cv)
    }


    override fun getItemCount(): Int {
        return countdowns?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cv = holder.cardView
        val daysLeftText = cv.findViewById<TextView>(R.id.daysLeftCard)
        val diffInMillis = abs(countdowns!![position].date.time - Date().time)
        val diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        daysLeftText.text = diffInDays.toString()
        val eventNameText = cv.findViewById<TextView>(R.id.eventNameCard)
        eventNameText.text = countdowns!![position].eventName
        val buttonDelete = cv.findViewById<ImageButton>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            val viewModel = ViewModelProvider(viewModelOwner).get(CountdownViewModel::class.java)
            viewModel.delete(countdowns!![position])
        }
    }
}