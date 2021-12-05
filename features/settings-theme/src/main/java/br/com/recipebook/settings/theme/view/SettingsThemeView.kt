package br.com.recipebook.settings.theme.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.component.DSTextButton
import br.com.recipebook.designsystem.compose.component.DSTopAppBar
import br.com.recipebook.settings.theme.R
import br.com.recipebook.settings.theme.presentation.SettingsThemeAction
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewState

@Composable
fun SettingsThemeView(
    state: SettingsThemeViewState,
    onBackClick: () -> Unit,
    onItemClick: (item: SettingsThemeAction) -> Unit,
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            DSTopAppBar(
                titleResId = R.string.settings_theme_title,
                onBackClick = onBackClick,
            )
            when (state) {
                is SettingsThemeViewState.Loaded -> SettingsThemeViewLoaded(
                    state,
                    Modifier.weight(weight = 1f),
                    onItemClick,
                )
                is SettingsThemeViewState.Error -> SettingsThemeViewError(Modifier.weight(weight = 1f))
                is SettingsThemeViewState.Loading -> SettingsThemeViewLoading(Modifier.weight(weight = 1f))
            }
        }
    }
}

@Composable
fun SettingsThemeViewLoaded(
    state: SettingsThemeViewState.Loaded,
    modifier: Modifier,
    onItemClick: (item: SettingsThemeAction) -> Unit,
) {
    Column(modifier = modifier) {
        ThemeItemView(
            textResId = R.string.theme_option_system,
            isSelected = SettingsThemeAction.SystemThemeSelected == state.selectedTheme,
            onItemClick = { onItemClick(SettingsThemeAction.SystemThemeSelected) }
        )
        ThemeItemView(
            textResId = R.string.theme_option_light,
            isSelected = SettingsThemeAction.LightThemeSelected == state.selectedTheme,
            onItemClick = { onItemClick(SettingsThemeAction.LightThemeSelected) }
        )
        ThemeItemView(
            textResId = R.string.theme_option_dark,
            isSelected = SettingsThemeAction.DarkThemeSelected == state.selectedTheme,
            onItemClick = { onItemClick(SettingsThemeAction.DarkThemeSelected) }
        )
    }
}

@Composable
private fun ThemeItemView(
    @StringRes textResId: Int,
    isSelected: Boolean,
    onItemClick: () -> Unit,
) {
    Row {
        RadioButton(
            modifier = Modifier.align(Alignment.CenterVertically).padding(start = Spacing.MarginNormal100),
            selected = isSelected,
            onClick = { onItemClick() },
        )
        DSTextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onItemClick() },
            textResId = textResId,
        )
    }
}

@Composable
fun SettingsThemeViewLoading(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}

@Composable
fun SettingsThemeViewError(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}
