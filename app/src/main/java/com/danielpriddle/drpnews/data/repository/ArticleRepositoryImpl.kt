package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.database.entities.relations.ArticleAndSource
import com.danielpriddle.drpnews.data.networking.Failure
import com.danielpriddle.drpnews.data.networking.Result
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.services.APINewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * ArticleRepository
 *
 * This repository class acts as a facilitator for retrieving data in the API layer and storing the
 * data in the database. For now, it just calls the APINewsService to retrieve data, but data
 * storage will be built out in the near future :)
 * @author Dan Priddle
 */

class ArticleRepositoryImpl(
    private val newsService: APINewsService,
    private val articleDao: ArticleDao,
) : ArticleRepository {

    override suspend fun getArticles(): Result<List<Article>> {
        return when (val remoteArticlesResult = newsService.getTopHeadlines()) {
            is Success -> {
                val remoteArticles =
                    remoteArticlesResult.data.map { article -> ArticleEntity.fromModel(article) }
                articleDao.addArticles(remoteArticles)
                val localArticles = articleDao.getArticles()
                    .map { articleAndSource -> ArticleAndSource.toModel(articleAndSource) }
                Success(localArticles)
            }
            is Failure -> {
                println(remoteArticlesResult.error)
                Failure(remoteArticlesResult.error)
            }
        }
    }

    override suspend fun searchArticles(searchString: String): List<ArticleAndSource> {
        return articleDao.searchArticles(searchString)
    }

}