package com.danielpriddle.drpnews.utils

import com.danielpriddle.drpnews.data.models.NewsAPIErrorResponse
import com.danielpriddle.drpnews.data.models.NewsAPIResponse
import com.danielpriddle.drpnews.data.networking.Failure
import com.google.gson.Gson
import retrofit2.Response

fun handleAPIError(response: Response<NewsAPIResponse>): Failure {
    val gson = Gson()
    return if (response.code() == 404) {
        Failure("We could not find the news you're looking for!")
    } else {
        val errorResponse = gson.fromJson(response.errorBody()!!.string(),
            NewsAPIErrorResponse::class.java)
        Failure(errorResponse.message)
    }
}