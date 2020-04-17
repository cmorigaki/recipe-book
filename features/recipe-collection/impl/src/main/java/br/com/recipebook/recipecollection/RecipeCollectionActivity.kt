package br.com.recipebook.recipecollection

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import br.com.recipebook.utilityandroid.MarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeCollectionActivity : AppCompatActivity() {

    private val viewModel: RecipeCollectionViewModel by viewModel(clazz = RecipeCollectionViewModel::class)
    private val recipeCollectionAdapter by lazy { RecipeCollectionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeCollectionActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState()
        }
    }

    private fun initComponents(binding: RecipeCollectionActivityBinding) {
        binding.recipeList.adapter = recipeCollectionAdapter
        binding.recipeList.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.margin_components_default).toInt()
            )
        )
    }

    private fun observeState() {
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
            //
        }
    }
}