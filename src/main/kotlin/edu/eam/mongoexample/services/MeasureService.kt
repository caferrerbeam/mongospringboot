package edu.eam.mongoexample.services

import edu.eam.mongoexample.exceptions.BusinessException
import edu.eam.mongoexample.model.Measure
import edu.eam.mongoexample.repositories.MeasureRepository
import edu.eam.mongoexample.repositories.SensorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException


@Service
class MeasureService {

    @Autowired
    lateinit var sensorRepository: SensorRepository

    @Autowired
    lateinit var measureRepository: MeasureRepository

    fun createMeasure(measure: Measure) {
        val sensorId = measure.sensorId
        val channelNumber = measure.channelNumber
        val measureValue = measure.measure

        val sensor = sensorRepository.findById(sensorId)
            .orElseThrow { throw EntityNotFoundException("sensor not found") }

        val channel = if (channelNumber < sensor.channels.size) sensor.channels[channelNumber]
        else throw BusinessException("channel not found")

        if (measureValue> channel.max && measureValue < channel.min)
            throw BusinessException("measure value not in range")

        measureRepository.save(measure)
    }

    fun measuresBySensorAndChannel(sensorId: String, channelNumber: Int, pageable: org.springframework.data.domain.Pageable) =
        measureRepository.findBySensorIdAndChannelNumber(sensorId, channelNumber, pageable)

    fun measuresBySensorAndDates(sensorId: String, from: Date, to: Date, pageable: Pageable) =
        measureRepository.findSensorMeasuresBetweenDates(sensorId, from, to, pageable)
}