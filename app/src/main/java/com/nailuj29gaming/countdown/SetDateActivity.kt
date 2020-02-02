package com.nailuj29gaming.countdown

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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
        /*prefs = getSharedPreferences("com.nailuj29gaming.countdown", Context.MODE_PRIVATE)
        editor = prefs.edit()
        gson = Gson()*/
        submit.setOnClickListener {
            date?.let {
                Log.i("date:", it.toString())
                val replyIntent = Intent()
                if (TextUtils.isEmpty(enterName.text) || it < Date()) {
                    setResult(Activity.RESULT_CANCELED)
                } else {
                    replyIntent.putExtra(EXTRA_NAME, enterName.text.toString())
                    replyIntent.putExtra(EXTRA_DATE, it.time)
                    finish()
                }
            }
        }
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = GregorianCalendar(year, month, dayOfMonth)
            date = cal.time
        }

    }

    companion object {
        const val EXTRA_DATE = "com.nailuj29gaming.countdown.DATE"
        const val EXTRA_NAME = "com.nailuj29gaming.countdown.NAME"
    }
}
