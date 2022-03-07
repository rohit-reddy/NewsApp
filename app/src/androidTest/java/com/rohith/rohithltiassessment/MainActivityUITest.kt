package com.rohith.rohithltiassessment

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.rohith.rohithltiassessment.article.ArticlesFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityUITest {

    @ExperimentalCoroutinesApi
    @Test
    fun clickTask_navigateToDetailFragmentOne() = runBlockingTest {

        val scenario = launchFragmentInContainer<ArticlesFragment>(Bundle(), R.style.Theme_RohithLTIAssessment)
        val navController = mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        onView(withId(R.id.news_feed))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0),
                ViewActions.click()
            ).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
    }
}