package com.example.impiccato.utils

import com.example.impiccato.model.DifficultyLevel
import com.example.impiccato.model.Game

class GameManager(
    private val currentGame: Game
) {

    private var level = currentGame.level
    var wrongGuessesInScorePoints = 0
    var usedLetters = mutableListOf<String>()

    enum class GameError {
        None, UsedLetter, NotLetter
    }

    fun goToNextLevel() {
        when (level) {
            DifficultyLevel.Easy -> currentGame.level = DifficultyLevel.Medium
            DifficultyLevel.Medium -> currentGame.level = DifficultyLevel.Difficult
            DifficultyLevel.Difficult -> currentGame.level = DifficultyLevel.Easy
        }
        level = currentGame.level
        resetErrorsAndUsedLetters()
    }

    fun isWordGuessed(guess: String, currentWord: String): Boolean {
        return guess.equals(currentWord, ignoreCase = true)
    }

    fun validateInput(input: String): GameError {
        return when {
            input.contains(Regex(pattern = "/[^a-zA-Z]/")) -> GameError.NotLetter
            usedLetters.contains(input) -> GameError.UsedLetter
            else -> GameError.None
        }
    }

    fun updateScore(error: Boolean) {
        val scoreIncrement = level.scoreIncrement
        when {
            error ->  {
                currentGame.score -= scoreIncrement
                wrongGuessesInScorePoints = usedLetters.size * scoreIncrement
            }
            else -> currentGame.score += scoreIncrement
        }
    }

    private fun resetErrorsAndUsedLetters() {
        wrongGuessesInScorePoints = 0
        usedLetters.clear()
    }
}