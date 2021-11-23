package com.samples.test.utils

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers

internal fun ViewInteraction.isClickable() = check(matches(ViewMatchers.isClickable()))

internal fun ViewInteraction.withText(stringRes: Int) =
    check(matches(ViewMatchers.withText(stringRes)))
