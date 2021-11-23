package com.samples.test.data

import com.samples.model.*

class GameRule {

    fun findTheWinner(cellList: List<Cell>, playerPickedCell: Cell): PlayerType? {
        filterUnSelectedCell(cellList).forEach { unSelectedCell ->
            if (isThePlayerPickedCell(unSelectedCell, playerPickedCell) &&
                isPlayerSelectedAllCellsInARow(cellList, playerPickedCell)
            ) {
                return playerType(unSelectedCell.state)
            }
        }
        return null
    }

    private fun filterUnSelectedCell(cellList: List<Cell>) =
        cellList.filterNot { it.state == UnSelected }

    private fun isThePlayerPickedCell(unSelectedCell: Cell, playerPickedCell: Cell): Boolean =
        unSelectedCell.row == playerPickedCell.row && unSelectedCell.column == playerPickedCell.column

    private fun isPlayerSelectedAllCellsInARow(cells: List<Cell>, playerPickedCell: Cell): Boolean {
        val selectedCellsInRow =
            cells.filter { it.row == playerPickedCell.row && playerPickedCell.state == it.state }
        return selectedCellsInRow.size > 2
    }

    private fun playerType(state: CellState) = when (state) {
        OSelected -> OPlayer
        XSelected -> XPlayer
        else -> null
    }
}