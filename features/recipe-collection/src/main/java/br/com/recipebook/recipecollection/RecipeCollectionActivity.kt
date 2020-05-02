package br.com.recipebook.recipecollection

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.view.ListMarginItemDecoration
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
        with(viewModel.viewState) {
            recipes.observe(this@RecipeCollectionActivity) {
                recipeCollectionAdapter.setData(it)
            }
            hasError.observe(this@RecipeCollectionActivity) {
                if (it) {
                    Toast.makeText(
                        this@RecipeCollectionActivity,
                        "Error... improve this",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            isLoading.observe(this@RecipeCollectionActivity) {
                binding.swipeRefresh.isRefreshing = it
            }
        }
    }

    private fun onRecipeClick(recipeId: String) {
        mainNavigator.navigate(this, RecipeDetailIntent(recipeId))
    }
}