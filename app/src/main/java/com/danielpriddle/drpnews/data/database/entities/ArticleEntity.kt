package com.danielpriddle.drpnews.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val title: String,
    @ColumnInfo(name = "source_name")
    val source: String,
    val author: String? = null,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null,
) {

}