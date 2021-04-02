package edu.eam.mongoexample.repositories

import edu.eam.mongoexample.model.Sensor
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SensorRepository: MongoRepository<Sensor, String> {
}