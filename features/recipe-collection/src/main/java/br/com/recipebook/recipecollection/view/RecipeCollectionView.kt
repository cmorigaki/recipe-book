package br.com.recipebook.recipecollection.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.designsystem.compose.FontSize
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSError
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.util.fillHalfSize
import br.com.recipebook.designsystem.compose.util.lineHeight
import br.com.recipebook.recipecollection.R
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewState
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

private const val RecipeImgRatio = 1.3333334f

@Composable
fun RecipeCollectionView(
    state: RecipeCollectionViewState,
    onItemClick: (item: RecipeItem) -> Unit,
    onSettingsClick: () -> Unit,
    onRefresh: () -> Unit,
) {
    Surface {
        when (state) {
            is RecipeCollectionViewState.Loaded -> RecipeCollectionViewLoaded(
                state = state,
                onItemClick = onItemClick,
                onSettingsClick = onSettingsClick,
                onRefresh = onRefresh,
            )
            RecipeCollectionViewState.Loading -> RecipeCollectionViewLoading(Modifier.fillMaxHeight())
            RecipeCollectionViewState.Error -> RecipeCollectionViewError(Modifier.fillMaxHeight())
        }
    }
}

@Composable
private fun RecipeCollectionViewLoaded(
    state: RecipeCollectionViewState.Loaded,
    onItemClick: (item: RecipeItem) -> Unit,
    onSettingsClick: () -> Unit,
    onRefresh: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(onSettingsClick)
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.isRefreshing),
            onRefresh = { onRefresh() },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.MarginSmall100),
            ) {
                itemsIndexed(state.recipes) { index, item ->
                    RecipeCollectionItem(
                        modifier = if (index == state.recipes.lastIndex) {
                            Modifier.navigationBarsPadding()
                        } else {
                            Modifier
                        },
                        recipe = item,
                        index = index,
                        onItemClick = onItemClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    onSettingsClick: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth().statusBarsPadding()) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(Spacing.MarginNormal100),
            text = stringResource(id = R.string.recipe_collection_title),
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                fontSize = FontSize.TitleHeadLineNormal100,
            )
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { onSettingsClick() }
        ) {
            Icon(
                modifier = Modifier.padding(Spacing.MarginNormal100),
                imageVector = Icons.Default.Settings,
                contentDescription = "",
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RecipeCollectionItem(
    modifier: Modifier = Modifier,
    recipe: RecipeItem,
    index: Int,
    onItemClick: (item: RecipeItem) -> Unit,
) {
    val newModifier = if (index % 2 == 0) {
        modifier.padding(
            start = Spacing.MarginNormal100,
            end = Spacing.MarginNormal100 / 2,
            bottom = Spacing.MarginNormal100,
        )
    } else {
        modifier.padding(
            start = Spacing.MarginNormal100 / 2,
            end = Spacing.MarginNormal100,
            bottom = Spacing.MarginNormal100,
        )
    }

    Card(
        modifier = newModifier,
        onClick = { onItemClick(recipe) },
        elevation = 2.dp
    ) {
        Column {
            ImageOrPlaceholder(recipe.imgPath)
            Text(
                text = recipe.title,
                modifier = Modifier
                    .padding(Spacing.MarginSmall100)
                    .sizeIn(minHeight = (2.lineHeight()).dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Composable
private fun ImageOrPlaceholder(imgPath: String?) {
    if (imgPath != null) {
        Image(
            painter = rememberImagePainter(
                ImageResolver.mountUrl(imgPath, ImageSize.MEDIUM),
                builder = {
                    size(OriginalSize)
                    placeholder(R.drawable.recipe_item_placeholder)
                    error(R.drawable.recipe_item_placeholder)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(RecipeImgRatio),
            contentScale = ContentScale.Crop,
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(RecipeImgRatio),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.recipe_item_placeholder),
                contentDescription = null,
                modifier = Modifier.fillHalfSize(),
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Composable
private fun RecipeCollectionViewLoading(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}

@Composable
private fun RecipeCollectionViewError(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSError()
    }
}
