package com.example.impiccato.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impiccato.data.GameRepository
import com.example.impiccato.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val bestGames: StateFlow<HomeScreenUiState> =
        gameRepository.getBestGames().map {
            HomeScreenUiState(bestGames = it)
        }
            .stateIn(
                scope = viewModelScope,
                initialValue = HomeScreenUiState(bestGames = emptyList()),
                started = SharingStarted.WhileSubscribed(5_000L)
            )

    fun startNewGame() {
        viewModelScope.launch {
            val game = Game()
            gameRepository.saveGame(game)
        }

    }
}

data class HomeScreenUiState(val bestGames: List<Game>)