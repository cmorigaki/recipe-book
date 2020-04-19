package br.com.recipebook.utilityandroid

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val space: Int, private val spanCount: Int = 1) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) < spanCount) {
                top = space
            }
            if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                left = space
            }
            right = space
            bottom = space
        }
    }
}