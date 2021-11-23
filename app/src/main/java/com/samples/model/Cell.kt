package com.samples.model

data class Cell(val column: Int, val row: Int, var state: CellState = UnSelected)