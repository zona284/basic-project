package com.rakha.basicproject.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rakha.basicproject.presentation.splash.SplashFragment
import com.rakha.basicproject.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

/**
 *   Created By rakha
 *   29/10/22
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashScreenTest {

    @Test
    fun displayTextSplashScreen() {
        // Create a TestNavHostController
        val navController = mock(NavController::class.java)

        val titleScenario = launchFragmentInContainer<SplashFragment>()

        titleScenario.onFragment { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    navController.setGraph(R.navigation.main_graph)
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }

        onView(withId(R.id.tv_splash)).check(matches(isDisplayed()))
    }
}