package com.example.group27project1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



private const val TEAM_ONE_SCORE = "com.example.group27project1.team1Score"

private const val TEAM_TWO_SCORE = "com.example.group27project1.team2Score"

const val EXTRA_SCORES_SAVED = "com.example.group27project1.scoresSaved"

class SecondActivity : AppCompatActivity() {

    private lateinit var backBtn: Button

    private var retTeam1Score = 0
    private var retTeam2Score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        backBtn = findViewById(R.id.back_button)

        retTeam1Score = intent.getIntExtra(TEAM_ONE_SCORE, 0)

        retTeam2Score = intent.getIntExtra(TEAM_TWO_SCORE, 0)


        backBtn.setOnClickListener {
            setAnswerShownResult(true)
        }


    }

    private fun setAnswerShownResult(isSaved: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_SCORES_SAVED, isSaved)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, team1Score: Int, team2Score: Int): Intent {
            return Intent(packageContext, SecondActivity::class.java).apply {
                putExtra(TEAM_ONE_SCORE, team1Score)
                putExtra(TEAM_TWO_SCORE, team2Score)
            }
        }
    }

}