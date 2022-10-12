package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.mappers.fromArticleModel
import com.danielpriddle.drpnews.data.mappers.fromSourceModel
import com.danielpriddle.drpnews.data.mappers.toArticleModel
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.Failure
import com.danielpriddle.drpnews.data.networking.Result
import com.danielpriddle.drpnews.data.networking.Success
import com.danielpriddle.drpnews.data.services.APINewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

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
    private val sourceDao: SourceDao,
) : ArticleRepository {

    override fun getArticles(): Flow<Result<List<Article>>> {
        return flow {
            val localArticles = articleDao.getArticles().map { articleAndSource ->
                toArticleModel(articleAndSource.article,
                    articleAndSource.source)
            }
            emit(Success(localArticles))

            try {
                when (val remoteArticlesResult = newsService.getTopHeadlines()) {
                    is Success -> {
                        val remoteArticles = remoteArticlesResult.data
                        emit(Success(remoteArticles))
                        if (remoteArticles.isNotEmpty()) {
                            remoteArticles.forEach { article ->
                                sourceDao.addSource(fromSourceModel(article.source))
                            }
                            articleDao.addArticles(remoteArticles.map { article ->
                                fromArticleModel(article)
                            })
                        }
                    }
                    is Failure -> {
                        println(remoteArticlesResult.error)
                        emit(Failure(remoteArticlesResult.error))
                    }
                }
            } catch (e: Exception) {
                emit(Failure(e.message!!))
            }
        }

    }

    override suspend fun searchArticles(searchString: String): List<Article> {
        return articleDao.searchArticles(searchString).map { articleAndSource ->
            toArticleModel(articleAndSource.article,
                articleAndSource.source)
        }
    }

}