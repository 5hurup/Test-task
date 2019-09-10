package ru.task.app.presentation.common.base.fragment

import androidx.fragment.app.Fragment
import ru.task.app.presentation.common.base.WithLoadingIndicator

fun Fragment.delegateLoadingToParent(loading: Boolean) =
    ((parentFragment as? WithLoadingIndicator) ?: (activity as? WithLoadingIndicator))?.enableLoading(loading) ?: Unit