package com.danielpriddle.drpnews.models

/**
 * Data class for Source objects
 * This class is part of the Article data class and holds source objects for articles
 */
data class Source(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val url: String? = null, val category: String? = null,
    val language: String? = null, val country: String? = null
)
