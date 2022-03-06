
package com.rohith.rohithltiassessment.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohith.rohithltiassessment.network.Source
import com.rohith.rohithltiassessment.network.NewsApi
import kotlinx.coroutines.launch

enum class NewsApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [ArticlesFragment].
 */
class ArticlesViewModel : ViewModel() {

    private val _status = MutableLiveData<NewsApiStatus>()

    val status: LiveData<NewsApiStatus>
        get() = _status

    private val _articles = MutableLiveData<List<Source>>()

    val articles: LiveData<List<Source>>
        get() = _articles

    private val _navigateToSelectedArticle = MutableLiveData<Source>()
    val navigateToSelectedArticle: LiveData<Source>
        get() = _navigateToSelectedArticle

    /**
     * Call getLatestNews() on init so we can display status immediately.
     */
    init {
       getLatestNews()
    }

    /**
     * Sets the value of the status LiveData to the News API status.
     */
    private fun getLatestNews() {
        viewModelScope.launch {
            _status.value = NewsApiStatus.LOADING
            try {
                _articles.value = NewsApi.RETROFIT_SERVICE.getNewsArticle().sources
                _status.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NewsApiStatus.ERROR
                _articles.value = ArrayList()
            }
        }
    }

    fun displayArticleDetails(source: Source) {
        _navigateToSelectedArticle.value = source
    }

    fun displayArticleDetailsComplete() {
        _navigateToSelectedArticle.value = null
    }

}
