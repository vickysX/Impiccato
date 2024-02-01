package com.example.impiccato.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.impiccato.model.Game

@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var instance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    GameDatabase::class.java,
                    name = "game_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}