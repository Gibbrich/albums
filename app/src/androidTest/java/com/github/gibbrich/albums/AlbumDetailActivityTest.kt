package com.github.gibbrich.albums

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.gibbrich.albums.ui.activity.AlbumDetailActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AlbumDetailActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(AlbumDetailActivity::class.java)


}