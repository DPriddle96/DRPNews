package com.danielpriddle.drpnews.utils

import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Success

sealed class ViewState {
    object Loading : ViewState()
    data class Ready(val result: Success<List<Article>>) : ViewState()
    data class Error(val error: String) : ViewState()
}
