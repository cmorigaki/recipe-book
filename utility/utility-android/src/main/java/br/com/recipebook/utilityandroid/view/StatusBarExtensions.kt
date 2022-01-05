package br.com.recipebook.utilityandroid.view

import android.app.Activity
import android.content.res.Resources
import android.graphics.Rect

fun Activity.getStatusBarHeight(): Float {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    val pixelSize = if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else Rect().apply { window.decorView.getWindowVisibleDisplayFrame(this) }.top
    return pixelSize / Resources.getSystem().displayMetrics.density
}
