package com.example.impiccato.model.module

import android.content.Context
import com.example.impiccato.data.GameDao
import com.example.impiccato.data.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : GameDatabase {
        return GameDatabase.getDatabase(context)
    }

    @Provides
    fun provideDao(gameDatabase: GameDatabase) : GameDao {
        return gameDatabase.gameDao()
    }

}