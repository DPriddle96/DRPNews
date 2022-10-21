package com.danielpriddle.drpnews.data.networking

import com.danielpriddle.drpnews.data.models.Article
import com.squareup.moshi.Json

/**
 * NewsAPIResponse
 *
 * Data class that integrates with Moshi to help parse the JSON response from NewsAPI requests.
 * @author Dan Priddle
 */
data class NewsAPIResponse(
    @field:Json(name = "totalResults") val totalResults: Int,
    @field:Json(name = "articles") val articles: List<Article> = listOf(),
)
