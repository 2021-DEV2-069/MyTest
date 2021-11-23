package com.samples.test.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.test.data.GameManager
import com.samples.test.model.Board
import com.samples.test.model.GameOnGoing
import com.samples.test.model.GameStatus
import com.samples.test.model.XPlayer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel constructor(private val gameManager: GameManager) : ViewModel() {

    private val _boardState: MutableLiveData<Board> = MutableLiveData()
    val boardState: MutableLiveData<Board> = _boardState
    private val _gameStatus: MutableLiveData<GameStatus> = MutableLiveData(GameOnGoing(XPlayer))
    val gameStatus: MutableLiveData<GameStatus> = _gameStatus

    init {
        viewModelScope.launch {
            observeBoardUpdates()
        }
    }

    private suspend fun observeBoardUpdates() {
        gameManager
            .getBoard()
            .collect { _boardState.value = it }
    }
}


