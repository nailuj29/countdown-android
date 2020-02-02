package com.nailuj29gaming.countdown

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.new_ui_layout.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val requestCode = 42 // Life, the universe, and everything
    lateinit var names: MutableList<String>
    lateinit var dates: MutableList<Date>
    private lateinit var viewModel: CountdownViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_ui_layout)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, SetDateActivity::class.java)
            startActivityForResult(intent, requestCode)
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
        if(requestCode == this.requestCode && requestCode == Activity.RESULT_OK) {
            data?.getStringExtra(SetDateActivity.EXTRA_NAME)?.let { name ->
                data?.getLongExtra(SetDateActivity.EXTRA_DATE, 0)?.let { date ->
                    val countdown = Countdown(name, Date(date))
                    viewModel.insert(countdown)
                    
                }
            }
        }
    }


}
