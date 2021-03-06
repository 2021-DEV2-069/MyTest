package com.samples.test.common

import com.samples.test.model.Cell
import com.samples.test.model.CellState

val cleanBoardCells = listOf(
    Cell(0, 0),
    Cell(0, 1),
    Cell(0, 2),
    Cell(1, 0),
    Cell(1, 1),
    Cell(1, 2),
    Cell(2, 0),
    Cell(2, 1),
    Cell(2, 2)
)

fun getCellSize(cells: List<Cell>, cellState: CellState) =
    cells.filter { it.state == cellState }.size