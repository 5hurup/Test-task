package ru.task.app.presentation.common.base.livedata

import timber.log.Timber

class MutableLiveStateData<T : Any> : LiveStateData<T>() {
    fun setError(error: Exception) {
        Timber.e(error)
        value = StateData.error(error)
    }

    fun setData(data: T) {
        value = StateData.success(data)
    }

    fun setLoading() {
        value = StateData.loading()
    }
}