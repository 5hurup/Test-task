package ru.task.app.presentation.common.base.fragment

import androidx.navigation.fragment.NavHostFragment
import ru.task.app.presentation.common.base.WithLoadingIndicator

class MyNavHostFragment : NavHostFragment(), WithLoadingIndicator {
    override fun enableLoading(state: Boolean) = delegateLoadingToParent(state)
}