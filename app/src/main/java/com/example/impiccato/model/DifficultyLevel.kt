package com.example.impiccato.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DifficultyLevel(val scoreIncrement: Int) {
    @SerialName("easy") Easy(scoreIncrement = 1),
    @SerialName("medium") Medium(scoreIncrement = 2),
    @SerialName("difficult") Difficult(scoreIncrement = 2)
}