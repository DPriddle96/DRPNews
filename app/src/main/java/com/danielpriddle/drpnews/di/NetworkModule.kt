package com.danielpriddle.drpnews.di

import android.content.Context
import android.net.ConnectivityManager
import com.danielpriddle.drpnews.data.networking.NewsAPI
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val NEWS_API_URL = "https://newsapi.org/"

    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI {
        val client: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .baseUrl(NEWS_API_URL)
            .build()

        return retrofit.create(NewsAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkStatusChecker(@ApplicationContext context: Context): NetworkStatusChecker {
        return NetworkStatusChecker(context.getSystemService(ConnectivityManager::class.java))
    }
}