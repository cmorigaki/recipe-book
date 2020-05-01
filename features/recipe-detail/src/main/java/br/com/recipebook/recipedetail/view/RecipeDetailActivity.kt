package br.com.recipebook.recipedetail.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.coreandroid.view.ListMarginItemDecoration
import br.com.recipebook.recipedetail.databinding.RecipeDetailActivityBinding
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import com.google.android.material.appbar.AppBarLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailActivity : AppCompatActivity() {
    private val viewModel: RecipeDetailViewModel by viewModel(clazz = RecipeDetailViewModel::class)
    private val imageResolver: ImageResolver by inject()
    private val adapter by lazy { RecipeDetailListAdapter(imageResolver) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RecipeDetailActivityBinding.inflate(layoutInflater).apply {
            initComponents(this)
            setContentView(root)
            observeState(this)
        }
    }

    private fun initComponents(binding: RecipeDetailActivityBinding) {
        setupHeader(binding)
        setupToolbar(binding)
        setupList(binding)
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
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.toolbarTitle.visibility = View.VISIBLE
                binding.toolbar.navigationIcon?.setTint(getColor(android.R.color.black))
                isShow = true
            } else if (isShow) {
                binding.toolbarTitle.visibility = View.GONE
                binding.toolbar.navigationIcon?.setTint(getColor(android.R.color.white))
                isShow = false
            }
        })
    }

    private fun setupList(binding: RecipeDetailActivityBinding) {
        binding.recipeDetailList.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(ListMarginItemDecoration(resources = resources))
        }
    }

    private fun observeState(binding: RecipeDetailActivityBinding) {
        viewModel.viewState.title.observe(this) {
            binding.toolbarTitle.text = it
        }
        viewModel.viewState.recipeImage.observe(this) {
            binding.recipeImage.setImageURI(imageResolver.mountUrl(it, ImageSize.LARGE))
        }
        viewModel.viewState.listItems.observe(this) {
            adapter.setData(it)
        }
    }
}