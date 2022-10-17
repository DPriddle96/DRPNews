package com.danielpriddle.drpnews.data

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import com.google.gson.Gson

object ArticleFactory {
    fun makeArticle(): String {
        val gson = Gson()
        return gson.toJson(Article(
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