package ru.task.app.presentation.common.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Base ViewModel
 * - handle job with current сoroutines context and clear him on onCleared
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    @CallSuper
    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
