package ru.task.app.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
data class ContactVO(
    val id: String,
    val name: String,
    val height: Float,
    val phone: String,
    val biography: String,
    val temperament: Temperament,
    val educationPeriod: EducationPeriod
) : Parcelable {

    enum class Temperament(val key: String) {
        MELANCHOLIC("m"),
        PHLEGMATIC("p"),
        SANGUINE("s"),
        CHOLERIC("c")
    }

    @Parcelize
    data class EducationPeriod(
        val end: DateTime,
        val start: DateTime
    ) : Parcelable
}