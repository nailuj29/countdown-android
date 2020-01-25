package com.nailuj29gaming.countdown

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var gson: Gson
    var date: Date = Date()
    var name: String = ""
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = getSharedPreferences("com.nailuj29gaming.countdown", Context.MODE_PRIVATE)
        editor = prefs.edit()
        gson = Gson()
        if (!prefs.getBoolean("date_set", false)) {
            val intent = Intent(this, SetDateActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "No event or past event set, you must set a new event",
                Toast.LENGTH_SHORT).show()
        } else {
            //Shouldn't get to this point unless date and name are set; assuming they are
            name = prefs.getString("name", "Name").toString()
            date = gson.fromJson(prefs.getString("date", ""), Date::class.java)
            Log.i("date-MainActivity", date.toString())
        }
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        eventName.text = name

        val diffInMillis = date.time - Date().time
        //Difference drops a day, so add it back
        val days = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS) + 1
        Log.i("diff", days.toString())
        daysLeft.text = days.toString()
        if(days < 0) {
            val intent = Intent(this, SetDateActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "No event or past event set, you must set a new event",
                Toast.LENGTH_SHORT).show()
        }
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

    //Debug to test SetDateActivity
    /*override fun onPause() {
        editor.putBoolean("date_set", false)
        editor.apply()
        super.onPause()
    }*/
}
