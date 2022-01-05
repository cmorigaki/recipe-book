package br.com.recipebook.designsystem.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.recipebook.designsystem.R
import br.com.recipebook.designsystem.compose.Spacing

@Composable
fun DSError(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(Spacing.MarginLarge100)) {
        Image(
            modifier = modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.recipe_error_state),
            contentDescription = stringResource(id = R.string.error),
        )
        Text(
            text = stringResource(id = R.string.error_state_generic_title),
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6,
        )
        Text(
            text = stringResource(id = R.string.error_state_generic_subtitle),
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}
