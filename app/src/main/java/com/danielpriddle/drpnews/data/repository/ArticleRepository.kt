package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Result
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
    suspend fun getArticles(): Result<List<Article>> {
        return newsService.getTopHeadlines()
    }
}