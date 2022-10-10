package com.danielpriddle.drpnews

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.datastore.preferences.preferencesDataStore
import com.danielpriddle.drpnews.data.database.NewsDatabase
import com.danielpriddle.drpnews.data.networking.buildApiService
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.data.repository.ArticleRepositoryImpl
import com.danielpriddle.drpnews.data.services.APINewsService
import com.danielpriddle.drpnews.utils.NetworkStatusChecker

class App : Application() {

    companion object {
        private lateinit var instance: App
        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }
        private val newsService by lazy {
            APINewsService(buildApiService(),
                NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java)))
        }

        val articleRepository: ArticleRepository by lazy {
            ArticleRepositoryImpl(newsService, database.articleDao())
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}