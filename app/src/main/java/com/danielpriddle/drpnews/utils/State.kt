package com.danielpriddle.drpnews.utils

import com.danielpriddle.drpnews.data.database.entities.relations.ArticleAndSource
import com.danielpriddle.drpnews.data.models.Article

sealed class State {
    object Loading : State()
    data class Ready(val articles: List<Article>) : State()
    data class Error(val error: String) : State()
}
