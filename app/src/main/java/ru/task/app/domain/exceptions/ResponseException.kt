package ru.task.app.domain.exceptions

open class ResponseException(override val message: String) : Exception(message)