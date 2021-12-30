package br.com.recipebook.recipedetail.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.component.DSTopAppBar
import br.com.recipebook.recipedetail.R
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewState
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
            modifier = Modifier.road(
                    whenCollapsed = Alignment.CenterStart,
                    whenExpanded = Alignment.BottomEnd
                ),
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
                    RecipeDetailLoading(Modifier)
                }
                is RecipeDetailViewState.Error -> {

                }
                is RecipeDetailViewState.Loaded -> {

                }
            }
        }
    }


}

@Composable
fun RecipeDetailLoading(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}
