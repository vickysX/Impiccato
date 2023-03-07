package com.example.impiccato.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.impiccato.data.INCREASE_DECREASE_SCORE
import com.example.impiccato.data.MAX_NO_OF_WORDS
import com.example.impiccato.data.MAX_NO_OF_WRONG_GUESSES
import com.example.impiccato.data.words
import com.example.impiccato.model.Word
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val TAG = "GameViewModel"

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUIState())
    val uiState : StateFlow<GameUIState> = _uiState.asStateFlow()

    private val usedWords : MutableSet<Word> = mutableSetOf()
    private lateinit var currentWord : Word

    var userGuess by mutableStateOf("")
        //private set
    var wordInProgress by mutableStateOf("")
        //private set
    private val guessedLetters : MutableSet<String> = mutableSetOf()
    private var wordCount = 1

    init {
        resetGame()
    }

    fun resetGame() {
        guessedLetters.clear()
        usedWords.clear()
        wordInProgress = chooseWordAndDisplay()
        _uiState.value = GameUIState(
            score = 100,
            isGuessedLetterWrong = false,
            isHintRequired = false,
            isBulbButtonEnabled = true,
            numWrongGuessesPerWord = 0,
            currentWordToGuess = wordInProgress,
            wordDefinition = currentWord.wordDefinition,
            isGameOver = false,
            isGameEnded = false
        )
    }

    fun updateUserGuess(letterGuess : String) {
        userGuess = letterGuess
        Log.d(TAG, "userGuess = $userGuess")
    }

    fun checkUserGuess() {
        var updatedScore = _uiState.value.score
        var wrongGuesses = _uiState.value.numWrongGuessesPerWord
        if (!guessedLetters.contains(userGuess)) {
            if (currentWord.word.contains(userGuess)) {
                updatedScore = updatedScore.plus(INCREASE_DECREASE_SCORE)
                updateWordDisplayed()
            } else {
                updatedScore = updatedScore.minus(INCREASE_DECREASE_SCORE)
                wrongGuesses++
            }
            guessedLetters.add(userGuess)
            updateGameUI(updatedScore, wrongGuesses)
        }
        userGuess = ""
    }

    private fun updateWordDisplayed() {
        val wordArr : MutableList<String> =
            wordInProgress.split(" ") as MutableList<String>
        Log.d(TAG, "wordArr = $wordArr")
        var index = 0
        while (index <= currentWord.word.lastIndexOf(userGuess)) {
            index = currentWord.word.indexOf(userGuess, index)
            wordArr[index] = userGuess
            index++
        }
        wordInProgress = wordArr.joinToString(" ")
        Log.d(TAG, "wordInProgress = $wordInProgress")
        _uiState.update { currentState ->
            currentState.copy(
                currentWordToGuess = wordInProgress
            )

        }
    }

    private fun updateGameUI(updatedScore: Int, wrongGuesses: Int) {
        if (updatedScore <= 0) {
            endGame(updatedScore, true)
        } else if (!wordInProgress.contains("_") || wrongGuesses > MAX_NO_OF_WRONG_GUESSES) {
            goToNextWord(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    isGameOver = false,
                    isGameEnded = false,
                    numWrongGuessesPerWord = wrongGuesses
                )
            }
        }
    }

    fun goToNextWord(updatedScore : Int) {
        guessedLetters.clear()
        wordCount++
        if (wordCount > MAX_NO_OF_WORDS) {
            endGame(updatedScore, false)
        } else {
            usedWords.add(currentWord)
            wordInProgress = chooseWordAndDisplay()
            _uiState.update { currentState ->
                currentState.copy(
                    score = updatedScore,
                    numWrongGuessesPerWord = 0,
                    isBulbButtonEnabled = true,
                    isHintRequired = false,
                    isGuessedLetterWrong = false,
                    currentWordToGuess = wordInProgress,
                    wordDefinition = currentWord.wordDefinition
                )
            }
        }
    }

    private fun endGame(finalScore: Int, isGameLost : Boolean) {
        if (isGameLost) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = finalScore,
                    currentWordToGuess = "",
                    wordDefinition = "",
                    isGameOver = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = finalScore,
                    currentWordToGuess = "",
                    wordDefinition = "",
                    isGameEnded = true
                )
            }
        }
    }

    fun requireHint() {
        _uiState.update {currentState ->
            currentState.copy(
                isHintRequired = true,
                isBulbButtonEnabled = false
            )
        }
    }

    private fun chooseWordAndDisplay() : String {
        currentWord = words.random()
        if (usedWords.contains(currentWord)) {
            return chooseWordAndDisplay()
        } else {
            usedWords.add(currentWord)
            return displayChosenWord(currentWord.word)
        }
    }

    private fun displayChosenWord(word : String) : String {
        val sb = StringBuilder()
        for (letter in word) {
            sb.append("_ ")
        }
        sb.trim()
        return sb.toString()
    }
}