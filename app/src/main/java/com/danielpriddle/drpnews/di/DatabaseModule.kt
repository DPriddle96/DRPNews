package com.danielpriddle.drpnews.di

import android.content.Context
import com.danielpriddle.drpnews.data.database.NewsDatabase
import com.danielpriddle.drpnews.data.database.dao.ArticleDao
import com.danielpriddle.drpnews.data.database.dao.SourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.buildDatabase(context)
    }

    @Provides
    fun provideArticleDao(db: NewsDatabase): ArticleDao {
        return db.articleDao()
    }

    @Provides
    fun provideSourceDao(db: NewsDatabase): SourceDao {
        return db.sourceDao()
    }
}