package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Failure
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.services.APINewsService

class ArticleRepository(private val newsService: APINewsService) {
    suspend fun getArticles(): Pair<List<Article>, String?> {
        return when (val result = newsService.getTopHeadlines()) {
            is Success -> {
                Pair(result.data, null)
            }
            is Failure -> {
                Pair(listOf(), result.error)
            }
        }
    }
}