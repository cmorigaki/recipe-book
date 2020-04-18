package br.com.recipebook.recipecollection

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewAction
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import br.com.recipebook.utilityandroid.MarginItemDecoration
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.java.KoinJavaComponent

class RecipeCollectionActivity : AppCompatActivity() {

    private val viewModel: RecipeCollectionViewModel by viewModel(clazz = RecipeCollectionViewModel::class)
    private val imageResolver: ImageResolver by inject()
    private val recipeCollectionAdapter by lazy { RecipeCollectionAdapter(imageResolver) }

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
                    resources.getDimension(R.dimen.margin_components_default).toInt()
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
}