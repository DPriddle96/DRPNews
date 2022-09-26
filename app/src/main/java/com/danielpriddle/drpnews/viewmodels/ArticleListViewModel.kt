package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.*
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    class Factory(
        private val articleRepository: ArticleRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleListViewModel(articleRepository) as T
        }
    }

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.value = true
        getArticles()

    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val articles = articleRepository.getArticles().first
            val error = articleRepository.getArticles().second
            if (error != null) {
                _error.postValue(error)
            }
            _articles.postValue(articles)
            withContext(Dispatchers.Main) {
                _isLoading.value = false
            }

        }
    }
}