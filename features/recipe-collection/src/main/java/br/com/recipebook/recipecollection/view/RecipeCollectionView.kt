package br.com.recipebook.recipecollection.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.component.DSLoading
import br.com.recipebook.recipecollection.R
import br.com.recipebook.recipecollection.presentation.RecipeCollectionViewState

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
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.recipe_collection_title)
            )
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_preferences),
                contentDescription = null,
            )
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(Spacing.MarginSmall100),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.recipes) {
                RecipeCollectionItem(it)
            }
        }
    }
}

@Composable
fun RecipeCollectionItem(recipe: RecipeItem) {
    Card() {

    }
    Text(text = recipe.title!!, Modifier.height(500.dp))
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
