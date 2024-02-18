package com.example.impiccato.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "game")
data class Game(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var score: Int = 0,
    var date: LocalDateTime = LocalDateTime.now(),
    var level: DifficultyLevel = DifficultyLevel.Easy
)
