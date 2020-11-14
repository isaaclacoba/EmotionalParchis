package com.multimediateam.parchisemocional.ui.ui.parchis

import android.os.Bundle
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import com.multimediateam.parchisemocional.R
import com.multimediateam.parchisemocional.ui.parchis.ParchisFragment
import com.multimediateam.parchisemocional.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Test


@SmallTest
@HiltAndroidTest
class ParchisFragmentTest {
    private val TAG: String = "ParchisFragmentTest"

    @Before
    fun setUp() {
        val bundle = Bundle()
        launchFragmentInHiltContainer<ParchisFragment>(bundle, R.style.AppTheme)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkCircleIsShownAfterTouchingParchis() {
        val xClick = 500f
        val yClick = 600f
        val clickAction: ViewAction = GeneralClickAction(Tap.SINGLE, CoordinatesProvider { view ->
            val screenPos = IntArray(2)
            view.getLocationOnScreen(screenPos)

            val screenX = screenPos[0] + xClick.toFloat()
            val screenY = screenPos[1] + xClick.toFloat()
            val coordinates = floatArrayOf(screenX, screenY)

            Log.d("TAG", "==> coordinates: $screenX $screenY x: $xClick y: $yClick ${screenPos.toString()}")
            coordinates

        }, Press.THUMB)

        onView(withId(R.id.parchis_emotional_iv)).check(matches(isDisplayed()))
        onView(withId(R.id.parchis_point_iv)).check(matches(
            ViewMatchers.withEffectiveVisibility(
                ViewMatchers.Visibility.INVISIBLE
            )
        ))
        onView(withId(R.id.parchis_emotional_iv)).perform(clickAction)
        onView(withId(R.id.parchis_point_iv)).check(matches(isDisplayed()))
    }
}