package com.example.impiccato.data

import android.content.Context
import com.example.impiccato.model.DifficultyLevel
import com.example.impiccato.model.Word
import com.example.impiccato.utils.readJSONFromAssets
import kotlinx.serialization.json.Json
import javax.inject.Inject

class OfflineWordsRepository @Inject constructor(
    private val context: Context
) : WordsRepository {

    private var allWords: List<Word>

    override fun chooseWord(currentLevel: DifficultyLevel): Word {
        val wordsAtCurrentLevel = allWords.filter { word ->
            word.level == currentLevel
        }
        return wordsAtCurrentLevel.random()
    }

    init {
        val jsonString = readJSONFromAssets(context = context, path = "words.json")
        allWords = Json.decodeFromString<List<Word>>(jsonString)
    }
}