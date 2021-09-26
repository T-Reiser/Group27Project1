package com.example.group27project1

//import GameDatabase
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.group27project1.database.GameDatabase
import java.util.*
import java.util.concurrent.Executors

    private const val DATABASE_NAME = "game-database"

class GameRepository private constructor(context: Context) {

    private val database : GameDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val gameDao = database.gameDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getGames(): LiveData<List<Game>> = gameDao.getGames()

    fun getGame(id: UUID): LiveData<Game?> = gameDao.getGame(id)

    fun updateGame(game: Game) {
        executor.execute {
            gameDao.updateCrime(game)
        }
    }

    fun addGame(crime: Game) {
        executor.execute {
            gameDao.addCrime(crime)
        }
    }

    companion object {
        private var INSTANCE: GameRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GameRepository(context)
            }
        }
        fun get(): GameRepository {
            return INSTANCE ?:
            throw IllegalStateException("GameRepository must be initialized")
        }
    }

}