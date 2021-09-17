package com.example.group27project1

import androidx.lifecycle.ViewModel

class GameListViewModel : ViewModel(){

    val games = mutableListOf<Game>()
    init {
        for (i in 0 until 100) {
            val game = Game()
            game.title = "Game #$i"
            game.team1 = Team()
            game.team2 = Team()
            games += game
        }
    }
}