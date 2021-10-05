package com.example.group27project1

import android.util.Log
import androidx.lifecycle.ViewModel
import java.io.File

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
    var isScoreSaved = false


    //val gameSummary = mutableListOf<Games>()


    //add methods here
    val getCurrentAScore: Int
        get() = curGame.teamAScore
    val getCurrentBScore: Int
        get() = curGame.teamBScore

    fun setCurrentAScore(newScore: Int) {
        curGame.teamAScore = newScore
    }

    fun setCurrentBScore(newScore: Int) {
        curGame.teamBScore = newScore
    }
    fun addGame(game: Game) {
        gameRepository.addGame(game)
    }
    fun updateGame(game: Game) {
        gameRepository.updateGame(game)
    }
    fun getPhotoFile(game: Game): File {
        return gameRepository.getPhotoFile(game)
    }


}