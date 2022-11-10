package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.*
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.data.models.Failure
import com.danielpriddle.drpnews.data.models.LocalSuccess
import com.danielpriddle.drpnews.data.models.RemoteSuccess
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.utils.Logger
import com.danielpriddle.drpnews.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ArticleListViewModel
 *
 * This ViewModel class exposes properties for the ArticleListFragment to observe and respond to.
 * It uses the ArticleRepository to retrieve data and expose it to the UI via LiveData. This also
 * helps handle the loading state for when data is being retrieved and the error state for when
 * data retrieval fails.
 * @author Dan Priddle
 */

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel(), Logger {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    private val _toastMessage = MutableSharedFlow<Int>()
    val toastMessage = _toastMessage.asSharedFlow()

    init {
        //set the loading state on init and start getting data
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(IO) {
            articleRepository.getArticles().collect { result ->
                when (result) {
                    is LocalSuccess -> {
                        _state.value = ViewState.Ready(result)
                        _toastMessage.emit(R.string.local_success_toast)
                    }
                    is RemoteSuccess -> {
                        _state.value = ViewState.Ready(result)
                        _toastMessage.emit(R.string.remote_success_toast)
                    }
                    is Failure -> {
                        _state.value = ViewState.Error(result.error)
                    }
                    else -> {}
                }
            }
        }

    }


}