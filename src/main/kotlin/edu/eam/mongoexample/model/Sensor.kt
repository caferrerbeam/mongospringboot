package edu.eam.mongoexample.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sensors")
data class Sensor(
    @Id
    val id: String?,

    val name: String,

    var config: Map<String,String>?,

    val channels: MutableList<Channel>
)
