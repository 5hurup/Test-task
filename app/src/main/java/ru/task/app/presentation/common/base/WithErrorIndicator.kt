package ru.task.app.presentation.common.base

import java.lang.Exception

interface WithErrorIndicator {
    fun showError(exception: Exception, retryCallback: (() -> Unit)? = null)
}