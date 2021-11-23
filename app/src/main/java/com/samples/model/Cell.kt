package com.samples.model

data class Cell(val column: Int, val row: Int, var state: CellState = UnSelected) {

    fun isCellAValidSelection(playerPickedCell: Cell) =
        row == playerPickedCell.row && column == playerPickedCell.column && state == UnSelected

}