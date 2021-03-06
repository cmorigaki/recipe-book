package br.com.recipebook.settings.theme.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.settings.theme.R
import br.com.recipebook.settings.theme.databinding.SettingsThemeActivityBinding
import br.com.recipebook.settings.theme.presentation.SettingsThemeAction
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SettingsThemeActivity : AppCompatActivity() {
    private val viewModel: SettingsThemeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsThemeActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState(this)
        }
    }

    private fun initComponents(binding: SettingsThemeActivityBinding) {
        setupToolbar(binding)
        setupRadioButtons(binding)
    }

    private fun setupToolbar(binding: SettingsThemeActivityBinding) {
        binding.toolbarTitle.text = getString(R.string.settings_theme_title)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRadioButtons(binding: SettingsThemeActivityBinding) {
        binding.settingsThemeSystemDefault.setOnClickListener {
            viewModel.dispatchAction(SettingsThemeAction.SystemThemeSelected)
        }
        binding.settingsThemeLight.setOnClickListener {
            viewModel.dispatchAction(SettingsThemeAction.LightThemeSelected)
        }
        binding.settingsThemeDark.setOnClickListener {
            viewModel.dispatchAction(SettingsThemeAction.DarkThemeSelected)
        }
    }

    private fun observeState(binding: SettingsThemeActivityBinding) {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.isLoading.collect {
                binding.settingsLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.hasError.collect {
                binding.settingsErrorState.root.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.isSystemThemeSelected.collect {
                binding.settingsThemeSystemDefault.isChecked = it
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.isLightThemeSelected.collect {
                binding.settingsThemeLight.isChecked = it
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.isDarkThemeSelected.collect {
                binding.settingsThemeDark.isChecked = it
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsThemeActivity::class.java)
        }
    }
}
