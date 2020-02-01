package com.nailuj29gaming.countdown

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.concurrent.TimeUnit

class CountdownAdapter(val dates: List<Date>, val names: List<String>) : RecyclerView.Adapter<CountdownAdapter.ViewHolder>() {

    inner class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

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
        val diffInMillis = Math.abs(dates[position].time - Date().time)
        val diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        daysLeftText.text = diffInDays.toString()
        val eventNameText = cv.findViewById<TextView>(R.id.eventNameCard)
        eventNameText.text = names[position]
    }
}