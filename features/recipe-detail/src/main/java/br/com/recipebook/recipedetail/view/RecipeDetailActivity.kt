package br.com.recipebook.recipedetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.designsystem.compose.RecipeBookTheme
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewModel
import br.com.recipebook.utilityandroid.view.activitySafeArgs
import br.com.recipebook.utilityandroid.view.putSafeArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
class RecipeDetailActivity : AppCompatActivity() {
    private val safeArgs by activitySafeArgs<RecipeDetailSafeArgs>()

    private val viewModel: RecipeDetailViewModel by viewModel(parameters = { parametersOf(safeArgs) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                setContent {
                    RecipeBookTheme {
                        RecipeDetailView(
                            state = it,
                            onBackClick = ::onBackPressed,
                        )
                    }
                }
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
