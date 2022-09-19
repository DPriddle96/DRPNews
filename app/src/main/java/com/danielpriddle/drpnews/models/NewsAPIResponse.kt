package com.danielpriddle.drpnews.models

import com.squareup.moshi.Json

data class NewsAPIResponse(
    @field:Json(name = "totalResults") val totalResults: Int,
    @field:Json(name = "articles") val articles: List<Article> = listOf(),
)
