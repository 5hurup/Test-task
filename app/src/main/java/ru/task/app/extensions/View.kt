package ru.task.app.extensions

import android.view.View
import android.view.View.*

fun View.hide(state: Boolean = true) {
    visibility = if (state) GONE else VISIBLE
}

fun View.show(state: Boolean = true) {
    visibility = if (state) VISIBLE else GONE
}

fun View.invisible(state: Boolean = true) {
    visibility = if (state) INVISIBLE else VISIBLE
}