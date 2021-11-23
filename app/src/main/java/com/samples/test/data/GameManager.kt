package com.samples.test.data

import com.samples.test.model.Board
import com.samples.test.model.Cell
import com.samples.test.model.GameStatus
import com.samples.test.model.PlayerType
import kotlinx.coroutines.flow.Flow

interface GameManager {
    fun getBoard(): Flow<Board>
    fun cellSelection(playerPickedCell: Cell, playerType: PlayerType): Cell?
    fun resetBoard()
    fun getGameStatus(cell: Cell): GameStatus
}