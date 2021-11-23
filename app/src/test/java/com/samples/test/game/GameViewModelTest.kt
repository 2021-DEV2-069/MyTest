package com.samples.test.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samples.mytest.common.MainCoroutineRule
import com.samples.mytest.common.getOrAwaitValue
import com.samples.test.common.BOARD_SIZE
import com.samples.test.common.cleanBoardCells
import com.samples.test.common.getCellSize
import com.samples.test.data.GameManager
import com.samples.test.data.GameManagerImpl
import com.samples.test.model.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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

    @Test
    fun initialGameStatus_willBeGameOnGoingWithXPlayerAsNextPlayer() {
        createGameViewModel()

        val gameOnGoing = gameViewModel.gameStatus.getOrAwaitValue()

        Assert.assertTrue(gameOnGoing is GameOnGoing)
        Assert.assertTrue((gameOnGoing as? GameOnGoing)?.nextPlayer is XPlayer)
    }

    @Test
    fun onCellClick_verifyWhetherGameStatusUpdatedToGameWin() {
        createGameViewModel()
        val cell = Cell(0, 0)
        coEvery {
            gameManager.cellSelection(cell, XPlayer)
        } returns cell
        coEvery { gameManager.getGameStatus(cell) } returns GameWin(OPlayer)


        gameViewModel.onCellClick(cell)

        val gameWin = gameViewModel.gameStatus.getOrAwaitValue()
        Assert.assertTrue(gameWin is GameWin)
        Assert.assertTrue((gameWin as? GameWin)?.player is OPlayer)
    }

    @Test
    fun onRestartClicked_clearsTheGameToInitialState() {
        val expectedUnselectedCellSize = BOARD_SIZE * BOARD_SIZE
        val gameViewModel = GameViewModel(GameManagerImpl(mockk()))
        gameViewModel.boardState.value = Board(
            cleanBoardCells.toMutableList().apply { set(4, Cell(1, 1, XSelected)) }
        )
        gameViewModel.gameStatus.value = GameOnGoing(OPlayer)

        gameViewModel.onRestartClicked()

        val actualBoardState = gameViewModel.boardState.getOrAwaitValue()
        val gameOnGoing = gameViewModel.gameStatus.getOrAwaitValue()
        assertEquals(expectedUnselectedCellSize, getCellSize(actualBoardState.cells, UnSelected))
        assertTrue(gameOnGoing is GameOnGoing)
        assertTrue((gameOnGoing as? GameOnGoing)?.nextPlayer is XPlayer)
    }

}