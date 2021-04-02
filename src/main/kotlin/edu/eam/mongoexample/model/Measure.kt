package edu.eam.mongoexample.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date
import java.util.UUID

@Document(collection = "measures")
data class Measure (
    @Id
    val id: String?,

    val sensorId: String,

    val channelNumber: Int,

    val measure: Double,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val takenAt: Date = Date(),
)