package com.example.impiccato.model

data class Word(
    val id: Int = 0,
    val entry : String,
    val definition : String,
    val definitionSource: String? = "",
    val level: DifficultyLevel? = null
)

enum class DifficultyLevel(level: String) {
    Easy("easy"),
    Medium("medium"),
    Difficult("difficult")
}