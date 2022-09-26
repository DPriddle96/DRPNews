package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.*
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ArticleListViewModel
 *
 * This ViewModel class exposes properties for the ArticleListFragment to observe and respond to.
 * It uses the ArticleRepository to retrieve data and expose it to the UI via LiveData. This also
 * helps handle the loading state for when data is being retrieved and the error state for when
 * data retrieval fails.
 * @author Dan Priddle
 */

class ArticleListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    class Factory(
        private val articleRepository: ArticleRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleListViewModel(articleRepository) as T
        }
    }

    //Articles state
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    //Error state
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    //Loading State
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        //set the loading state on init and start getting data
        _isLoading.value = true
        getArticles()

    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            //ArticleRepository returns a Pair, a list of Articles, and an error string if an error occurred.
            val articles = articleRepository.getArticles().first
            val error = articleRepository.getArticles().second
            if (error != null) {
                _error.postValue(error)
            }
            _articles.postValue(articles)

            //go back to the main thread and set loading state to false now that we're done
            withContext(Dispatchers.Main) {
                _isLoading.value = false
            }

        }
    }
}