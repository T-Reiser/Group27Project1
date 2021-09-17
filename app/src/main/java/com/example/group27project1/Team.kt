package com.example.group27project1

import java.util.*

data class Team(val id: UUID = UUID.randomUUID(),
                var name: String = id.toString().substring(0,8),
                var score: Int = (Math.random() * 100).toInt())
