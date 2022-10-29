package com.rakha.basicproject.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rakha.basicproject.R
import com.rakha.basicproject.presentation.viewholder.MoviesViewHolder
import com.rakha.basicproject.waitFor
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *   Created By rakha
 *   29/10/22
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MoviesScreenTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayMovies() {
        onView(isRoot()).perform(waitFor(2000))
        onView(withId(R.id.tv_page_title)).check(matches(isDisplayed()))
    }

    @Test
    fun clickMovieItem() {
        onView(isRoot()).perform(waitFor(2000))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesViewHolder>(
                1,
                click()
            )
        )
    }

    @Test
    fun clickFavoriteMovie() {
        onView(isRoot()).perform(waitFor(2000))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.iv_favorite)).perform(click())
    }

    @Test
    fun goToFavoritePage() {
        onView(isRoot()).perform(waitFor(2000))
        onView(withId(R.id.iv_page_action)).perform(click())
    }
}