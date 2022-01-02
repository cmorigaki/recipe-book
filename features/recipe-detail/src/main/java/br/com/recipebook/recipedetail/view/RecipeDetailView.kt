package br.com.recipebook.recipedetail.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import br.com.recipebook.designsystem.compose.FontSize
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSError
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.component.DSTopAppBar
import br.com.recipebook.recipedetail.R
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewState
import br.com.recipebook.recipedetail.presentation.model.DescriptionItem
import br.com.recipebook.recipedetail.presentation.model.IngredientHeaderItem
import br.com.recipebook.recipedetail.presentation.model.InstructionHeaderItem
import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RecipeDetailView(
    state: RecipeDetailViewState,
    onBackClick: () -> Unit,
) {
    Surface {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = rememberCollapsingToolbarScaffoldState(), // provide the state of the scaffold
            toolbar = {
                DSTopAppBar(
                    title = state.title ?: "",
                    onBackClick = onBackClick,
                )
            },
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        ) {
            when (state) {
                is RecipeDetailViewState.Loading -> {
                    RecipeDetailLoading()
                }
                is RecipeDetailViewState.Error -> {
                    RecipeDetailError()
                }
                is RecipeDetailViewState.Loaded -> {
                    RecipeDetailLoaded(state.listItems)
                }
            }
        }
    }


}

@Composable
fun RecipeDetailLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}

@Composable
fun RecipeDetailError() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DSError()
    }
}

@Composable
fun RecipeDetailLoaded(list: List<RecipeDetailItem>) {
    LazyColumn {
        items(list) {
            when (it) {
                IngredientHeaderItem -> ItemHeader(stringResource(id = R.string.recipeDetailIngredients))
                InstructionHeaderItem -> ItemHeader(stringResource(id = R.string.recipeDetailInstruction))
                is DescriptionItem -> Item(it.description)
            }
        }
    }
}

@Composable
fun ItemHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(Spacing.MarginNormal100),
        style = MaterialTheme.typography.h6,
    )
}

@Composable
fun Item(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(
            start = Spacing.MarginLarge100,
            top = Spacing.MarginSmall100,
            end = Spacing.MarginSmall100,
            bottom = Spacing.MarginSmall100,
        ),
        style = MaterialTheme.typography.body1,
    )
}
