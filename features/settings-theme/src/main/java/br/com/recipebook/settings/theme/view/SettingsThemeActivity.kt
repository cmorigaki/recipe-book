package br.com.recipebook.settings.theme.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.designsystem.compose.RecipeBookTheme
import br.com.recipebook.settings.theme.presentation.SettingsThemeAction
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsThemeActivity : AppCompatActivity() {
    private val viewModel: SettingsThemeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                setContent {
                    RecipeBookTheme {
                        SettingsThemeView(
                            state = it,
                            onBackClick = ::onBackPressed,
                            onItemClick = ::onItemClick,
                        )
                    }
                }
            }
        }
    }

    private fun onItemClick(item: SettingsThemeAction) {
        viewModel.dispatchAction(item)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsThemeActivity::class.java)
        }
    }
}
