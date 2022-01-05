package br.com.recipebook.designsystem.compose.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
@Suppress("MagicNumber")
fun Int.lineHeight() = this * MaterialTheme.typography.body1.fontSize.value * 4 / 3
