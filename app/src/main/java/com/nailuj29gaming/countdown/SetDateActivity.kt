package com.nailuj29gaming.countdown

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_set_date.*
import java.text.SimpleDateFormat
import java.util.*

class SetDateActivity : AppCompatActivity() {
    var date: Date? = null
    lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_date)
        setSupportActionBar(tootbarSetDate)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        prefs = getSharedPreferences("com.nailuj29gaming.countdown", Context.MODE_PRIVATE)
        editor = prefs.edit()
        gson = Gson()
        submit.setOnClickListener {
            date?.let {
                Log.i("date:", it.toString())
                if(it > Date()) {
                    if(enterName.text.toString() != "") {
                        val dateString = gson.toJson(it)
                        editor.putString("date", dateString)
                        editor.putString("name", enterName.text.toString())
                        editor.putBoolean("date_set", true)
                        editor.commit()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "You must enter a name for your event",
                            Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                } else {
                    Toast.makeText(this, "The date must be in the future.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = GregorianCalendar(year, month, dayOfMonth)
            date = cal.time
        }
    }
}
