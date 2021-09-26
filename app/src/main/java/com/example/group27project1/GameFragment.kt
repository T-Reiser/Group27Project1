package com.example.group27project1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        //titleField = view.findViewById(R.id.game_title) as EditText

        return view
    }
}