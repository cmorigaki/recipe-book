package br.com.recipebook.designsystem.compose

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import br.com.recipebook.designsystem.R

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(Font(R.font.montserrat))
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
