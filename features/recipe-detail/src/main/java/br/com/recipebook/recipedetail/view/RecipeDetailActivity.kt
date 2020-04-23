package br.com.recipebook.recipedetail.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.recipedetail.R
import br.com.recipebook.recipedetail.databinding.RecipeDetailActivityBinding
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import com.google.android.material.appbar.AppBarLayout
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
        viewModel.viewState.title.value = "Bolo de cenoura"
        viewModel.viewState.recipeImage.value = "bolo_de_cenoura.jpg"

        setupHeader(binding)
        setupToolbar(binding)
    }

    private fun setupToolbar(binding: RecipeDetailActivityBinding) {
        binding.collapsingToolbarLayout.title = " "
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupHeader(binding: RecipeDetailActivityBinding) {
        var isShow = true
        var scrollRange = -1
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                binding.toolbarTitle.visibility = View.VISIBLE
                binding.toolbar.navigationIcon?.setTint(getColor(android.R.color.black))
                isShow = true
            } else if (isShow){
                binding.toolbarTitle.visibility = View.GONE
                binding.toolbar.navigationIcon?.setTint(getColor(android.R.color.white))
                isShow = false
            }
        })
    }

    private fun observeState(binding: RecipeDetailActivityBinding) {
        viewModel.viewState.title.observe(this) {
            binding.title.text = it
            binding.toolbarTitle.text = it
        }
        viewModel.viewState.recipeImage.observe(this) {
            binding.recipeImage.setImageURI(imageResolver.mountUrl(it, ImageSize.LARGE))
        }
    }
}