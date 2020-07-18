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
import br.com.recipebook.recipecollection.presentation.RecipeCollectionActionFromView
import br.com.recipebook.recipecollection.presentation.RecipeCollectionActionToView
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                    RecipeCollectionActionFromView.Refresh
                )
            }
            // FIXME Temporary access point. Said that, I'll not dispatch to VM
            recipeCollectionSettings.setOnClickListener {
                mainNavigator.navigate(this@RecipeCollectionActivity, SettingsIntent)
            }
        }
    }

    private fun observeState(binding: RecipeCollectionActivityBinding) {
        lifecycleScope.launch {
            viewModel.viewState.recipes.collect {
                recipeCollectionAdapter.setData(it)
            }
        }
        lifecycleScope.launch {
            viewModel.viewState.hasError.collect {
                binding.recipeCollectionErrorState.root.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        lifecycleScope.launch {
            viewModel.viewState.isLoading.collect {
                binding.swipeRefresh.isRefreshing = it
                binding.recipeCollectionLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun observeActionCommand() {
        lifecycleScope.launch {
            viewModel.actionToView.consumeEach {
                when (it) {
                    is RecipeCollectionActionToView.OpenRecipeDetail -> {
                        mainNavigator.navigate(
                            this@RecipeCollectionActivity,
                            RecipeDetailIntent(recipeId = it.recipeId, title = it.title)
                        )
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
            RecipeCollectionActionFromView.RecipeClick(
                recipeId = recipeId,
                title = title
            )
        )
    }
}
