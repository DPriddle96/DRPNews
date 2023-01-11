package com.danielpriddle.drpnews.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danielpriddle.drpnews.R
import com.danielpriddle.drpnews.ui.theme.DRPNewsTheme

@Composable
fun SettingToggle(checked: Boolean, onCheckedChange: (Boolean) -> Unit, settingResId: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange)
        Text(text = stringResource(id = settingResId))
    }
}

@Preview
@Composable
fun SettingTogglePreview() {
    DRPNewsTheme {
        SettingToggle(checked = true,
            onCheckedChange = {},
            settingResId = R.string.download_only_over_wifi)
    }
}