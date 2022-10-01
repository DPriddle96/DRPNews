package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.*
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.utils.State
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

class ArticleListViewModel() : ViewModel() {

    private val articleRepository = ArticleRepository()

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleListViewModel() as T
        }
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        //set the loading state on init and start getting data
        _state.value = State.Loading
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            //ArticleRepository returns a Pair, a list of Articles, and an error string if an error occurred.
            val articles = articleRepository.getArticles().first
            val error = articleRepository.getArticles().second
            if (error != null) {
                _state.postValue(State.Error(error))
            }
            _state.postValue(State.Ready(articles))
        }
    }
}