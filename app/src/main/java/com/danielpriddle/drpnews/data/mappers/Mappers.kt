package com.danielpriddle.drpnews.data.mappers

import com.danielpriddle.drpnews.data.database.entities.ArticleEntity
import com.danielpriddle.drpnews.data.database.entities.SourceEntity
import com.danielpriddle.drpnews.data.models.*

fun fromArticleModel(model: Article): ArticleEntity {
    return ArticleEntity(
        title = model.title,
        source = model.source.name,
        author = model.author,
        description = model.description,
        url = model.url,
        urlToImage = model.urlToImage,
        publishedAt = model.publishedAt,
        content = model.content
    )
}

fun toArticleModel(articleEntity: ArticleEntity, sourceEntity: SourceEntity): Article {
    return Article(
        title = articleEntity.title,
        source = toSourceModel(sourceEntity),
        author = articleEntity.author,
        description = articleEntity.description,
        url = articleEntity.url,
        urlToImage = articleEntity.urlToImage,
        publishedAt = articleEntity.publishedAt,
        content = articleEntity.content
    )
}

fun fromSourceModel(model: Source): SourceEntity {
    return SourceEntity(
        id = model.id,
        name = model.name,
        description = model.description,
        url = model.url,
        country = model.country,
        category = model.category,
        language = model.language
    )
}

fun toSourceModel(sourceEntity: SourceEntity): Source {
    return Source(
        id = sourceEntity.id,
        name = sourceEntity.name,
        description = sourceEntity.description,
        url = sourceEntity.url,
        country = sourceEntity.country,
        category = sourceEntity.category,
        language = sourceEntity.language,
    )
}