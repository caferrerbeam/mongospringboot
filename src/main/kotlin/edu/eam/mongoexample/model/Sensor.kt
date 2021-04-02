package edu.eam.mongoexample.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "sensors")
data class Sensor(
    @Id
    val id: String?,

    val name: String,

    val config: Map<String,String>,

    val channels: List<Channel>
)
