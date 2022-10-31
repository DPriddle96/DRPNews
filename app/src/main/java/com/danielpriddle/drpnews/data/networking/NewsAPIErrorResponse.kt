package com.danielpriddle.drpnews.data.networking

/**
 * NewsAPIErrorResponse
 *
 * Data class to help parse the Retrofit error response.
 * @author Dan Priddle
 */

data class NewsAPIErrorResponse(
    val status: String,
    val code: String,
    val message: String,
)
