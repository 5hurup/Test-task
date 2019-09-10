package ru.task.app.presentation.common.base.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import ru.task.app.presentation.common.base.fragment.BaseFragment

open class LiveStateData<T : Any> : LiveData<StateData<T>>() {
    fun observe(owner: LifecycleOwner, observer: BaseFragment.StateDataProcessor<T>) {
        super.observe(owner, observer)
    }
}