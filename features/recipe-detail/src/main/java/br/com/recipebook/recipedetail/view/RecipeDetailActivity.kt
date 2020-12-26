package br.com.recipebook.recipedetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.designsystem.ListMarginItemDecoration
import br.com.recipebook.recipedetail.R
import br.com.recipebook.recipedetail.databinding.RecipeDetailActivityBinding
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import br.com.recipebook.utilityandroid.view.activitySafeArgs
import br.com.recipebook.utilityandroid.view.appbarlayout.setCollapsedAndDisableScroll
import br.com.recipebook.utilityandroid.view.putSafeArgs
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
class RecipeDetailActivity : AppCompatActivity() {
    private val safeArgs by activitySafeArgs<RecipeDetailSafeArgs>()

    private val viewModel: RecipeDetailViewModel by viewModel(parameters = { parametersOf(safeArgs) })
    private val imageResolver: ImageResolver by inject()
    private val adapter by lazy { RecipeDetailListAdapter() }

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
        binding.toolbar.navigationIcon?.setTint(getColor(R.color.colorIconDark))
    }

    private fun setupHeader(binding: RecipeDetailActivityBinding) {
        var isShow = true
        var scrollRange = -1
        binding.appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = barLayout?.totalScrollRange!!
                }
                if (scrollRange + verticalOffset == 0) {
                    binding.toolbarTitle.visibility = View.VISIBLE
                    isShow = true
                } else if (isShow) {
                    binding.toolbarTitle.visibility = View.GONE
                    isShow = false
                }
            }
        )
    }

    private fun setupList(binding: RecipeDetailActivityBinding) {
        binding.recipeDetailList.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
            it.addItemDecoration(ListMarginItemDecoration(resources = resources))
        }
    }

    private fun observeState(binding: RecipeDetailActivityBinding) {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.isLoading.collect {
                binding.recipeDetailLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.hasError.collect {
                if (it) {
                    binding.recipeDetailErrorState.root.visibility = View.VISIBLE
                    binding.appBarLayout.setCollapsedAndDisableScroll(binding.recipeDetailList)
                } else {
                    binding.recipeDetailErrorState.root.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.title.collect {
                binding.toolbarTitle.text = it
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.recipeImage.collect {
                if (it != null) {
                    binding.recipeImage.setImageURI(imageResolver.mountUrl(it, ImageSize.LARGE))
                } else {
                    binding.appBarLayout.setCollapsedAndDisableScroll(binding.recipeDetailList)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.listItems.collect {
                binding.recipeDetailList.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
                adapter.setData(it)
            }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            recipeId: String,
            title: String?
        ): Intent {
            return Intent(context, RecipeDetailActivity::class.java).apply {
                putSafeArgs(RecipeDetailSafeArgs(recipeId, title))
            }
        }
    }
}
