package com.example.impiccato.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DifficultyLevel {
    @SerialName("easy") Easy,
    @SerialName("medium") Medium,
    @SerialName("difficult") Difficult
}