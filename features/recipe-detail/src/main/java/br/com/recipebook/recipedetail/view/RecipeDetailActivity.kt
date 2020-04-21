package br.com.recipebook.recipedetail.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.recipedetail.databinding.RecipeDetailActivityBinding
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailActivity : AppCompatActivity() {
    private val viewModel: RecipeDetailViewModel by viewModel(clazz = RecipeDetailViewModel::class)
    private val imageResolver: ImageResolver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeDetailActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState(this)
        }
    }

    private fun initComponents(binding: RecipeDetailActivityBinding) {
        with(binding) {

        }
    }

    private fun observeState(binding: RecipeDetailActivityBinding) {

    }
}