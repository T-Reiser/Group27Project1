package com.example.group27project1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.util.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task


private const val REQUEST_CODE_SAVE = 0

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(),
    HomeFragment.Callbacks {

    companion object {
        const val TABLE_NAME: String="table_game"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = HomeFragment.newInstance()
            //val fragment = GameListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }



    }


    override fun onGameSelected(gameId: UUID) {
        //Log.d(TAG, "MainActivity.onCrimeSelected: $gameId")
        val fragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDispSelected(gameId: UUID) {

        val fragment = GameListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }



}