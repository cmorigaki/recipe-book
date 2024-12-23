package br.com.recipebook.recipecollection

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.designsystem.compose.RecipeBookTheme
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.navigation.intent.RecipeDetailIntent
import br.com.recipebook.navigation.intent.SettingsIntent
import br.com.recipebook.recipecollection.presentation.RecipeCollectionAction
import br.com.recipebook.recipecollection.presentation.RecipeCollectionCommand
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import br.com.recipebook.recipecollection.view.RecipeCollectionView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class RecipeCollectionActivity : AppCompatActivity() {

    private val viewModel: RecipeCollectionViewModel by viewModel()
    private val mainNavigator: MainNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        observeState()
        observeActionCommand()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                setContent {
                    RecipeBookTheme {
                        RecipeCollectionView(
                            state = it,
                            onItemClick = {
                                viewModel.dispatchAction(
                                    RecipeCollectionAction.RecipeClick(
                                        recipeId = it.id,
                                        title = it.title,
                                    )
                                )
                            },
                            onSettingsClick = {
                                mainNavigator.navigate(this@RecipeCollectionActivity, SettingsIntent)
                            },
                            onRefresh = {
                                viewModel.dispatchAction(RecipeCollectionAction.Refresh)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun observeActionCommand() {
        lifecycleScope.launchWhenStarted {
            viewModel.commandReceiver.collect {
                when (it) {
                    is RecipeCollectionCommand.OpenRecipeDetail -> {
                        mainNavigator.navigate(
                            this@RecipeCollectionActivity,
                            RecipeDetailIntent(recipeId = it.recipeId, title = it.title)
                        )
                    }
                    is RecipeCollectionCommand.FinishApp -> {
                        exitProcess(1)
                    }
                }
            }
        }
    }
}
