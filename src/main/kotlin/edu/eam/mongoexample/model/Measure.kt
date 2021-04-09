package edu.eam.mongoexample.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalTime
import java.util.Date

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