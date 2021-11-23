package com.samples.test.view.game

import android.content.Context
import android.widget.FrameLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.samples.test.R
import com.samples.test.common.BOARD_SIZE
import com.samples.test.utils.*
import com.samples.test.view.game.adapter.GameAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class GameBoardFragmentTest {

    private var appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val totalCell = BOARD_SIZE * BOARD_SIZE

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyInitialViews_gameBoardFragmentShouldDisplayTheViewsWithCorrectText() {
        val initialPlayerName = appContext.getString(R.string.x_player)

        launchFragment()

        onView(withId(R.id.textViewHeader)).withText(R.string.app_name)
        onView(withId(R.id.buttonRestartGame)).withText(R.string.restart_game)
        onView(withId(R.id.buttonRestartGame)).isClickable()
        onView(withId(R.id.textViewGameStatus)).check(
            ViewAssertions.matches(
                withSubstring(appContext.getString(R.string.player_turn, initialPlayerName))
            )
        )
    }

    @Test
    fun recyclerView_displayedWithExpectedItemCount() {
        launchFragment()

        onView(withId(R.id.gameRecyclerView)).isDisplayed()
        onView(withId(R.id.gameRecyclerView)).apply {
            ViewMatchers.isDisplayed()
            check(ViewAssertions.matches(ViewMatchers.hasChildCount(totalCell)))
        }
    }

    @Test
    fun recyclerViewCellSelection_whenXPlayerSelectsAllTheCellsInTheFirstColumnThenDisplayXPlayerAsTheWinner() {
        launchFragment()

        onRecyclerViewItemClick(0)
        onRecyclerViewItemClick(4)
        onRecyclerViewItemClick(3)
        onRecyclerViewItemClick(7)
        onRecyclerViewItemClick(6)

        val xPlayerName = appContext.getString(R.string.x_player)
        val winnerTitle = appContext.getString(R.string.winner, xPlayerName)
        verifyWinner(winnerTitle)
    }

    @Test
    fun recyclerViewCellSelection_whenAllTheCellsAreSelectedThenDisplayGameDraw() {
        launchFragment()

        onRecyclerViewItemClick(0)
        onRecyclerViewItemClick(3)
        onRecyclerViewItemClick(6)
        onRecyclerViewItemClick(4)
        onRecyclerViewItemClick(1)
        onRecyclerViewItemClick(7)
        onRecyclerViewItemClick(5)
        onRecyclerViewItemClick(2)
        onRecyclerViewItemClick(8)

        onView(withId(R.id.textViewGameStatus)).apply { withText(R.string.game_draw) }
    }


    @Test
    fun restartButton_whenCellsArePickedByThePlayerAndResetClickRevertTheGameStatusToInitial() {
        launchFragment()

        onRecyclerViewItemClick(0)
        onRecyclerViewItemClick(3)
        onView(withId(R.id.buttonRestartGame)).perform(ViewActions.click())

        val initialPlayerName = appContext.getString(R.string.x_player)
        onView(withId(R.id.textViewGameStatus)).check(
            ViewAssertions.matches(withSubstring(appContext.getString(R.string.player_turn,
                initialPlayerName)))
        )
    }

    private fun onRecyclerViewItemClick(index: Int) {
        onView(withId(R.id.gameRecyclerView)).apply {
            perform(
                RecyclerViewActions.actionOnItemAtPosition<GameAdapter.MyViewHolder>(
                    index,
                    recyclerChildAction<FrameLayout>(R.id.cellItemView) { callOnClick() }
                )
            )
        }
    }

    private fun verifyWinner(winnerTitle: String) {
        onView(withId(R.id.textViewGameStatus)).apply {
            check(ViewAssertions.matches(withSubstring(winnerTitle)))
        }
    }

    private fun launchFragment() =
        launchFragmentInHiltContainer<GameBoardFragment>(themeResId = R.style.AppTheme)

}