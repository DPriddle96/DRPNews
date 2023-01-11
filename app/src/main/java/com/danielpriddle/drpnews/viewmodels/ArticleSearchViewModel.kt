package com.danielpriddle.drpnews.viewmodels

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleSearchViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : Logger {

    private val _articles = MutableStateFlow<List<Article>>(mutableListOf())
    val articles: StateFlow<List<Article>> = _articles

    fun searchArticles(searchString: String) {
        CoroutineScope(IO).launch {
            if (searchString.isEmpty()) {
                _articles.value = mutableListOf()
            } else {
                val filteredArticles = articleRepository.searchArticles("%$searchString%")
                _articles.value = filteredArticles
            }

        }
    }
}