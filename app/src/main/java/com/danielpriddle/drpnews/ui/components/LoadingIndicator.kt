package com.danielpriddle.drpnews.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                strokeWidth = 8.dp,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.loading_text),
                style = MaterialTheme.typography.h4
            )
        }

    }
}

@Preview
@Composable
fun LoadingIndicatorPreview() {
    DRPNewsTheme {
        LoadingIndicator()
    }

}