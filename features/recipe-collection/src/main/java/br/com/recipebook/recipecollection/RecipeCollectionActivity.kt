package br.com.recipebook.recipecollection

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.designsystem.ListMarginItemDecoration
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.navigation.intent.RecipeDetailIntent
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewAction
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                viewModel.dispatchViewAction(
                    RecipeCollectionViewAction.Refresh
                )
            }
        }
    }

    private fun observeState(binding: RecipeCollectionActivityBinding) {
        viewModel.viewState.recipes.observe(this) {
            recipeCollectionAdapter.setData(it)
        }
        viewModel.viewState.hasError.observe(this) {
            binding.recipeCollectionErrorState.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.viewState.isLoading.observe(this) {
            binding.swipeRefresh.isRefreshing = it
            binding.recipeCollectionLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun onRecipeClick(recipeId: String, title: String?) {
        mainNavigator.navigate(this, RecipeDetailIntent(recipeId = recipeId, title = title))
    }
}