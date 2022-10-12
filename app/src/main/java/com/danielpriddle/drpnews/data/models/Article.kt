package com.danielpriddle.drpnews.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Article
 *
 * Data class for Article objects
 *
 * Update 9/10/2022: Added Parcelize plugin to make data class Parcelable for passing article data
 * between activities/fragments.
 * @author Dan Priddle
 */

@Parcelize
data class Article(
    val source: Source,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null,
) : Parcelable
