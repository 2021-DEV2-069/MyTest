package com.samples.test

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.samples.test.view.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class MainActivityTest {

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun activityNavigation_initialScreenShouldBeGameFragment() {
        activityScenario = launchActivity()
        activityScenario.onActivity { navController.setGraph(R.navigation.nav_graph) }
        assertEquals(navController.currentDestination?.id, R.id.gameFragment)
    }
}