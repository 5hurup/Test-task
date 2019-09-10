package ru.task.app.domain.exceptions

class NoInternetException(
    override val message: String
) : ResponseException(message)