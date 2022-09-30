package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Failure
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.services.APINewsService

/**
 * ArticleRepository
 *
 * This repository class acts as a facilitator for retrieving data in the API layer and storing the
 * data in the database. For now, it just calls the APINewsService to retrieve data, but data
 * storage will be built out in the near future :)
 * @author Dan Priddle
 */

class ArticleRepository(private val newsService: APINewsService) {
    suspend fun getArticles(): Pair<List<Article>, String?> {
        return when (val result = newsService.getTopHeadlines()) {
            is Success -> {
                Pair(result.data, null)
            }
            is Failure -> {
                Pair(emptyList(), result.error)
            }
        }
    }
}