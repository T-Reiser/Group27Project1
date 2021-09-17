package com.example.group27project1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders



private const val REQUEST_CODE_SAVE = 0

class MainActivity : AppCompatActivity() {

    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            //val fragment = HomeFragment()
            val fragment = GameListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

//        saveBtn = findViewById(R.id.save_btn)
//        saveBtn.setOnClickListener {
//            // Start saveActivity
//            val intent = SecondActivity.newIntent(this@MainActivity, 0, 0)
//            startActivityForResult(intent, REQUEST_CODE_SAVE)
//
//        }
    }



}