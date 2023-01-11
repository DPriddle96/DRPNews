package com.danielpriddle.drpnews.data.networking

import android.content.Context
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.data.models.*
import com.danielpriddle.drpnews.utils.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * APINewsService
 *
 * This service class keeps data retrieval from the API separate from the UI logic and provides a
 * service that the Article repository can consume to store data. It also enhances error handling
 * by handling network connection issues and client side errors.
 * @author Dan Priddle
 */

@Singleton
class APINewsService @Inject constructor(
    private val api: NewsAPI,
    private val networkStatusChecker: NetworkStatusChecker,
    @ApplicationContext private val context: Context,
) : Logger {
    suspend fun getTopHeadlines(): DataResult<List<Article>> {
        return if (networkStatusChecker.hasInternetConnection()) {
            val response = api.getTopHeadlines(country = "us")
            logDebug("getTopHeadlines Response: $response")
            if (response.isSuccessful) {
                RemoteSuccess(response.body()!!.articles)
            } else {
                logInfo("getTopHeadlines returned an error. Handling error now...")
                handleAPIError(context, response)
            }
        } else {
            val message =
                "You are not connected to the Internet! Please check your connection and try again!"
            logError(message)
            Failure(message)
        }
    }

    private fun handleAPIError(context: Context, response: Response<NewsAPIResponse>): Failure {
        return if (response.code() == 404) {
            val message = context.getString(R.string.notFound_error)
            logError(message)
            Failure(message)
        } else {
            val errorResponse =
                Json.decodeFromString<NewsAPIErrorResponse>(response.errorBody()!!.string())
            logInfo(errorResponse.message)
            logError("getTopHeadlines ERROR: ${errorResponse.message}")
            Failure(errorResponse.message)
        }
    }
}