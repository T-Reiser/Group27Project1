package com.example.group27project1

import java.util.*

data class Game(
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var team1: Team = Team(),
    var team2: Team = Team(),
)

