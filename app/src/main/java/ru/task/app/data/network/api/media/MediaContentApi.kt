package ru.task.app.data.network.api.media

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import ru.task.app.data.entity.ContactDTO

interface MediaContentApi {
    @GET("master/json/{name}.json")
    fun getContactsAsync(@Path("name") filename: String): Deferred<List<ContactDTO>>
}