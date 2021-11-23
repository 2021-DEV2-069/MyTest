package com.samples.test.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.test.data.GameManager
import com.samples.test.model.Board
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel constructor(private val gameManager: GameManager) : ViewModel() {

    private val _boardState: MutableLiveData<Board> = MutableLiveData()
    val boardState: MutableLiveData<Board> = _boardState

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


