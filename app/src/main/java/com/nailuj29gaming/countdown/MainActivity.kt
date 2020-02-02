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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_ui_layout.*
import java.util.Date
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    lateinit var names: MutableList<String>
    lateinit var dates: MutableList<Date>
    private lateinit var viewModel: CountdownViewModel

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


        val adapter = CountdownAdapter(this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this).get(CountdownViewModel::class.java)
        viewModel.countdowns.observe(this, Observer { countdowns ->
            countdowns?.let { adapter.countdowns = it }
        }

        )

    }
    

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        TODO("Implement onActivityResult")
    }


}
