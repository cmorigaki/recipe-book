package br.com.recipebook.recipecollection

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.designsystem.ListMarginItemDecoration
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.navigation.intent.RecipeDetailIntent
import br.com.recipebook.navigation.intent.SettingsIntent
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.presentation.RecipeCollectionAction
import br.com.recipebook.recipecollection.presentation.RecipeCollectionCommand
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

@ExperimentalCoroutinesApi
class RecipeCollectionActivity : AppCompatActivity() {

    private val viewModel: RecipeCollectionViewModel by viewModel(clazz = RecipeCollectionViewModel::class)
    private val imageResolver: ImageResolver by inject()
    private val recipeCollectionAdapter by lazy {
        RecipeCollectionAdapter(
            imageResolver,
            ::onRecipeClick
        )
    }
    private val mainNavigator: MainNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeCollectionActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState(this)
            observeActionCommand()
        }
    }

    private fun initComponents(binding: RecipeCollectionActivityBinding) {
        with(binding) {
            recipeList.adapter = recipeCollectionAdapter
            recipeList.addItemDecoration(
                ListMarginItemDecoration(
                    resources = resources,
                    spanCount = 2
                )
            )
            swipeRefresh.setOnRefreshListener {
                viewModel.dispatchAction(
                    RecipeCollectionAction.Refresh
                )
            }
            // FIXME Temporary access point. Said that, I'll not dispatch to VM
            recipeCollectionSettings.setOnClickListener {
                mainNavigator.navigate(this@RecipeCollectionActivity, SettingsIntent)
            }
        }
    }

    private fun observeState(binding: RecipeCollectionActivityBinding) {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.recipes.collect {
                recipeCollectionAdapter.setData(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.hasError.collect {
                binding.recipeCollectionErrorState.root.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.isLoading.collect {
                binding.swipeRefresh.isRefreshing = it
                binding.recipeCollectionLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun observeActionCommand() {
        lifecycleScope.launchWhenStarted {
            viewModel.commandReceiver.collect {
                when (it) {
                    is RecipeCollectionCommand.OpenRecipeDetail -> {
                        mainNavigator.navigate(
                            this@RecipeCollectionActivity,
                            RecipeDetailIntent(recipeId = it.recipeId, title = it.title)
                        )
                    }
                    is RecipeCollectionCommand.FinishApp -> {
                        exitProcess(1)
                    }
                }
            }
        }
    }

    private fun onRecipeClick(
        recipeId: String,
        title: String?
    ) {
        viewModel.dispatchAction(
            RecipeCollectionAction.RecipeClick(
                recipeId = recipeId,
                title = title
            )
        )
    }
}
