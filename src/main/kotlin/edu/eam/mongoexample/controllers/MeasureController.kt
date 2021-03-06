package edu.eam.mongoexample.controllers

import edu.eam.mongoexample.configs.Permission
import edu.eam.mongoexample.model.Measure
import edu.eam.mongoexample.model.Sensor
import edu.eam.mongoexample.security.Secured
import edu.eam.mongoexample.services.MeasureService
import edu.eam.mongoexample.services.SensorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/measures")
@RestController
class MeasureController {

    @Autowired
    lateinit var measureService: MeasureService

    @Secured(permissions = [
        Permission.CREATE_MEASURE_PERMISSION,
        Permission.ADMIN_SENSOR_PERMISSION],
    )
    @PostMapping
    fun create(@RequestBody measure: Measure) = measureService.createMeasure(measure)
}