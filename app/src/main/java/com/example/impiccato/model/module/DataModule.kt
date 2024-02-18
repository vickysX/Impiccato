package com.example.impiccato.model.module

import com.example.impiccato.data.GameRepository
import com.example.impiccato.data.OfflineGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun provideGameRepository(
        gameRepository: OfflineGameRepository
    ) : GameRepository

}