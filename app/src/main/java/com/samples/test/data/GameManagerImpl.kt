package com.samples.test.data

import com.samples.model.Board
import kotlinx.coroutines.flow.MutableStateFlow

class GameManagerImpl : GameManager {
    private var boardFlow: MutableStateFlow<Board> = MutableStateFlow(Board.getNewBoardInstance())

    override fun getBoard() = boardFlow

}