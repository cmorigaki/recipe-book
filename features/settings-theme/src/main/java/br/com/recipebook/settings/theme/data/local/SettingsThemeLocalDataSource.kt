package br.com.recipebook.settings.theme.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.recipebook.settings.theme.data.SettingsThemeDataSource
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel

private const val KEY_CURRENT_THEME = "CURRENT_THEME"

class SettingsThemeLocalDataSource(
    private val sharedPreferences: SharedPreferences
) : SettingsThemeDataSource {

    override fun getCurrentTheme(): UserThemePreferenceModel {
        return sharedPreferences.getString(KEY_CURRENT_THEME, null)?.let {
            UserThemePreferenceModel.valueOf(it)
        } ?: UserThemePreferenceModel.SYSTEM
    }

    override fun setCurrentTheme(theme: UserThemePreferenceModel) {
        sharedPreferences.edit {
            putString(KEY_CURRENT_THEME, theme.name)
            apply()
        }
    }
}