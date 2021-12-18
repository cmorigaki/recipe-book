package br.com.recipebook.recipecollection

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.recipebook.designsystem.ListMarginItemDecoration
import br.com.recipebook.designsystem.compose.RecipeBookTheme
import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.navigation.intent.RecipeDetailIntent
import br.com.recipebook.navigation.intent.SettingsIntent
import br.com.recipebook.recipecollection.databinding.RecipeCollectionActivityBinding
import br.com.recipebook.recipecollection.presentation.RecipeCollectionAction
import br.com.recipebook.recipecollection.presentation.RecipeCollectionCommand
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewModel
import br.com.recipebook.recipecollection.view.RecipeCollectionView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

@ExperimentalCoroutinesApi
class RecipeCollectionActivity : AppCompatActivity() {

    private val viewModel: RecipeCollectionViewModel by viewModel(clazz = RecipeCollectionViewModel::class)
    private val recipeCollectionAdapter by lazy {
        RecipeCollectionAdapter(
            ::onRecipeClick
        )
    }
    private val mainNavigator: MainNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        RecipeCollectionActivityBinding.inflate(layoutInflater).apply {
//            initComponents(this)
//            setContentView(root)
//            observeState()
//            observeActionCommand()
//        }
        observeState()
        observeActionCommand()
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
                viewModel.dispatchAction(
                    RecipeCollectionAction.Refresh
                )
            }
            // FIXME Temporary access point. Said that, I'll not dispatch to VM
            recipeCollectionSettings.setOnClickListener {
                mainNavigator.navigate(this@RecipeCollectionActivity, SettingsIntent)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                setContent {
                    RecipeBookTheme {
                        RecipeCollectionView(state = it)
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

    private fun onRecipeClick(
        recipeId: String,
        title: String?
    ) {
        viewModel.dispatchAction(
            RecipeCollectionAction.RecipeClick(
                recipeId = recipeId,
                title = title
            )
        )
    }
}
