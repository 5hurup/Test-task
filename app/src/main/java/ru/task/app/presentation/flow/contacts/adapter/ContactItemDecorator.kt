package ru.task.app.presentation.flow.contacts.adapter

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import android.graphics.drawable.Drawable

class ContactItemDecorator(private val divider: Drawable, private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.top = padding
                outRect.bottom = padding / 2
                outRect.left = padding
                outRect.right = padding
            }
            parent.adapter?.itemCount -> {
                outRect.top = padding / 2
                outRect.bottom = padding / 2
                outRect.left = padding
                outRect.right = padding
            }
            else -> {
                outRect.top = padding / 2
                outRect.bottom = padding
                outRect.left = padding
                outRect.right = padding
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: State) {
        val dividerLeft = parent.paddingLeft + padding
        val dividerRight = parent.width - parent.paddingRight - padding

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)

            val dividerTop = child.bottom  + padding / 2
            val dividerBottom = dividerTop + divider.intrinsicHeight

            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            divider.draw(c)
        }
    }
}