package ru.task.app.presentation.common.base.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.task.app.R
import ru.task.app.presentation.common.base.WithErrorIndicator
import ru.task.app.presentation.common.base.WithLoadingIndicator
import ru.task.app.presentation.common.base.livedata.StateData
import ru.task.app.presentation.common.base.livedata.StateData.Status.ERROR
import ru.task.app.presentation.common.base.livedata.StateData.Status.LOADING
import ru.task.app.presentation.common.base.livedata.StateData.Status.SUCCESS

abstract class BaseFragment : Fragment(), WithLoadingIndicator, WithErrorIndicator {
    override fun enableLoading(state: Boolean) = delegateLoadingToParent(state)

    override fun showError(exception: Exception, retryCallback: (() -> Unit)?) {
        Snackbar.make(requireView(), exception.message ?: getString(R.string.common_error), Snackbar.LENGTH_LONG)
            .apply {
                retryCallback?.also { callback -> setAction("RETRY") { callback.invoke() } }
            }.show()
    }

    inner class StateDataProcessor<K : Any>(
        private val onSuccess: (K) -> Unit,
        private val onError: ((Exception) -> Unit)? = null,
        private val onLoading: ((Boolean) -> Unit)? = null
    ) : Observer<StateData<K>> {

        override fun onChanged(stateData: StateData<K>) {
            when (stateData.status) {
                ERROR -> {
                    onLoading?.let { it(false) } ?: enableLoading(false)
                    stateData.error?.let { exception ->
                        onError?.let { it(exception) } ?: showError(exception, null)
                    }
                }
                LOADING -> onLoading?.let { it(true) } ?: enableLoading(true)
                SUCCESS -> {
                    onLoading?.let { it(false) } ?: enableLoading(false)
                    stateData.data?.let(onSuccess) ?: onError?.let { it(Exception(getString(R.string.common_error))) }
                    ?: showError(Exception(getString(R.string.common_error))) {}
                }
            }
        }
    }
}