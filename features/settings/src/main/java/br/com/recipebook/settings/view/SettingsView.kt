package br.com.recipebook.settings.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsView(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = "Configuration") },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.ArrowBack,"")
            }
        },
    )
}
