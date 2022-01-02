package br.com.recipebook.recipedetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSError
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.component.DSTopAppBar
import br.com.recipebook.recipedetail.R
import br.com.recipebook.recipedetail.presentation.RecipeDetailViewState
import br.com.recipebook.recipedetail.presentation.model.DescriptionItem
import br.com.recipebook.recipedetail.presentation.model.IngredientHeaderItem
import br.com.recipebook.recipedetail.presentation.model.InstructionHeaderItem
import coil.compose.rememberImagePainter
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
        when (state) {
            is RecipeDetailViewState.Loading -> {
                RecipeDetailLoading(state.title ?: "", onBackClick)
            }
            is RecipeDetailViewState.Error -> {
                RecipeDetailError(state.title ?: "", onBackClick)
            }
            is RecipeDetailViewState.Loaded -> {
                RecipeDetailLoaded(state, onBackClick)
            }
        }
    }
}

@Composable
fun RecipeDetailLoading(
    title: String,
    onBackClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        DSTopAppBar(
            title = title,
            onBackClick = onBackClick,
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            DSLoading()
        }
    }
}

@Composable
fun RecipeDetailError(
    title: String,
    onBackClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        DSTopAppBar(
            title = title,
            onBackClick = onBackClick,
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            DSError()
        }
    }
}

@Composable
fun RecipeDetailLoaded(
    state: RecipeDetailViewState.Loaded,
    onBackClick: () -> Unit,
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = scaffoldState, // provide the state of the scaffold
        toolbar = {
            if (state.recipeImage != null) {
                Image(
                    painter = rememberImagePainter(
                        ImageResolver.mountUrl(state.recipeImage, ImageSize.MEDIUM),
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .parallax(ratio = 0.2f),
                    contentScale = ContentScale.FillWidth,
                )
            }
            DSTopAppBar(
                title = state.title ?: "",
                textModifier = Modifier.alpha(1 - scaffoldState.toolbarState.progress),
                onBackClick = onBackClick,
            )
        },
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
    ) {
        LazyColumn {
            items(state.listItems) {
                when (it) {
                    IngredientHeaderItem -> ItemHeader(stringResource(id = R.string.recipeDetailIngredients))
                    InstructionHeaderItem -> ItemHeader(stringResource(id = R.string.recipeDetailInstruction))
                    is DescriptionItem -> Item(it.description)
                }
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
