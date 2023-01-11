package com.danielpriddle.drpnews.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.data.models.Source
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme

@Composable
fun ArticleCard(article: Article, clickListener: (Article) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                clickListener(article)
            },
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
fun ArticleCardPreview() {
    DRPNewsTheme {
        ArticleCard(
            Article(
                title = "Test Title",
                source = Source(name = "Test Source"),
                author = "Dan Priddle",
                publishedAt = "11/4/2022",
                url = "https://test.com"
            )
        )
    }
}