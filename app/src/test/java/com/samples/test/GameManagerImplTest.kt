package com.samples.test

import com.samples.model.*
import com.samples.test.common.BOARD_SIZE
import com.samples.test.data.GameManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GameManagerImplTest {
    private val gameManager = GameManagerImpl()

    @Test
    fun getInitialBoard_willReturnBoardWithAllTheCellsInUnSelectedState() = runBlockingTest {
        val expectedCellSize: Int = BOARD_SIZE * BOARD_SIZE

        val board = getBoard()

        val actualCellSize =  getCellSize(board.cells, UnSelected)
        assertEquals(expectedCellSize, actualCellSize)
    }

    @Test
    fun cellSelection_updateCellStateWhenACellIsSelectedByAPlayer() = runBlockingTest {
        val cellXSelected = Cell(0, 0)
        val cellOSelected = Cell(0, 1)

        gameManager.cellSelection(cellXSelected, XPlayer)
        gameManager.cellSelection(cellOSelected, OPlayer)

        with(getBoard()) {
            val unSelectedCellSize = getCellSize(cells, UnSelected)
            val xSelectedCellSize = getCellSize(cells, XSelected)
            val oSelectedCellSize = getCellSize(cells, OSelected)
            assertEquals(7, unSelectedCellSize)
            assertEquals(1, xSelectedCellSize)
            assertEquals(1, oSelectedCellSize)
        }
    }

    @Test
    fun cellSelection_willBeImmutableIfTheCellIsAlreadySelectedByAPlayer() = runBlockingTest {
        val cellSelected = Cell(0, 0)

        gameManager.cellSelection(cellSelected, XPlayer)
        gameManager.cellSelection(cellSelected, OPlayer)

        val board = getBoard()
        val unSelectedCellSize = getCellSize(board.cells, UnSelected)
        val xSelectedCellSize = getCellSize(board.cells, XSelected)
        assertEquals(8, unSelectedCellSize)
        assertEquals(1, xSelectedCellSize)
    }

    private fun getCellSize(cells: List<Cell>, cellState: CellState) =
        cells.filter { it.state == cellState }.size

    private suspend fun getBoard() = gameManager.getBoard().first()
}