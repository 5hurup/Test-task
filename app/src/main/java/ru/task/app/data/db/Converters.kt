package ru.task.app.data.db

import androidx.room.TypeConverter
import org.joda.time.DateTime
import ru.task.app.domain.entity.ContactVO.Temperament
import java.lang.IllegalArgumentException

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String): DateTime = DateTime(value)

    @TypeConverter
    fun dateToTimestamp(date: DateTime): String = date.toString()

    @TypeConverter
    fun fromTemperament(value: Temperament): String = value.key

    @TypeConverter
    fun stringToTemperament(temperament: String): Temperament = Temperament.values().find { it.key == temperament} ?: throw IllegalArgumentException()
}