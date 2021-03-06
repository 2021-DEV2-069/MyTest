package com.samples.test.data

import com.samples.test.common.cleanBoardCells
import com.samples.test.model.*
import org.junit.Assert.assertEquals
import org.junit.Test

class GameRuleTest {
    private val gameRuleRequest = GameRule()

    @Test
    fun findTheWinner_returnAWinnerIfAPlayerPicksAllThe3CellsInARows() {
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

    @Test
    fun findTheWinner_returnAWinnerIfAPlayerPicksAllThe3CellsInAColumn() {
        val cellListWithOPlayerAsWinner = cleanBoardCells.toMutableList().apply {
            set(4, Cell(1, 1, XSelected))
            set(0, Cell(0, 0, OSelected))
            set(7, Cell(1, 2, XSelected))
            set(1, Cell(0, 1, OSelected))
            set(8, Cell(2, 2, XSelected))
            set(2, Cell(0, 2, OSelected))
        }
        val recentSelectedCell = Cell(0, 2, OSelected)

        val player =
            gameRuleRequest.findTheWinner(cellListWithOPlayerAsWinner, recentSelectedCell)

        assertEquals(OPlayer, player)
    }

    @Test
    fun findTheWinner_returnAWinnerIfAPlayerPicksAllThe3CellsInRightDiagonal() {
        val cellListWithXPlayerAsWinner = cleanBoardCells.toMutableList().apply {
            set(0, Cell(0, 0, XSelected))
            set(1, Cell(1, 0, OSelected))
            set(4, Cell(1, 1, XSelected))
            set(3, Cell(0, 1, OSelected))
            set(8, Cell(2, 2, XSelected))
        }
        val recentSelectedCell = Cell(2, 2, XSelected)

        val player =
            gameRuleRequest.findTheWinner(cellListWithXPlayerAsWinner, recentSelectedCell)

        assertEquals(XPlayer, player)
    }

    @Test
    fun findTheWinner_returnAWinnerIfAPlayerPicksAllThe3CellsInLeftDiagonal() {
        val cellListWithXPlayerAsWinner = cleanBoardCells.toMutableList().apply {
            set(0, Cell(2, 0, XSelected))
            set(1, Cell(1, 0, OSelected))
            set(4, Cell(1, 1, XSelected))
            set(3, Cell(0, 1, OSelected))
            set(8, Cell(0, 2, XSelected))
        }
        val recentSelectedCell = Cell(0, 2, XSelected)

        val player =
            gameRuleRequest.findTheWinner(cellListWithXPlayerAsWinner, recentSelectedCell)

        assertEquals(XPlayer, player)
    }
}