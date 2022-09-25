package com.danielpriddle.drpnews.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Functions that help build the HTTP client that will be used to call NewsAPI endpoints.
 * @author Dan Priddle
 */

const val NEWS_API_URL = "https://newsapi.org/"

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .baseUrl(NEWS_API_URL)
        .build()
}

fun buildApiService(): NewsAPI =
    buildRetrofit().create(NewsAPI::class.java)