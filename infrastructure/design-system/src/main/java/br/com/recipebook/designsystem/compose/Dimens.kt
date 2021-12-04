package br.com.recipebook.designsystem.compose

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Spacing {
    val MarginSmall100 = 6.dp
    val MarginNormal100 = 16.dp
    val MarginLarge100 = 30.dp
}

object Size {
    val IconSize = 36.dp
}

fun Modifier.minTouchHeight() = height(48.dp)
