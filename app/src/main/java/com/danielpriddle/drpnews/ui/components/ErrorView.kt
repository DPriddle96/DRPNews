package com.danielpriddle.drpnews.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme

@Composable
fun ErrorView(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.Warning,
                contentDescription = errorMessage,
                tint = Color.Red
            )
            Text(text = errorMessage, style = MaterialTheme.typography.h4)
        }

    }

}

@Preview
@Composable
fun ErrorViewPreview() {
    DRPNewsTheme {
        ErrorView(errorMessage = "This is an error message")
    }
}