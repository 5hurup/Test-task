package ru.task.app.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime
import ru.task.app.domain.entity.ContactVO.Temperament

@Entity
data class Contact(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "biography") val biography: String,
    @ColumnInfo(name = "temperament") val temperament: Temperament,
    @ColumnInfo(name = "education_period_start") val educationPeriodStart: DateTime,
    @ColumnInfo(name = "education_period_end") val educationPeriodEnd: DateTime
)