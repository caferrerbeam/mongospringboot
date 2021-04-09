package edu.eam.mongoexample.controllers

import CommonTests
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class SensorControllerTest: CommonTests() {

    @Test
    fun findSensorTest() {
        fillCollection("sensors", "/data/sensorControllerTest/findSensor.json")

        val request = MockMvcRequestBuilders.get("/sensors/1234")
            .contentType(MediaType.APPLICATION_JSON)
        val response = mockMvc.perform(request)

        response.andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.`is`("humedad")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels",Matchers.hasSize<Int>(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels[0].min",Matchers.`is`(0.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels[0].max",Matchers.`is`(5.0)))
    }

    @Test
    fun createSensorTest() {
        val sensorId = "606c5c7cef274e2f25d760af"
        val body = """{
                    "id": "$sensorId",
                    "name": "humedad",
                    "channels": [
                        {
                            "max": 5.0,
                            "min": 0.0,
                            "number": 0
                        },
                        {
                            "max": 5.0,
                            "min": 0.0,
                            "number": 1
                        }
                    ]
                }"""

        val request = MockMvcRequestBuilders.post("/sensors/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        val response = mockMvc.perform(request)

        response.andExpect(MockMvcResultMatchers.status().isOk)

        val sensor = sensorRepository.findById(sensorId).get()
        Assertions.assertEquals("humedad", sensor.name)
        Assertions.assertEquals(2, sensor.channels.size)
    }

    @Test
    fun getMeasuresBySensorIdAndChannelId() {
        fillCollection("sensors", "/data/sensorControllerTest/getMeasuresBySensorIdAndChannelId_sensors.json")
        fillCollection("measures", "/data/sensorControllerTest/getMeasuresBySensorIdAndChannelId_measures.json")

        val request = MockMvcRequestBuilders.get("/sensors/{sensorId}/channels/{channelNumber}/measures",1234, 0)
            .contentType(MediaType.APPLICATION_JSON)
        val response = mockMvc.perform(request)

        response.andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize<Int>(3)))

    }

    @Test
    fun addChannelToSensorTest() {
        fillCollection("sensors", "/data/sensorControllerTest/addChannelToSensorTest.json")

        val sensorId = "1234"
        val body = """{
                    "max":20,
                    "min":3,
                    "number":4 
                   }"""

        val request = MockMvcRequestBuilders.put("/sensors/$sensorId/channels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        val response = mockMvc.perform(request)

        response.andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.`is`("temperature")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels",Matchers.hasSize<Int>(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels[0].min",Matchers.`is`(0.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels[0].max",Matchers.`is`(5.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels[1].min",Matchers.`is`(3.0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.channels[1].max",Matchers.`is`(20.0)))

        val sensor = sensorRepository.findById(sensorId).get()

        val channel = sensor.channels[1];
        Assertions.assertEquals(20.0, channel.max)
        Assertions.assertEquals(3.0, channel.min)
    }
}