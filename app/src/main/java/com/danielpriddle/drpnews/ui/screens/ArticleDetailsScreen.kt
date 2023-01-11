package com.danielpriddle.drpnews.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.work.*
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.danielpriddle.drpnews.data.models.Article

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleDetailsScreen(
    article: Article,
) {
    Column(modifier = Modifier
        .padding(8.dp)
        .verticalScroll(rememberScrollState())) {
        Text(text = article.source.name,
            style = MaterialTheme.typography.h5,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = article.title, style = MaterialTheme.typography.h4)
        Text(text = "By ${article.author}")
        Text(text = article.publishedAt)
        GlideImage(model = article.urlToImage,
            contentDescription = article.title,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.size(8.dp))
        article.description?.let { Text(text = it) }
        article.content?.let { Text(text = it) }
        Text(text = article.url, color = Color.Blue, textDecoration = TextDecoration.Underline)
    }
}

