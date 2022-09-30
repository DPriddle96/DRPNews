package com.danielpriddle.drpnews.data.networking

import com.danielpriddle.drpnews.data.models.NewsAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val API_KEY = "8b4d707493f544f09407584ae652edf5"

/**
 * NewsAPI
 *
 * Defines a contract for the various NewsAPI endpoints; including the URL, HTTP method types, and
 * required headers and query parameters.
 * @author Dan Priddle
 */
interface NewsAPI {
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Header("X-Api-Key") apiKey: String = API_KEY,
        @Query("country") country: String,
        @Query("category") category: String? = null,
        @Query("sources") sources: String? = null,
        @Query("q") q: String? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null,
    ): Response<NewsAPIResponse>
}