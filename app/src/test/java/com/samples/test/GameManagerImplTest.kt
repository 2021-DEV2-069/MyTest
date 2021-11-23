package com.samples.test

import com.samples.model.UnSelected
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

        val board = gameManager.getBoard().first()

        val actualCellSize = board.cells.filter { it.state == UnSelected }.size
        assertEquals(expectedCellSize, actualCellSize)
    }

}