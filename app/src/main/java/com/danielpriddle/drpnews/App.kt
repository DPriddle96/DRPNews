package com.danielpriddle.drpnews

import android.app.Application
import android.net.ConnectivityManager
import androidx.datastore.preferences.preferencesDataStore
import com.danielpriddle.drpnews.data.database.NewsDatabase
import com.danielpriddle.drpnews.data.networking.buildApiService
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.data.repository.ArticleRepositoryImpl
import com.danielpriddle.drpnews.data.networking.APINewsService
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStoreImpl
import com.danielpriddle.drpnews.utils.NetworkStatusChecker

class App : Application() {
    val dataStore by preferencesDataStore(name = "preferences")

    companion object {
        private lateinit var instance: App
        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }
        private val newsService by lazy {
            APINewsService(
                buildApiService(),
                NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java)))
        }

        val prefsDataStore by lazy { PreferencesDataStoreImpl(instance.dataStore) }

        val articleRepository: ArticleRepository by lazy {
            ArticleRepositoryImpl(
                newsService,
                database.articleDao(),
                database.sourceDao(),
                prefsDataStore,
                NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java)))
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}