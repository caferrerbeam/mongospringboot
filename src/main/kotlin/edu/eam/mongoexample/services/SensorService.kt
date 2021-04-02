package edu.eam.mongoexample.services

import edu.eam.mongoexample.exceptions.BusinessException
import edu.eam.mongoexample.model.Sensor
import edu.eam.mongoexample.repositories.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SensorService {

    @Autowired
    lateinit var sensorRepository: SensorRepository

    fun createSensor(sensor: Sensor) {
        if(sensor.channels.isEmpty()) {
            throw BusinessException("channels are empty")
        }

        sensorRepository.save(sensor)
    }
}