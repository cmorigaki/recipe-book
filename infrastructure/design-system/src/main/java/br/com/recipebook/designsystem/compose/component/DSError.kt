package br.com.recipebook.designsystem.compose.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.recipebook.designsystem.R

@Composable
fun DSError(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.recipe_error_state),
        contentDescription = stringResource(id = R.string.error),
    )
}
