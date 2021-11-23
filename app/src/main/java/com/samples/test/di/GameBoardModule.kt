package com.samples.test.di

import com.samples.test.data.GameManager
import com.samples.test.data.GameManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GameBoardModule {
    @Binds
    abstract fun bindGameManager(gameManager: GameManagerImpl): GameManager
}