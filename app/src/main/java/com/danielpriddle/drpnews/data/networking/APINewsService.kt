package com.danielpriddle.drpnews.data.networking

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.NewsAPIErrorResponse
import com.danielpriddle.drpnews.data.models.NewsAPIResponse
import com.danielpriddle.drpnews.utils.*
import com.google.gson.Gson
import retrofit2.Response

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
) : Logger {
    suspend fun getTopHeadlines(): DataResult<List<Article>> {
        return if (networkStatusChecker.hasInternetConnection()) {
            val response = api.getTopHeadlines(country = "us")
            logDebug("getTopHeadlines Response: $response")
            if (response.isSuccessful) {
                RemoteSuccess(response.body()!!.articles)
            } else {
                logInfo("getTopHeadlines returned an error. Handling error now...")
                handleAPIError(response)
            }
        } else {
            val message =
                "You are not connected to the Internet! Please check your connection and try again!"
            logError(message)
            Failure(message)
        }
    }

    private fun handleAPIError(response: Response<NewsAPIResponse>): Failure {
        val gson = Gson()

        return if (response.code() == 404) {
            val message = "We could not find the news you're looking for!"
            logError(message)
            Failure(message)
        } else {
            val errorResponse = gson.fromJson(response.errorBody()!!.string(),
                NewsAPIErrorResponse::class.java)
            logError("getTopHeadlines ERROR: ${errorResponse.message}")
            Failure(errorResponse.message)
        }
    }
}