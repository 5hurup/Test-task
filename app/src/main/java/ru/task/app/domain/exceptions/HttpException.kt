package ru.task.app.domain.exceptions

class HttpException(val code: Int, override val message: String) : ResponseException(message)