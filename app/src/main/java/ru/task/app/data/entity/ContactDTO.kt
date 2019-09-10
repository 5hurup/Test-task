package ru.task.app.data.entity

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class ContactDTO(
    @SerializedName("biography")
    val biography: String,
    @SerializedName("educationPeriod")
    val educationPeriod: EducationPeriodDTO,
    @SerializedName("height")
    val height: Float,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("temperament")
    val temperament: TemperamentDTO
) {

    data class EducationPeriodDTO(
        @SerializedName("end")
        val end: DateTime,
        @SerializedName("start")
        val start: DateTime
    )

    enum class TemperamentDTO {
        @SerializedName("melancholic")
        MELANCHOLIC,
        @SerializedName("phlegmatic")
        PHLEGMATIC,
        @SerializedName("sanguine")
        SANGUINE,
        @SerializedName("choleric")
        CHOLERIC
    }
}