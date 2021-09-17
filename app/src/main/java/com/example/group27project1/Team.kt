package com.example.group27project1

import java.util.*

data class Team(val id: UUID = UUID.randomUUID(),
                var name: String = "",
                var score: Int = 0)
