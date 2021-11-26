package br.com.recipebook.designsystem.compose.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.recipebook.designsystem.compose.textColorPrimary
import br.com.recipebook.designsystem.compose.toolbarBackground

@Preview
@Composable
fun DSTopAppBarPreview() {
    DSTopAppBar("Title", {})
}

@Composable
fun DSTopAppBar(
    @StringRes titleResId: Int,
    onBackClick: () -> Unit,
) {
    DSTopAppBar(
        title = stringResource(id = titleResId),
        onBackClick = onBackClick
    )
}

@Composable
fun DSTopAppBar(
    title: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        content = {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                }
            }
        }
    )
}
