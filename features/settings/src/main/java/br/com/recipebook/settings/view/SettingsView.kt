package br.com.recipebook.settings.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.com.recipebook.designsystem.compose.RecipeBookTheme
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.component.DSTopAppBar
import br.com.recipebook.designsystem.compose.minTouchHeight
import br.com.recipebook.navigation.intent.NavIntent
import br.com.recipebook.settings.R
import br.com.recipebook.settings.presentation.SettingsViewState
import br.com.recipebook.settings.presentation.model.SettingsItem

@Preview
@Composable
fun SettingsViewPreview() {
    val state = SettingsViewState.Loaded(
        listItems = listOf(
            SettingsItem(id = "1", title = R.string.settings_title, navIntent = object : NavIntent {}),
            SettingsItem(id = "2", title = R.string.settings_title, navIntent = object : NavIntent {}),
        ),
        appVersion = "1.0.0",
    )
    RecipeBookTheme {
        SettingsView(
            state = state,
            onBackClick = {},
            onItemClick = {},
        )
    }
}

@Composable
fun SettingsView(
    state: SettingsViewState,
    onBackClick: () -> Unit,
    onItemClick: (item: SettingsItem) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DSTopAppBar(
            titleResId = R.string.settings_title,
            onBackClick = onBackClick,
        )
        when (state) {
            is SettingsViewState.Loaded -> SettingsViewLoaded(
                state,
                Modifier.weight(weight = 1f),
                onItemClick,
            )
            is SettingsViewState.Error -> SettingsViewError(Modifier.weight(weight = 1f))
            is SettingsViewState.Loading -> SettingsViewLoading(Modifier.weight(weight = 1f))
        }
        Text(
            text = stringResource(id = R.string.settings_app_version, state.appVersion),
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.MarginSmall100),
        )
    }
}

@Composable
fun SettingsViewLoaded(
    state: SettingsViewState.Loaded,
    modifier: Modifier,
    onItemClick: (item: SettingsItem) -> Unit,
) {
    Column(modifier = modifier) {
        state.listItems.forEach {
            SettingsItem(item = it, onItemClick = onItemClick)
        }
    }
}

@Composable
private fun SettingsItem(
    item: SettingsItem,
    onItemClick: (item: SettingsItem) -> Unit,
) {
    ClickableText(
        text = AnnotatedString(stringResource(id = item.title)),
        modifier = Modifier
            .padding(horizontal = Spacing.MarginNormal100)
            .fillMaxWidth()
            .minTouchHeight(),
        onClick = { onItemClick(item) },
    )
}

@Composable
fun SettingsViewLoading(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}

@Composable
fun SettingsViewError(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}
