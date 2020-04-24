package com.nailuj29gaming.countdown

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class CountdownAdapter(private val viewModel: CountdownViewModel, private val owner: MainActivity) : RecyclerView.Adapter<CountdownAdapter.ViewHolder>() {

    inner class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
    var countdowns = listOf<Countdown>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv: CardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_countdown, parent, false).findViewById(R.id.cardView)
        return ViewHolder(cv)
    }


    override fun getItemCount(): Int {
        return countdowns.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cv = holder.cardView
        val layout = cv.findViewById<LinearLayout>(R.id.LinearLayoutCard)
        val daysLeftText = layout.findViewById<TextView>(R.id.daysLeftCard)
        val diffInMillis = abs(countdowns[position].date.time - Date().time)
        val diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        daysLeftText.text = diffInDays.toString()
        val eventNameText = layout.findViewById<TextView>(R.id.eventNameCard)
        eventNameText.text = countdowns[position].eventName
        val buttonDelete = layout.findViewById<ImageButton>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            viewModel.delete(countdowns[position])
            owner.recreate()
        }
    }
}