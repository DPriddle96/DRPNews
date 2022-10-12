package com.danielpriddle.drpnews.data.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.SourceEntity

data class ArticleAndSource(
    @Embedded
    val article: ArticleEntity,
    @Relation(parentColumn = "source_name", entityColumn = "name")
    val source: SourceEntity,
)