package com.github.gibbrich.albums

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.gibbrich.albums.ui.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun no_data_label_displayed_on_MainActivity_start() {
        val context = InstrumentationRegistry.getTargetContext()
        onView(withText(context.getString(R.string.activity_main_no_data))).check(matches(isDisplayed()))
    }
}