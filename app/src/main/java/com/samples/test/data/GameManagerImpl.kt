package com.samples.test.data

import com.samples.model.*
import kotlinx.coroutines.flow.MutableStateFlow

class GameManagerImpl : GameManager {
    private var boardFlow: MutableStateFlow<Board> = MutableStateFlow(Board.getNewBoardInstance())

    override fun getBoard() = boardFlow

    override fun cellSelection(playerPickedCell: Cell, playerType: PlayerType): Cell? {
        val cells = boardFlow.value.cells
        val selectedCell: Cell? =
            cells.find { it.isCellAValidSelection(playerPickedCell) }
        selectedCell?.state = getCurrentPlayer(playerType)
        boardFlow.value = Board(cells)
        return selectedCell
    }

    private fun getCurrentPlayer(playerType: PlayerType) = when (playerType) {
        OPlayer -> OSelected
        XPlayer -> XSelected
    }

}