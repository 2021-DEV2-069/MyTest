package com.samples.test.model

import com.samples.test.common.BOARD_SIZE

class Board(val cells: List<Cell>) {
    fun isCellLeftToPick() = cells.any { it.state == UnSelected }

    companion object {

        fun getNewBoardInstance(): Board {
            return Board(getUnSelectedCellList())
        }

        private fun getUnSelectedCellList(): MutableList<Cell> {
            val cellList = mutableListOf<Cell>()
            val totalCell = (BOARD_SIZE * BOARD_SIZE) - 1
            for (index in 0..totalCell)
                cellList.add(Cell(index / BOARD_SIZE, index % BOARD_SIZE))
            return cellList
        }
    }
}