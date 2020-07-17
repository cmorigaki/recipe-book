package br.com.recipebook.settings.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.recipebook.designsystem.ListMarginItemDecoration
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.settings.R
import br.com.recipebook.settings.databinding.SettingsActivityBinding
import br.com.recipebook.settings.presentation.SettingsActionFromView
import br.com.recipebook.settings.presentation.SettingsActionToView
import br.com.recipebook.settings.presentation.SettingsViewModel
import br.com.recipebook.settings.presentation.model.SettingsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        lifecycleScope.launch {
            viewModel.viewState.isLoading.collect {
                binding.settingsLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        lifecycleScope.launch {
            viewModel.viewState.hasError.collect {
                binding.settingsErrorState.root.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        lifecycleScope.launch {
            viewModel.viewState.listItems.collect {
                adapter.setData(it)
            }
        }
    }

    private fun observeActionCommand() {
        viewModel.actionToView.observe(this) {
            when (it) {
                is SettingsActionToView.OpenItem -> {
                    mainNavigator.navigate(
                        this,
                        it.navIntent
                    )
                }
            }
        }
    }

    private fun onItemClick(item: SettingsItem) {
        viewModel.dispatchAction(
            SettingsActionFromView.ItemClick(settingsItem = item)
        )
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}
