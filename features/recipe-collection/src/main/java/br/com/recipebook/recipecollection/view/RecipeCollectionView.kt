package br.com.recipebook.recipecollection.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import br.com.recipebook.coreandroid.image.ImageResolver
import br.com.recipebook.coreandroid.image.ImageSize
import br.com.recipebook.designsystem.compose.FontSize
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.designsystem.compose.textColorPrimary
import br.com.recipebook.recipecollection.R
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewState
import coil.compose.rememberImagePainter
import coil.size.OriginalSize

@Composable
fun RecipeCollectionView(
    state: RecipeCollectionViewState,

    ) {
    when (state) {
        is RecipeCollectionViewState.Loaded -> RecipeCollectionViewLoaded(state)
        RecipeCollectionViewState.Loading -> RecipeCollectionViewLoading(Modifier.fillMaxHeight())
        RecipeCollectionViewState.Error -> RecipeCollectionViewError(Modifier.fillMaxHeight())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeCollectionViewLoaded(
    state: RecipeCollectionViewState.Loaded
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f).padding(Spacing.MarginNormal100),
                text = stringResource(id = R.string.recipe_collection_title),
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = FontSize.TitleHeadLineNormal100,
                )
            )
            Image(
                modifier = Modifier.padding(Spacing.MarginNormal100),
                painter = painterResource(id = android.R.drawable.ic_menu_preferences),
                contentDescription = null,
            )
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.MarginSmall100),
        ) {
            itemsIndexed(state.recipes) { index, item ->
                RecipeCollectionItem(
                    recipe = item,
                    index = index,
                )
            }
        }
    }
}

@Composable
fun RecipeCollectionItem(
    recipe: RecipeItem,
    index: Int,
) {
    val modifier = if (index % 2 == 0) {
        Modifier.padding(
            start = Spacing.MarginNormal100,
            end = Spacing.MarginNormal100,
            bottom = Spacing.MarginNormal100,
        )
    } else {
        Modifier.padding(end = Spacing.MarginNormal100, bottom = Spacing.MarginNormal100)
    }
    Card(modifier = modifier) {
        Column {
            Image(
                painter = rememberImagePainter(
                    ImageResolver.mountUrl(recipe.imgPath, ImageSize.MEDIUM),
                    builder = {
                        size(OriginalSize)
                        placeholder(R.drawable.recipe_item_placeholder)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(4f / 3f),
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.padding(Spacing.MarginSmall100),
                text = recipe.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun RecipeCollectionViewLoading(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}

@Composable
fun RecipeCollectionViewError(modifier: Modifier) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        DSLoading()
    }
}
