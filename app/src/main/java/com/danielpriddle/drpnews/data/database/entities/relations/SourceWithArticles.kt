package com.danielpriddle.drpnews.data.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.SourceEntity

data class SourceWithArticles(
    @Embedded
    val source: SourceEntity,
    @Relation(parentColumn = "name", entityColumn = "source_name")
    val articles: List<ArticleEntity>,
)
