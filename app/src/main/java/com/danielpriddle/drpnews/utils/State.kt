package com.danielpriddle.drpnews.utils

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Success

sealed class State {
    object Loading : State()
    data class Ready(val result: Success<List<Article>>) : State()
    data class Error(val error: String) : State()
}
