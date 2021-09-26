package com.example.group27project1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.group27project1.Game
import com.example.group27project1.MainActivity
import java.util.*

@Dao
interface GameDAO {

    @Query("SELECT * FROM "+ MainActivity.TABLE_NAME)
    fun getGames(): LiveData<List<Game>>

    @Query("SELECT * FROM "+ MainActivity.TABLE_NAME+" WHERE id=(:id)")
    fun getGame(id: UUID): LiveData<Game?>

    @Update
    fun updateCrime(game: Game)
    @Insert
    fun addCrime(game: Game)
}