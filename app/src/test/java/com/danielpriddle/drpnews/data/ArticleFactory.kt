package com.danielpriddle.drpnews.data

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ArticleFactory {
    fun makeArticle(): String {
        return Json.encodeToString(Article(
            source = Source(
                id = "1",
                name = "Test Source"
            ),
            title = "Test Title",
            url = "https://test.com",
            publishedAt = "10/17/2022",
            author = "Dan Priddle",
            description = "This is a test article generated by an Article Factory."
        ))
    }
}