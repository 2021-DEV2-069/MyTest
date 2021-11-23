package com.samples.test.data

import com.samples.model.Cell
import com.samples.model.OSelected
import com.samples.model.XPlayer
import com.samples.model.XSelected
import com.samples.test.common.cleanBoardCells
import org.junit.Assert.assertEquals
import org.junit.Test

class GameRuleTest {
    private val gameRuleRequest = GameRule()

    @Test
    fun checkForWinner_returnAWinnerIfAPlayerPicksAllThe3CellsInARows() {
        val cellListWithXPlayerAsWinner = cleanBoardCells.toMutableList().apply {
            set(0, Cell(0, 0, XSelected))
            set(3, Cell(0, 1, OSelected))
            set(1, Cell(1, 0, XSelected))
            set(4, Cell(1, 1, OSelected))
            set(4, Cell(1, 1, OSelected))
            set(6, Cell(2, 0, XSelected))
        }
        val recentSelectedCell = Cell(2, 0, XSelected)

        val player = gameRuleRequest.findTheWinner(cellListWithXPlayerAsWinner, recentSelectedCell)

        assertEquals(XPlayer, player)
    }
}