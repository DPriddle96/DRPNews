package com.danielpriddle.drpnews.di

import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import com.danielpriddle.drpnews.data.networking.APINewsService
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.repository.ArticleRepository
import com.danielpriddle.drpnews.data.repository.ArticleRepositoryImpl
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        newsService: APINewsService,
        articleDao: ArticleDao,
        sourceDao: SourceDao,
        preferencesDataStore: PreferencesDataStore,
        networkStatusChecker: NetworkStatusChecker,
    ): ArticleRepository {
        return ArticleRepositoryImpl(
            newsService,
            articleDao,
            sourceDao,
            preferencesDataStore,
            networkStatusChecker
        )
    }
}