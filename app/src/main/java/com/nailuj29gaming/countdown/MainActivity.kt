package com.nailuj29gaming.countdown

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_ui_layout.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    lateinit var names: MutableList<String>
    lateinit var dates: MutableList<Date>
    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_ui_layout)
        fab.setOnClickListener {
            Log.i("Clicked", "fab clicked")
        }
        if(!getSharedPreferences("com.nailuj29gaming.countdown", Context.MODE_PRIVATE)
                .getBoolean("migrated", false) &&
           getSharedPreferences("com.nailuj29gaming.countdown", Context.MODE_PRIVATE)
               .getBoolean("date_set", false)) {
            val prefs = getSharedPreferences("com.nailuj29gaming.countdown", Context.MODE_PRIVATE)
            val gson = Gson()
            val date = gson.fromJson(prefs.getString("date", ""), Date::class.java)
            val name = prefs.getString("name", "Example Event")
        }
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "countdowns"
        ).build()
        val dao = db?.countdownDao()
        val countdowns = dao?.getAll()
        if (countdowns != null) {
            for (countdown in countdowns) {
                if (countdown.date > Date()) {
                    dates.add(countdown.date)
                    names.add(countdown.eventName)
                } else
                    dao.delete(countdown)
                names.add(countdown.eventName)
            }
        }
        recyclerView.adapter = CountdownAdapter(applicationContext, dates, names)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings -> {
                Toast.makeText(this, "Settings not currently implemented, will be added in a future update",
                    Toast.LENGTH_SHORT).show()
                // val intent = Intent(this, SettingsActivity::class.java)
                // startActivity(intent)
            }
            R.id.changeDate -> {
                val intent = Intent(this, SetDateActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            val dao = db?.countdownDao()
            dao?.insert(Countdown(data?.getStringExtra(SetDateActivity.EXTRA_NAME)!!, Date(data.getLongExtra(SetDateActivity.EXTRA_DATE, Date().time)!!)))
            recreate()
        } else {
            Toast.makeText(applicationContext,
                R.string.toast_not_set,
                Toast.LENGTH_LONG
                ).show()
        }
    }


}
