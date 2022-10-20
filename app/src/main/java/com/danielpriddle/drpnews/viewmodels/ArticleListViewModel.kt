package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.*
import com.danielpriddle.drpnews.data.networking.Failure
import com.danielpriddle.drpnews.data.networking.LocalSuccess
import com.danielpriddle.drpnews.data.networking.RemoteSuccess
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.utils.Logger
import com.danielpriddle.drpnews.utils.State
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * ArticleListViewModel
 *
 * This ViewModel class exposes properties for the ArticleListFragment to observe and respond to.
 * It uses the ArticleRepository to retrieve data and expose it to the UI via LiveData. This also
 * helps handle the loading state for when data is being retrieved and the error state for when
 * data retrieval fails.
 * @author Dan Priddle
 */

class ArticleListViewModel(
    private val articleRepository: ArticleRepository,
) : ViewModel(), Logger {

    class Factory(
        private val articleRepository: ArticleRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleListViewModel(articleRepository) as T
        }
    }

    //private val TAG = javaClass.simpleName

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        //set the loading state on init and start getting data
        _state.value = State.Loading
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(IO) {
            articleRepository.getArticles()
                .onEach { result ->
                    logDebug("ArticleViewModel call to repository result: ${result.javaClass.simpleName}")
                    when (result) {
                        is LocalSuccess -> {
                            _state.postValue(State.Ready(result))
                        }
                        is RemoteSuccess -> {
                            _state.postValue(State.Ready(result))
                        }
                        is Failure -> {
                            _state.postValue(State.Error(result.error))
                        }
                        else -> {}
                    }
                }
                .collect()
        }

    }

    fun searchArticles(searchString: String) {
        viewModelScope.launch(IO) {
            val filteredArticles = articleRepository.searchArticles("%$searchString%")
            _state.postValue(State.Ready(Success(filteredArticles)))
        }
    }
}