package ru.task.app.presentation.common.base.livedata

import ru.task.app.presentation.common.base.livedata.StateData.Status.*

class StateData<out T> private constructor(
    val status: Status,
    val data: T?,
    val error: Exception?
) {
    enum class Status { SUCCESS, ERROR, LOADING }
    companion object {
        fun <T> success(data: T?) = StateData(SUCCESS, data, null)
        fun loading() = StateData(LOADING, null, null)
        fun error(error: Exception?) = StateData(ERROR, null, error)
    }

    override fun toString(): String = "{status: ${status.name}, data: $data, error: $error}"
}