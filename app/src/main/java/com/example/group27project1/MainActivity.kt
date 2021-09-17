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

private const val TAG = "MainActivity"
private const val aKEY_INDEX = "aindex"
private const val bKEY_INDEX = "bindex"
private const val REQUEST_CODE_SAVE = 0


class MainActivity : AppCompatActivity() {

    /*
    private lateinit var teamAScoreTextView: TextView
    private lateinit var teamBScoreTextView: TextView
    private lateinit var freeABtn: Button
    private lateinit var freeBBtn: Button
    private lateinit var twoABtn: Button
    private lateinit var twoBBtn: Button
    private lateinit var threeABtn: Button
    private lateinit var threeBBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var saveBtn: Button

    private lateinit var  rem1ABtn: Button
    private lateinit var rem1BBtn: Button

    private val bbViewModel: BasketballViewModel by lazy {
        ViewModelProviders.of(this).get(BasketballViewModel::class.java)
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = HomeFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
        /*
        val acurrentIndex = savedInstanceState?.getInt(aKEY_INDEX, 0) ?: 0
        bbViewModel.aScore = acurrentIndex
        val bcurrentIndex = savedInstanceState?.getInt(bKEY_INDEX, 0) ?: 0
        bbViewModel.bScore = bcurrentIndex


        //val provider: ViewModelProvider = ViewModelProviders.of(this)
        //val bbViewModel = provider.get(BasketballViewModel::class.java)
        Log.d("BasketballViewModel", "Got a BasketballViewModel: $bbViewModel")


        freeABtn = findViewById(R.id.freeA_btn)
        freeBBtn = findViewById(R.id.freeB_btn)
        twoABtn = findViewById(R.id.twoA_btn)
        twoBBtn = findViewById(R.id.twoB_btn)
        threeABtn = findViewById(R.id.threeA_btn)
        threeBBtn = findViewById(R.id.threeB_btn)

        rem1ABtn = findViewById(R.id.undoA_btn)
        rem1BBtn = findViewById(R.id.undoB_btn)

        teamAScoreTextView = findViewById(R.id.teamA_score)
        teamBScoreTextView = findViewById(R.id.teamB_score)
        resetBtn = findViewById(R.id.reset_btn)
        saveBtn = findViewById(R.id.save_button)

        freeABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore +1)
            //teamAScoreTextView.text = bbViewModel.getCurrentAScore.toString()
            updateScores()
        }

        freeBBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore +1)
            //teamBScoreTextView.text = bbViewModel.getCurrentBScore.toString()
            updateScores()

        }

        twoABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore +2)
            updateScores()
        }

        twoBBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore +2)
            updateScores()
        }

        threeABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore +3)
            updateScores()
        }

        threeBBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore +3)
            updateScores()
        }

        rem1ABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore -1)
            updateScores()
        }
        rem1BBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore -1)
            updateScores()
        }

        resetBtn.setOnClickListener {
            bbViewModel.setCurrentAScore(0)
            bbViewModel.setCurrentBScore(0)
            updateScores()
        }

        saveBtn.setOnClickListener {
            // Start saveActivity
            val intent = SecondActivity.newIntent(this@MainActivity, bbViewModel.getCurrentAScore, bbViewModel.getCurrentBScore)
            startActivityForResult(intent, REQUEST_CODE_SAVE)

        }

        updateScores()

         */
    }
    /*
    private fun updateScores() {
        teamAScoreTextView.setText(bbViewModel.getCurrentAScore.toString())
        teamBScoreTextView.setText(bbViewModel.getCurrentBScore.toString())

        val messageResId = when {
            bbViewModel.isScoreSaved -> R.string.scoreSaved
            else ->  R.string.scoreNotSaved
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(aKEY_INDEX, bbViewModel.getCurrentAScore)
        savedInstanceState.putInt(bKEY_INDEX, bbViewModel.getCurrentBScore)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
    */

    override fun onActivityResult(requestCode: Int,  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_SAVE) {
            bbViewModel.isScoreSaved =
                data?.getBooleanExtra(EXTRA_SCORES_SAVED, false) ?: false
        }
    }

}