package br.com.recipebook.recipedetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
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
    statusBarHeight: Dp,
    onBackClick: () -> Unit,
) {
    Surface {
        when (state) {
            is RecipeDetailViewState.Loading -> {
                RecipeDetailLoading(state.title ?: "", statusBarHeight, onBackClick)
            }
            is RecipeDetailViewState.Error -> {
                RecipeDetailError(state.title ?: "", statusBarHeight, onBackClick)
            }
            is RecipeDetailViewState.Loaded -> {
                RecipeDetailLoaded(state, statusBarHeight, onBackClick)
            }
        }
    }
}

@Composable
private fun RecipeDetailLoading(
    title: String,
    statusBarHeight: Dp,
    onBackClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        RecipeDetailTopBar(
            title = title,
            statusBarHeight = statusBarHeight,
            onBackClick = onBackClick,
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            DSLoading()
        }
    }
}

@Composable
private fun RecipeDetailError(
    title: String,
    statusBarHeight: Dp,
    onBackClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        RecipeDetailTopBar(
            title = title,
            statusBarHeight = statusBarHeight,
            onBackClick = onBackClick,
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            DSError()
        }
    }
}

@Composable
private fun RecipeDetailLoaded(
    state: RecipeDetailViewState.Loaded,
    statusBarHeight: Dp,
    onBackClick: () -> Unit,
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = scaffoldState, // provide the state of the scaffold
        toolbarModifier = Modifier.background(MaterialTheme.colors.primary),
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
            RecipeDetailTopBar(
                title = state.title ?: "",
                statusBarHeight = statusBarHeight,
                titleAlpha = 1 - scaffoldState.toolbarState.progress,
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
private fun ItemHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(Spacing.MarginNormal100),
        style = MaterialTheme.typography.h6,
    )
}

@Composable
private fun Item(text: String) {
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

@Composable
private fun RecipeDetailTopBar(
    title: String,
    titleAlpha: Float = 1f,
    statusBarHeight: Dp,
    onBackClick: () -> Unit,
) {
    DSTopAppBar(
        title = title,
        textModifier = Modifier.alpha(titleAlpha),
        onBackClick = onBackClick,
        tint = Color.White,
        modifier = Modifier.padding(top = statusBarHeight)
    )
}
