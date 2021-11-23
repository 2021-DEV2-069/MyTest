package com.samples.model

sealed interface CellState

object UnSelected : CellState
object XSelected : CellState
object OSelected : CellState