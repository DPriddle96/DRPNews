package com.danielpriddle.drpnews.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.danielpriddle.drpnews.data.models.Article
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danielpriddle.drpnews.data.models.Source
import com.danielpriddle.drpnews.utils.State
import com.danielpriddle.drpnews.viewmodels.ArticleListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ArticleListComposable(
    articles: List<Article>,
    clickListener: (Article) -> Unit = {},
) {
    val articleViewModel: ArticleListViewModel = viewModel()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = articleViewModel.state.value == State.Loading),
        onRefresh = { articleViewModel.getArticles() }) {
        LazyColumn(modifier = Modifier) {
            items(articles) { article ->
                ArticleComposable(article, clickListener)
            }
        }
    }
}

@Composable
fun ArticleComposable(article: Article, clickListener: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                clickListener(article)
            },
        backgroundColor = Color.White,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = article.source.name,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun ArticleListComposablePreview() {
    ArticleListComposable(
        listOf(
            Article(
                source = Source(
                    id = "1",
                    name = "Test Source"
                ),
                title = "Test Title",
                url = "https://test.com",
                publishedAt = "10/17/2022",
                author = "Dan Priddle",
                description = "This is a preview article."
            ),
            Article(
                source = Source(
                    id = "2",
                    name = "Test Source 2"
                ),
                title = "Test Title 2",
                url = "https://test2.com",
                publishedAt = "10/31/2022",
                author = "Joe Shmoe",
                description = "This is another preview article."
            ),
        ),
    )
}