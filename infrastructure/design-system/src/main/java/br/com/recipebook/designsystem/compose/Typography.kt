package br.com.recipebook.designsystem.compose

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    /* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)

object FontSize {
    val TitleHeadLineNormal100 = 24.sp
    val TitleToolbarNormal100 = 18.sp

    val ItemTitleNormal100 = 20.sp
    val ItemTitleNormal125 = 25.sp
    val ItemTitleLarge100 = 40.sp

    val ItemNormal100 = 16.sp
    val ItemNormal125 = 20.sp
    val ItemLarge100 = 32.sp
}
