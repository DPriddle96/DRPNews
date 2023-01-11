package com.danielpriddle.drpnews.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme

@Composable
fun ArticleList(articles: List<Article>, clickListener: (Article) -> Unit = {}) {
    if (articles.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.no_articles),
                style = MaterialTheme.typography.h5,
                fontStyle = FontStyle.Italic)
        }
    }
    LazyColumn(modifier = Modifier) {
        items(articles) { article ->
            ArticleCard(article, clickListener)
        }
    }
}

@Preview
@Composable
fun ArticleListPreview() {
    DRPNewsTheme {
        ArticleList(articles = listOf(
            Article(
                title = "Test Title",
                source = Source(name = "Test Source"),
                author = "Dan Priddle",
                publishedAt = "11/4/2022",
                url = "https://test.com"
            ),
            Article(
                title = "Test Title",
                source = Source(name = "Test Source"),
                author = "Dan Priddle",
                publishedAt = "11/4/2022",
                url = "https://test.com"
            ),
            Article(
                title = "Test Title",
                source = Source(name = "Test Source"),
                author = "Dan Priddle",
                publishedAt = "11/4/2022",
                url = "https://test.com"
            )
        ))
    }
}