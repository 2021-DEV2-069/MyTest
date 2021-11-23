package com.samples.test.view.game

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.samples.test.R
import com.samples.test.common.BOARD_SIZE
import com.samples.test.utils.isClickable
import com.samples.test.utils.isDisplayed
import com.samples.test.utils.launchFragmentInHiltContainer
import com.samples.test.utils.withText
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

    private fun launchFragment() =
        launchFragmentInHiltContainer<GameBoardFragment>(themeResId = R.style.AppTheme)

}