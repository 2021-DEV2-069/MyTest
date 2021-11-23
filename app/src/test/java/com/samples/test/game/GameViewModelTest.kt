package com.samples.test.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samples.mytest.common.MainCoroutineRule
import com.samples.mytest.common.getOrAwaitValue
import com.samples.test.common.cleanBoardCells
import com.samples.test.data.GameManager
import com.samples.test.model.Board
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val gameManager = mockk<GameManager>(relaxed = true)
    private lateinit var gameViewModel: GameViewModel

    private fun createGameViewModel() {
        gameViewModel = GameViewModel(gameManager)
    }

    @Test
    fun initialBoardState_shouldHoldABoardWithAllTheCellsHavingUnSelectedState() = runBlockingTest {
        //board with cells in unselected state
        val expectedBoard = Board(cleanBoardCells)
        coEvery { gameManager.getBoard() } returns flowOf(expectedBoard)
        createGameViewModel()

        val actualBoardState = gameViewModel.boardState.getOrAwaitValue()

        assertEquals(actualBoardState, expectedBoard)
    }
}