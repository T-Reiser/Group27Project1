package com.example.group27project1

import android.app.Application

class BasketballApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        GameRepository.initialize(this)
    }
}