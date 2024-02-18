package com.example.impiccato.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.impiccato.model.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert
    suspend fun saveGame(game: Game)

    @Query("SELECT * FROM game ORDER BY score DESC LIMIT 5")
    fun getBestGames(): Flow<List<Game>>

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteGames(games: List<Game>)
}