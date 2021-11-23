package com.samples.test.data

import com.samples.test.common.BOARD_SIZE
import com.samples.test.model.*

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
                || isPlayerSelectedAllCellsInRightDiagonal(cellList, playerPickedCell)
                || isPlayerSelectedAllCellsInLeftDiagonal(cellList, playerPickedCell)

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

    private fun isPlayerSelectedAllCellsInRightDiagonal(
        cells: List<Cell>,
        playerPickedCell: Cell,
    ): Boolean {
        val selectedCellsInRightDiagonal = mutableListOf<Cell>()
        for (index in 0 until BOARD_SIZE) {
            findDiagonalCellWithSameCellState(cells,
                playerPickedCell,
                index)?.let { cellInDiagonal ->
                selectedCellsInRightDiagonal.add(cellInDiagonal)
            }
        }
        return selectedCellsInRightDiagonal.size > 2
    }

    private fun isPlayerSelectedAllCellsInLeftDiagonal(
        cells: List<Cell>,
        playerPickedCell: Cell,
    ): Boolean {
        val selectedCellsInLeftDiagonal = mutableListOf<Cell>()
        var columnIndex = 2
        for (rowIndex in 0 until BOARD_SIZE) {
            findDiagonalCellWithSameCellState(cells, playerPickedCell, rowIndex, columnIndex)
                ?.let { cellInDiagonal -> selectedCellsInLeftDiagonal.add(cellInDiagonal) }
            columnIndex--
        }
        return selectedCellsInLeftDiagonal.size > 2
    }

    private fun findDiagonalCellWithSameCellState(
        cells: List<Cell>, playerPickedCell: Cell, rowIndex: Int, columnIndex: Int = rowIndex,
    ): Cell? = cells.find {
        it.isSameRowAndColumn(rowIndex, columnIndex) && it.isSameCellState(playerPickedCell)
    }

    private fun playerType(state: CellState) = when (state) {
        OSelected -> OPlayer
        XSelected -> XPlayer
        else -> null
    }
}