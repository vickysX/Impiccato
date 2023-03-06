package com.example.impiccato.ui

data class GameUIState(
    val score : Int = 100,
    val currentWordToGuess : String = "",
    val wordDefinition : String = "",
    val isGuessedLetterWrong : Boolean = false,
    val numWrongGuessesPerWord : Int = 0,
    val isHintRequired : Boolean = false,
    val isBulbButtonEnabled : Boolean = true,
    val isGameOver : Boolean = false,
    val isGameEnded : Boolean = false
)
