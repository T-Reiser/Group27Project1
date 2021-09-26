package com.example.group27project1

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "BasketballViewModel"

class BasketballViewModel : ViewModel(){

    init {
        Log.d(TAG, "ViewModel instance created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
    private val gameRepository = GameRepository.get()
    val gameListLiveData = gameRepository.getGames()



    val curGame = Game()
    //store team scores here
    var aScore: Int = 0
    var bScore: Int = 0
    var isScoreSaved = false


    //val gameSummary = mutableListOf<Games>()


    //add methods here
    val getCurrentAScore: Int
        get() = aScore
    val getCurrentBScore: Int
        get() = bScore

    fun setCurrentAScore(newScore: Int) {
        aScore = newScore
    }

    fun setCurrentBScore(newScore: Int) {
        bScore = newScore
    }
    fun addGame(game: Game) {
        gameRepository.addGame(game)
    }


}