package com.samples.test.data

import com.samples.model.Board
import com.samples.model.Cell
import com.samples.model.PlayerType
import kotlinx.coroutines.flow.Flow

interface GameManager {
    fun getBoard(): Flow<Board>
    fun cellSelection(playerPickedCell: Cell, playerType: PlayerType): Cell?
}