package com.example.group27project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var aScore: Int = 0
    private var bScore: Int = 0
    private lateinit var teamAScoreTextView: TextView
    private lateinit var teamBScoreTextView: TextView
    private lateinit var freeABtn: Button
    private lateinit var freeBBtn: Button
    private lateinit var twoABtn: Button
    private lateinit var twoBBtn: Button
    private lateinit var threeABtn: Button
    private lateinit var threeBBtn: Button
    private lateinit var resetBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        freeABtn = findViewById(R.id.freeA_btn)
        freeBBtn = findViewById(R.id.freeB_btn)
        twoABtn = findViewById(R.id.twoA_btn)
        twoBBtn = findViewById(R.id.twoB_btn)
        threeABtn = findViewById(R.id.threeA_btn)
        threeBBtn = findViewById(R.id.threeB_btn)
        teamAScoreTextView = findViewById(R.id.teamA_score)
        teamBScoreTextView = findViewById(R.id.teamB_score)
        resetBtn = findViewById(R.id.reset_btn)

        freeABtn.setOnClickListener {
            aScore++
            teamAScoreTextView.text = aScore.toString()
        }

        freeBBtn.setOnClickListener {
            bScore++
            teamBScoreTextView.text = bScore.toString()
        }

        twoABtn.setOnClickListener {
            aScore+=2
            teamAScoreTextView.text = aScore.toString()
        }

        twoBBtn.setOnClickListener {
            bScore+=2
            teamBScoreTextView.text = bScore.toString()
        }

        threeABtn.setOnClickListener {
            aScore+=3
            teamAScoreTextView.text = aScore.toString()
        }

        threeBBtn.setOnClickListener {
            bScore+=3
            teamBScoreTextView.text = bScore.toString()
        }

        resetBtn.setOnClickListener {
            aScore = 0
            bScore = 0
            teamAScoreTextView.text = aScore.toString()
            teamBScoreTextView.text = bScore.toString()
        }
    }
}