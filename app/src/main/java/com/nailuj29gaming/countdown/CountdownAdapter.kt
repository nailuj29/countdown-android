package com.nailuj29gaming.countdown

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class CountdownAdapter(val context: Context, val dates: List<Date>, val names: List<String>) : RecyclerView.Adapter<CountdownAdapter.ViewHolder>() {

    inner class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
    val db: AppDatabase?

    init {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "countdowns").build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv: CardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_countdown, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cv = holder.cardView
        val daysLeftText = cv.findViewById<TextView>(R.id.daysLeftCard)
        val diffInMillis = abs(dates[position].time - Date().time)
        val diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        daysLeftText.text = diffInDays.toString()
        val eventNameText = cv.findViewById<TextView>(R.id.eventNameCard)
        eventNameText.text = names[position]
        val buttonDelete = cv.findViewById<ImageButton>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            db?.countdownDao()?.delete(Countdown(names[position], dates[position]))
        }
    }
}