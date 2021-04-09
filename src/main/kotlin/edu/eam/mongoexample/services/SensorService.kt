package edu.eam.mongoexample.services

import edu.eam.mongoexample.exceptions.BusinessException
import edu.eam.mongoexample.model.Channel
import edu.eam.mongoexample.model.Sensor
import edu.eam.mongoexample.repositories.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

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

    fun addChannel(sensorId: String, channel: Channel): Sensor {
        val sensor = sensorRepository.findById(sensorId)
            .orElseThrow { throw EntityNotFoundException("sensor not found") }

        sensor.channels.add(channel);

        return sensorRepository.save(sensor);
    }

    fun findSensor(id: String) = sensorRepository.findById(id)
}