package br.com.recipebook.settings.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.designsystem.compose.RecipeBookTheme
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.settings.presentation.SettingsAction
import br.com.recipebook.settings.presentation.SettingsCommand
import br.com.recipebook.settings.presentation.SettingsViewModel
import br.com.recipebook.settings.presentation.model.SettingsItem
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    private val viewModel: SettingsViewModel by viewModel()
    private val mainNavigator: MainNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        observeState()
        observeActionCommand()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                setContent {
                    RecipeBookTheme {
                        SettingsView(
                            state = it,
                            onBackClick = { onBackPressedDispatcher.onBackPressed() },
                            onItemClick = ::onItemClick,
                        )
                    }
                }
            }
        }
    }

    private fun observeActionCommand() {
        lifecycleScope.launchWhenStarted {
            viewModel.commandReceiver.collect {
                when (it) {
                    is SettingsCommand.OpenItem -> {
                        mainNavigator.navigate(
                            this@SettingsActivity,
                            it.navIntent
                        )
                    }
                }
            }
        }
    }

    private fun onItemClick(item: SettingsItem) {
        viewModel.dispatchAction(
            SettingsAction.ItemClick(settingsItem = item)
        )
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}
