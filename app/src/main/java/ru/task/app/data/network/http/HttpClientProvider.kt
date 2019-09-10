package ru.task.app.data.network.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.task.app.BuildConfig
import ru.task.app.data.network.http.HttpClient.Companion.OK_HTTP_TAG
import timber.log.Timber

internal class HttpClient {
    companion object {
        const val OK_HTTP_TAG = "OkHttp"

        fun getDefaultOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)

        inline fun build(block: OkHttpClient.Builder.() -> Unit): OkHttpClient =
            getDefaultOkHttpBuilder().apply(block).withLogging().build()
    }
}

private fun OkHttpClient.Builder.withLogging() =
    addInterceptor(HttpLoggingInterceptor { Timber.tag(OK_HTTP_TAG).d(it) }
        .setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        ))
