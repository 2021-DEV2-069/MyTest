package com.samples.model

data class Cell(val column: Int, val row: Int, var state: CellState = UnSelected) {

    val isCellUnSelected: Boolean
        get() = state == UnSelected

    fun isCellAValidSelection(playerPickedCell: Cell) =
        isSameRowAndColumn(playerPickedCell.row, playerPickedCell.column) && isCellUnSelected

    fun isSameRowAndColumn(rowIndex: Int, columnIndex: Int) =
        row == rowIndex && column == columnIndex

    fun isSameCellState(playerPicked: Cell) = playerPicked.state == state
}