package com.danielpriddle.drpnews.data.networking

import kotlinx.serialization.Serializable

/**
 * NewsAPIErrorResponse
 *
 * Data class to help parse the Retrofit error response.
 * @author Dan Priddle
 */

@Serializable
data class NewsAPIErrorResponse(
    val status: String,
    val code: String,
    val message: String,
)
