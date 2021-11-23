package com.samples.test.view.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.test.data.GameManager
import com.samples.test.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val gameManager: GameManager) : ViewModel() {

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

    fun onCellClick(cell: Cell) {
        val gameStatus = _gameStatus.value
        if (gameStatus is GameOnGoing) {
            gameManager.cellSelection(cell, gameStatus.nextPlayer)?.let { updatedCell ->
                updateGameStatus(updatedCell)
            }
        }
    }

    private fun updateGameStatus(updatedCell: Cell) {
        viewModelScope.launch {
            _gameStatus.value = gameManager.getGameStatus(updatedCell)
        }
    }

    fun onRestartClicked() {
        gameManager.resetBoard()
        _gameStatus.value = GameOnGoing(XPlayer)
    }

}


