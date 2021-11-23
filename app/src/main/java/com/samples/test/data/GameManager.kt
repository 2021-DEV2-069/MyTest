package com.samples.test.data

import com.samples.model.Board
import kotlinx.coroutines.flow.Flow

interface GameManager {
    fun getBoard(): Flow<Board>
}