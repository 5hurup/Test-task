package ru.task.app.data.network.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.task.app.data.network.api.converters.DateTimeConverter

object ApiProvider {
    fun createGson(): Gson = GsonBuilder().registerTypeAdapter(DateTime::class.java, DateTimeConverter()).create()

    inline fun <reified S> createApiService(url: String, okHttpClient: OkHttpClient): S =
        Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .client(okHttpClient)
            .build()
            .create(S::class.java)
}