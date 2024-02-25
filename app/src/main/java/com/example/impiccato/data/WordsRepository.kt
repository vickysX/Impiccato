package com.example.impiccato.data

import com.example.impiccato.model.DifficultyLevel
import com.example.impiccato.model.Word

interface WordsRepository {
    fun chooseWord(currentLevel: DifficultyLevel): Word
}