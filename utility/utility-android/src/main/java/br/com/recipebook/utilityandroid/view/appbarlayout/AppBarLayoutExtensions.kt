package br.com.recipebook.utilityandroid.view.appbarlayout

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

fun AppBarLayout.setCollapsedAndDisableScroll(recyclerView: RecyclerView) {
    // Collapse AppBar
    setExpanded(false)
    // Disable AppBar scroll from the recycler view
    ViewCompat.setNestedScrollingEnabled(recyclerView, false)
    // Disable AppBar scroll from the AppBar
    ((layoutParams as CoordinatorLayout.LayoutParams).behavior as AppBarLayout.Behavior)
        .setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })
}
