package com.samples.test.data

import com.samples.test.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class GameManagerImpl @Inject constructor(private val gameRule: GameRule) : GameManager {
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

    override fun resetBoard() {
        boardFlow.value = Board.getNewBoardInstance()
    }

    override fun getGameStatus(cell: Cell): GameStatus {
        val board = boardFlow.value
        return when (gameRule.findTheWinner(cellList = board.cells, playerPickedCell = cell)) {
            null -> if (!board.isCellLeftToPick()) {
                GameDraw
            } else {
                GameOnGoing(nextPlayer = if (cell.state == XSelected) OPlayer else XPlayer)
            }
            else -> GameWin(if (cell.state == XSelected) XPlayer else OPlayer)
        }
    }

    private fun getCurrentPlayer(playerType: PlayerType) = when (playerType) {
        OPlayer -> OSelected
        XPlayer -> XSelected
    }

}