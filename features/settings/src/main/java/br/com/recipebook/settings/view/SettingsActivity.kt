package br.com.recipebook.settings.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.recipebook.designsystem.ListMarginItemDecoration
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.settings.R
import br.com.recipebook.settings.databinding.SettingsActivityBinding
import br.com.recipebook.settings.presentation.SettingsAction
import br.com.recipebook.settings.presentation.SettingsCommand
import br.com.recipebook.settings.presentation.SettingsViewModel
import br.com.recipebook.settings.presentation.model.SettingsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SettingsActivity : AppCompatActivity() {
    private val viewModel: SettingsViewModel by viewModel()
    private val adapter by lazy { SettingsListAdapter(onItemClick = ::onItemClick) }
    private val mainNavigator: MainNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState(this)
            observeActionCommand()
        }
    }

    private fun initComponents(binding: SettingsActivityBinding) {
        setupToolbar(binding)
        setupList(binding)
    }

    private fun setupToolbar(binding: SettingsActivityBinding) {
        binding.toolbarTitle.text = getString(R.string.settings_title)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupList(binding: SettingsActivityBinding) {
        binding.settingsList.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(ListMarginItemDecoration(resources = resources))
        }
    }

    private fun observeState(binding: SettingsActivityBinding) {
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
            viewModel.viewState.listItems.collect {
                adapter.setData(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.appVersion.collect {
                if (it.isNotBlank()) binding.appVersion.text = getString(R.string.settings_app_version, it)
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
