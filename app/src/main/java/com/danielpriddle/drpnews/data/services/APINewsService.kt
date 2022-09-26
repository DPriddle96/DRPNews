package com.danielpriddle.drpnews.data.services

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.NewsAPIErrorResponse
import com.danielpriddle.drpnews.data.networking.*
import com.danielpriddle.drpnews.utils.NetworkStatusChecker
import com.google.gson.Gson

/**
 * APINewsService
 *
 * This service class keeps data retrieval from the API separate from the UI logic and provides a
 * service that the Article repository can consume to store data. It also enhances error handling
 * by handling network connection issues and client side errors.
 * @author Dan Priddle
 */
class APINewsService(
    private val api: NewsAPI,
    private val networkStatusChecker: NetworkStatusChecker,
) {
    private val gson = Gson()
    suspend fun getTopHeadlines(): APIResult<List<Article>> {
        if (networkStatusChecker.hasInternetConnection) {
            val response = api.getTopHeadlines(country = "us")
            return if (response.isSuccessful) {
                Success(response.body()!!.articles)
            } else {
                if (response.code() == 404) {
                    Failure("We could not find the news you're looking for!")
                } else {
                    val errorResponse = gson.fromJson(response.errorBody()!!.string(),
                        NewsAPIErrorResponse::class.java)
                    Failure(errorResponse.message)
                }
            }
        } else {
            return Failure("You are not connected to the Internet! Please check your connection and try again!")
        }
    }
}