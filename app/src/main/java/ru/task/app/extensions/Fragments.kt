package ru.task.app.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.task.app.R

fun Fragment.showSnackbar(message: String) =
    Snackbar.make(
        this.requireView(),
        message,
        Snackbar.LENGTH_LONG
    ).show()

fun Fragment.showSnackbar(exception: Exception, retryCallback: (() -> Unit)?) =
    Snackbar.make(
        this.requireView(),
        exception.message ?: getString(R.string.common_error),
        Snackbar.LENGTH_LONG
    ).apply {
        retryCallback?.also { callback -> setAction("RETRY") { callback.invoke() } }
    }.show()