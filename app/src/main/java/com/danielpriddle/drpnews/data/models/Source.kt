package com.danielpriddle.drpnews.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Source
 *
 * This data class is part of the Article data class and holds Source objects for Articles
 *
 * Update 9/10/2022: Added Parcelize plugin to make data class Parcelable. This data class needs to
 * be Parcelable since it is part of Article, which is also Parcelable.
 * @author Dan Priddle
 */

@Parcelize
data class Source(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val url: String? = null,
    val category: Category? = null,
    val language: Language? = null,
    val country: Country? = null,
) : Parcelable
