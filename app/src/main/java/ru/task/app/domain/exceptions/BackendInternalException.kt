package ru.task.app.domain.exceptions

open class BackendInternalException(
    override val message: String
) : ResponseException(message)