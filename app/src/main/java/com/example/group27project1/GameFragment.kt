package com.example.group27project1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.util.*
import androidx.lifecycle.Observer

private const val ARG_GAME_ID = "game_id"
private const val TAG = "GameFragment"

class GameFragment : Fragment() {

    private lateinit var game: Game
    private lateinit var titleField: EditText

    private val gameDetailViewModel: GameDetailViewModel by lazy {
        ViewModelProviders.of(this).get(GameDetailViewModel::class.java)
    }

    companion object {
        fun newInstance(gameId: UUID): GameFragment {
            val args = Bundle().apply {
                putSerializable(ARG_GAME_ID, gameId)
            }
            return GameFragment().apply {
                arguments = args
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameDetailViewModel.gameLiveData.observe(
            viewLifecycleOwner,
            Observer { game ->
                game?.let {
                    this.game = game
                    updateUI()
                }
            })
    }

    private fun updateUI() {
        titleField.setText(game.id.toString())
        //dateButton.text = game.date.toString()
//        solvedCheckBox.apply {
//            isChecked = crime.isSolved
//            jumpDrawablesToCurrentState()
//        }
    }


    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {

            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // This space intentionally left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                //game.id = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                // This one too
            }
        }
        titleField.addTextChangedListener(titleWatcher)

//        solvedCheckBox.apply {
//            setOnCheckedChangeListener { _, isChecked ->
//                crime.isSolved = isChecked
//            }
//        }
    }

    override fun onStop() {
        super.onStop()
        gameDetailViewModel.saveGame(game)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Game()
        val gameId: UUID = arguments?.getSerializable(ARG_GAME_ID) as UUID
        Log.d(TAG, "args bundle crime ID: $gameId")
        gameDetailViewModel.loadGame(gameId)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_item_game, container, false)

//        titleField = view.findViewById(R.id.crime_title) as EditText
//        dateButton = view.findViewById(R.id.crime_date) as Button
//        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox
//
//        dateButton.apply {
//            text = crime.date.toString()
//            isEnabled = false
//        }

        return view
    }
}