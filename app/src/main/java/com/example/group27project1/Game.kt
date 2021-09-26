package com.example.group27project1

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Game(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var teamAName: String = "",
    var teamBName: String = "",
    var teamAScore: Int = 0,
    var teamBScore: Int = 0,
    var date: Date = Date(),



)

