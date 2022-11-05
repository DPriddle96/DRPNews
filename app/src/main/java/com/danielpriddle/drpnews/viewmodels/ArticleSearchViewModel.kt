package com.danielpriddle.drpnews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleSearchViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel(), Logger {

    private val _articles = MutableStateFlow<List<Article>>(mutableListOf())
    val articles: StateFlow<List<Article>> = _articles

    fun searchArticles(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (searchString.isEmpty()) {
                _articles.value = mutableListOf()
            } else {
                val filteredArticles = articleRepository.searchArticles("%$searchString%")
                _articles.value = filteredArticles
            }

        }
    }
}