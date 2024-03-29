package com.danielpriddle.drpnews.data.repository

import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.mappers.*
import com.danielpriddle.drpnews.data.models.*
import com.danielpriddle.drpnews.data.networking.APINewsService
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.preferences.PreferencesKeys
import com.danielpriddle.drpnews.utils.Logger
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

/**
 * ArticleRepository
 *
 * This repository class acts as a facilitator for retrieving data in the API layer and storing the
 * data in the database. For now, it just calls the APINewsService to retrieve data, but data
 * storage will be built out in the near future :)
 * @author Dan Priddle
 */

class ArticleRepositoryImpl @Inject constructor(
    private val newsService: APINewsService,
    private val articleDao: ArticleDao,
    private val sourceDao: SourceDao,
    private val dataStore: PreferencesDataStore,
    private val networkStatusChecker: NetworkStatusChecker,
) : ArticleRepository, Logger {

    override fun getArticles(): Flow<DataResult<List<Article>>> {
        return flow {
            logInfo("Retrieving local articles from the database...")
            val localArticles = articleDao.getArticles().map { articleAndSource ->
                toArticleModel(articleAndSource.article,
                    articleAndSource.source)
            }
            logInfo("Articles retrieved from local database successfully. Emitting LocalSuccess!")
            emit(LocalSuccess(localArticles))
            val isDownloadOverWifiOnly =
                dataStore.getPreference(PreferencesKeys.WIFI_ONLY_KEY).first()
            if (!isDownloadOverWifiOnly || networkStatusChecker.hasWifiConnection()) {
                try {
                    val remoteArticlesResult = newsService.getTopHeadlines()
                    if (remoteArticlesResult is RemoteSuccess) {
                        val remoteArticles = remoteArticlesResult.data
                        logInfo("Articles retrieved from NewsAPI successfully. Emitting RemoteSuccess!")
                        emit(RemoteSuccess(remoteArticles))
                        if (remoteArticles.isNotEmpty()) {
                            remoteArticles.forEach { article ->
                                sourceDao.addSource(toSourceEntity(article.source))
                            }
                            logInfo("Sources added to local database!")
                            articleDao.addArticles(remoteArticles.map { article ->
                                toArticleEntity(article)
                            })
                            logInfo("Articles added to local database!")
                        }
                    }
                } catch (e: Exception) {
                    logError("Error fetching remote articles: ${e.message}")
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