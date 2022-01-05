package br.com.recipebook.designsystem.compose.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.recipebook.designsystem.R

@Composable
fun DSLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.loading_icon),
        contentDescription = stringResource(id = R.string.loading),
    )
}
