package com.danielpriddle.drpnews.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielpriddle.drpnews.data.models.Article
import com.danielpriddle.drpnews.ui.components.ArticleList
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme
import com.danielpriddle.drpnews.viewmodels.ArticleSearchViewModel

@Composable
fun ArticleSearchScreen(
    clickListener: (Article) -> Unit = {},
    articleSearchViewModel: ArticleSearchViewModel = hiltViewModel(),
) {
    val articles = articleSearchViewModel.articles.collectAsState().value
    val textFieldState =
        rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    Column {
        ArticleSearch(state = textFieldState, onValueChange = { textFieldValue ->
            articleSearchViewModel.searchArticles(textFieldValue.text)
        })
        ArticleList(articles, clickListener)
    }
}

@Composable
fun ArticleSearch(
    state: MutableState<TextFieldValue>,
    onValueChange: (TextFieldValue) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = state.value,
        onValueChange = { value ->
            state.value = value
            onValueChange(value)
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search icon")
        }
    )
}

@Preview
@Composable
fun ArticleSearchPreview() {
    DRPNewsTheme {
        val textFieldState =
            rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
        ArticleSearch(state = textFieldState, onValueChange = {})
    }
}