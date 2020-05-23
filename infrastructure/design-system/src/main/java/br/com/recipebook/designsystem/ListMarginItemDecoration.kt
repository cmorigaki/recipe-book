package br.com.recipebook.designsystem

import android.content.res.Resources
import br.com.recipebook.navigation.R
import br.com.recipebook.utilityandroid.MarginItemDecoration

class ListMarginItemDecoration(resources: Resources, spanCount: Int = 1) :
    MarginItemDecoration(
        space = resources.getDimensionPixelSize(R.dimen.margin_normal_100),
        spanCount = spanCount
    )