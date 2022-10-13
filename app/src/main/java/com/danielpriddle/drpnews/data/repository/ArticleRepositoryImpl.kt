package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.mappers.*
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.networking.*
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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
    private val dataStore: PreferencesDataStore,
    private val networkStatusChecker: NetworkStatusChecker,
) : ArticleRepository {

    override fun getArticles(): Flow<Result<List<Article>>> {
        return flow {
            val localArticles = articleDao.getArticles().map { articleAndSource ->
                toArticleModel(articleAndSource.article,
                    articleAndSource.source)
            }
            emit(LocalSuccess(localArticles))
            val isDownloadOverWifiOnly = dataStore.isDownloadOverWifiOnly().first()
            if (!isDownloadOverWifiOnly || (isDownloadOverWifiOnly && networkStatusChecker.hasWifiConnection())) {
                try {
                    val remoteArticlesResult = newsService.getTopHeadlines()
                    if (remoteArticlesResult is RemoteSuccess) {
                        val remoteArticles = remoteArticlesResult.data
                        emit(RemoteSuccess(remoteArticles))
                        if (remoteArticles.isNotEmpty()) {
                            remoteArticles.forEach { article ->
                                sourceDao.addSource(toSourceEntity(article.source))
                            }
                            articleDao.addArticles(remoteArticles.map { article ->
                                toArticleEntity(article)
                            })
                        }
                    }
                } catch (e: Exception) {
                    emit(Failure(e.message!!))
                }
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