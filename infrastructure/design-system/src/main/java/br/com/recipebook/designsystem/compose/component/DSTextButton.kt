package br.com.recipebook.designsystem.compose.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.recipebook.designsystem.compose.Spacing
import br.com.recipebook.designsystem.compose.textColorPrimary

@Composable
fun DSTextButton(
    modifier: Modifier,
    @StringRes textResId: Int,
    onClick: () -> Unit,
) {
    DSTextButton(
        modifier = modifier,
        text = stringResource(id = textResId),
        onClick = onClick,
    )
}

@Composable
fun DSTextButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        contentPadding = PaddingValues(Spacing.MarginNormal100),
        onClick = { onClick() },
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colors.textColorPrimary,
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
