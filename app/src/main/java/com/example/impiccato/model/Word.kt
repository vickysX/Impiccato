package com.example.impiccato.model

import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val id: Int = 0,
    val entry : String,
    val definition : String,
    val definitionSource: String? = "",
    val level: DifficultyLevel? = null
)