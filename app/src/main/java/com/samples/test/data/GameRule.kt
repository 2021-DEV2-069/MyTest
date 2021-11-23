package com.samples.test.data

import com.samples.model.*

class GameRule {

    fun findTheWinner(cellList: List<Cell>, playerPickedCell: Cell): PlayerType? {
        filterUnSelectedCell(cellList).forEach { unSelectedCell ->
            if (isThePlayerPickedCell(unSelectedCell, playerPickedCell) &&
                isWinnerAvailable(cellList, playerPickedCell)
            ) {
                return playerType(unSelectedCell.state)
            }
        }
        return null
    }

    private fun filterUnSelectedCell(cellList: List<Cell>) =
        cellList.filterNot { it.isCellUnSelected }

    private fun isThePlayerPickedCell(unSelectedCell: Cell, playerPickedCell: Cell): Boolean =
        unSelectedCell.isSameRowAndColumn(playerPickedCell.row, playerPickedCell.column)

    private fun isWinnerAvailable(cellList: List<Cell>, playerPickedCell: Cell): Boolean =
        isPlayerSelectedAllCellsInARow(cellList, playerPickedCell)
                || isPlayerSelectedAllTheCellsInAColumn(cellList, playerPickedCell)

    private fun isPlayerSelectedAllCellsInARow(cells: List<Cell>, playerPickedCell: Cell): Boolean {
        val selectedCellsInRow =
            cells.filter { it.row == playerPickedCell.row && playerPickedCell.state == it.state }
        return selectedCellsInRow.size > 2
    }

    private fun isPlayerSelectedAllTheCellsInAColumn(
        cells: List<Cell>,
        playerPickedCell: Cell,
    ): Boolean {
        val selectedCellsInColumn = cells.filter {
            it.column == playerPickedCell.column && it.isSameCellState(playerPickedCell)
        }
        return selectedCellsInColumn.size > 2
    }

    private fun playerType(state: CellState) = when (state) {
        OSelected -> OPlayer
        XSelected -> XPlayer
        else -> null
    }
}