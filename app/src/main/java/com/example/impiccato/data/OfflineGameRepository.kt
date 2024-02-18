package com.example.impiccato.data

import com.example.impiccato.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineGameRepository @Inject constructor(
    val gameDao: GameDao
) : GameRepository {
    override suspend fun saveGame(game: Game) {
        gameDao.saveGame(game)
    }

    override fun getBestGames(): Flow<List<Game>> {
        return gameDao.getBestGames()
    }

    override suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }
}