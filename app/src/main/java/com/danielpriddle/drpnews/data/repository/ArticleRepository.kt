package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Result
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(): Flow<Result<List<Article>>>
    suspend fun searchArticles(searchString: String): List<Article>
}