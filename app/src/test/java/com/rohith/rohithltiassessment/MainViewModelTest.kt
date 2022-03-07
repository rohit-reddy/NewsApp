package com.rohith.rohithltiassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rohith.rohithltiassessment.article.ArticlesViewModel
import com.rohith.rohithltiassessment.article.NewsApiStatus
import com.rohith.rohithltiassessment.network.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest
{
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testsuccess()= runBlockingTest{
        val viewModel = ArticlesViewModel()
        assertThat(viewModel.articles.value, `not`(emptyList()))
    }

    @Test
    fun callApi_setsResponse() = runBlockingTest {

        val articleViewModel = ArticlesViewModel()
        val observer = Observer<List<Source>> {}
        try {
            articleViewModel.articles.observeForever(observer)
            val value = articleViewModel.articles.value
            assertThat(value, not(emptyList()))
        } finally {
            articleViewModel.articles.removeObserver(observer)
        }
    }

    @Test
    fun callApi_checkStatus() = runBlockingTest {

        val articleViewModel = ArticlesViewModel()
        val observer = Observer<NewsApiStatus> {}
        try {
            articleViewModel.status.observeForever(observer)
            val value = articleViewModel.status.value
            assertThat(value, `is`(NewsApiStatus.LOADING))
        } finally {
            articleViewModel.status.removeObserver(observer)
        }
    }

}