package br.com.recipebook.settings.theme.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.settings.theme.R
import br.com.recipebook.settings.theme.databinding.SettingsThemeActivityBinding
import br.com.recipebook.settings.theme.presentation.SettingsThemeActionFromView
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsThemeActivity : AppCompatActivity() {
    private val viewModel: SettingsThemeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsThemeActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState(this)
            observeActionCommand()
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
            viewModel.dispatchAction(SettingsThemeActionFromView.SystemThemeSelected)
        }
        binding.settingsThemeLight.setOnClickListener {
            viewModel.dispatchAction(SettingsThemeActionFromView.LightThemeSelected)
        }
        binding.settingsThemeDark.setOnClickListener {
            viewModel.dispatchAction(SettingsThemeActionFromView.DarkThemeSelected)
        }
    }

    private fun observeState(binding: SettingsThemeActivityBinding) {
        viewModel.viewState.isLoading.observe(this) {
            binding.settingsLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.viewState.hasError.observe(this) {
            binding.settingsErrorState.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.viewState.isSystemThemeSelected.observe(this) {
            binding.settingsThemeSystemDefault.isChecked = it
        }
        viewModel.viewState.isLightThemeSelected.observe(this) {
            binding.settingsThemeLight.isChecked = it
        }
        viewModel.viewState.isDarkThemeSelected.observe(this) {
            binding.settingsThemeDark.isChecked = it
        }
    }

    private fun observeActionCommand() {
        viewModel.actionToView.observe(this) {

        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsThemeActivity::class.java)
        }
    }
}