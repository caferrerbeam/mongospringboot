package edu.eam.mongoexample.repositories

import edu.eam.mongoexample.model.Measure
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MeasureRepository : MongoRepository<Measure, String>{

    fun findBySensorIdAndChannelNumber(sensorId: String, channelNumber:Int, pageable: Pageable): List<Measure>

    @Query("{sensorId: ?0, takenAt: { \$gte: ?1, \$lt: ?2 }}")
    fun findSensorMeasuresBetweenDates(sensorId: String, from:Date, to:Date, pageable: Pageable): Page<Measure>
}