package br.com.recipebook.recipecollection

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.navigation.intent.RecipeDetailIntent
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewAction
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import br.com.recipebook.utilityandroid.MarginItemDecoration
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
                MarginItemDecoration(
                    space = resources.getDimension(R.dimen.margin_components_default).toInt(),
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
            if (it) {
                Toast.makeText(
                    this@RecipeCollectionActivity,
                    "Error... improve this",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.viewState.isLoading.observe(this) {
            binding.swipeRefresh.isRefreshing = it
        }
    }

    private fun onRecipeClick() {
        mainNavigator.navigate(this, RecipeDetailIntent)
    }
}