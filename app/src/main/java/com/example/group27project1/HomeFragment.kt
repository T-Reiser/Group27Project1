package com.example.group27project1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val aKEY_INDEX = "aindex"
private const val bKEY_INDEX = "bindex"

class HomeFragment : Fragment() {
    private lateinit var game: Game
    private lateinit var teamA: Team
    private lateinit var teamB: Team
    private lateinit var teamAName: EditText

    private lateinit var teamAScoreTextView: TextView
    private lateinit var teamBScoreTextView: TextView
    private lateinit var freeABtn: Button
    private lateinit var freeBBtn: Button
    private lateinit var twoABtn: Button
    private lateinit var twoBBtn: Button
    private lateinit var threeABtn: Button
    private lateinit var threeBBtn: Button
    private lateinit var resetBtn: Button

    private lateinit var  rem1ABtn: Button
    private lateinit var rem1BBtn: Button

    private val bbViewModel: BasketballViewModel by lazy {
        ViewModelProviders.of(this).get(BasketballViewModel::class.java)
    }

    companion object {
        fun newInstance(): GameListFragment {
            return GameListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamA = Team()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        teamAName = view.findViewById(R.id.teamA_name) as EditText

        freeABtn = view.findViewById(R.id.freeA_btn)
        freeBBtn = view.findViewById(R.id.freeB_btn)
        twoABtn = view.findViewById(R.id.twoA_btn)
        twoBBtn = view.findViewById(R.id.twoB_btn)
        threeABtn = view.findViewById(R.id.threeA_btn)
        threeBBtn = view.findViewById(R.id.threeB_btn)

        rem1ABtn = view.findViewById(R.id.undoA_btn)
        rem1BBtn = view.findViewById(R.id.undoB_btn)

        teamAScoreTextView = view.findViewById(R.id.teamA_score)
        teamBScoreTextView = view.findViewById(R.id.teamB_score)
        resetBtn = view.findViewById(R.id.reset_btn)

        val acurrentIndex = savedInstanceState?.getInt(aKEY_INDEX, 0) ?: 0
        bbViewModel.aScore = acurrentIndex
        val bcurrentIndex = savedInstanceState?.getInt(bKEY_INDEX, 0) ?: 0
        bbViewModel.bScore = bcurrentIndex


        //val provider: ViewModelProvider = ViewModelProviders.of(this)
        //val bbViewModel = provider.get(BasketballViewModel::class.java)
        Log.d("BasketballViewModel", "Got a BasketballViewModel: $bbViewModel")

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
        updateScores()


        return view
        //return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun updateScores() {
        teamAScoreTextView.setText(bbViewModel.getCurrentAScore.toString())
        teamBScoreTextView.setText(bbViewModel.getCurrentBScore.toString())
    }

    override fun onResume() {
        super.onResume()

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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onResume() called")

        val teamANameWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {
                teamA.name = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        }
    }


}