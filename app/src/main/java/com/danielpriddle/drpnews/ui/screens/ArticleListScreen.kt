package com.danielpriddle.drpnews.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.ui.components.ArticleList
import com.danielpriddle.drpnews.ui.components.ErrorView
import com.danielpriddle.drpnews.ui.components.LoadingIndicator
import com.danielpriddle.drpnews.utils.ViewState
import com.danielpriddle.drpnews.utils.toast
import com.danielpriddle.drpnews.viewmodels.ArticleListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ArticleListScreen(
    articleListViewModel: ArticleListViewModel = hiltViewModel(),
    clickListener: (Article) -> Unit = {},
) {
    val viewState = articleListViewModel.state.collectAsState().value
    val context = LocalContext.current

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewState == ViewState.Loading)

    LaunchedEffect(Unit) {
        articleListViewModel.toastMessage.collect { message ->
            context.toast(context.getString(message))
        }
    }

    Column {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                articleListViewModel.getArticles()
            }) {
            when (viewState) {
                is ViewState.Loading -> {
                    LoadingIndicator()
                }
                is ViewState.Ready -> {
                    val articles = viewState.result.data
                    ArticleList(articles, clickListener)
                }
                is ViewState.Error -> {
                    ErrorView(viewState.error)
                }
            }

        }
    }

}