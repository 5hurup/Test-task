package ru.task.app.data.network.api.converters

import com.google.gson.*
import org.joda.time.DateTime
import java.lang.reflect.Type

class DateTimeConverter() : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
    //Not used, feel free to fix realization of this method
    override fun serialize(src: DateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
        JsonPrimitive(src.toString())

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime? =
        DateTime(json?.asString)
}