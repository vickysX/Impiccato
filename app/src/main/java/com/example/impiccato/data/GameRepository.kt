package com.example.impiccato.data

import com.example.impiccato.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun saveGame(game: Game)
    fun getBestGames(): Flow<List<Game>>
    suspend fun updateGame(game: Game)
}