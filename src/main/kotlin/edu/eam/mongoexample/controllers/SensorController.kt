package edu.eam.mongoexample.controllers

import edu.eam.mongoexample.configs.Constans
import edu.eam.mongoexample.model.Sensor
import edu.eam.mongoexample.services.MeasureService
import edu.eam.mongoexample.services.SensorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RequestMapping("/sensors")
@RestController
class SensorController {

    @Autowired
    lateinit var sensorService: SensorService

    @Autowired
    lateinit var measureService: MeasureService

    @PostMapping
    fun create(@RequestBody sensor: Sensor) = sensorService.createSensor(sensor)

    @GetMapping("{sensorId}/channels/{channelNumber}")
    fun getMeasuresBySensorAndChannel(@PathVariable sensorId: String,
                                      @PathVariable channelNumber: Int,
                                      pageable: Pageable) =
        measureService.measuresBySensorAndChannel(sensorId, channelNumber, pageable)


    @GetMapping("{sensorId}")
    fun getMeasuresBySensorAndDates(@PathVariable sensorId: String,
                                    @DateTimeFormat(pattern = Constans.DATE_TIME_FORMAT) @RequestParam from: Date,
                                    @DateTimeFormat(pattern = Constans.DATE_TIME_FORMAT) @RequestParam to: Date,
                                    pageable: Pageable) =
        measureService.measuresBySensorAndDates(sensorId, from, to, pageable)
}