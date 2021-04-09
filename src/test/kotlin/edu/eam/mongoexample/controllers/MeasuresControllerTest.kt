package edu.eam.mongoexample.controllers

import CommonTests
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class MeasuresControllerTest: CommonTests() {

    @Test
    fun createMeasure() {
        fillCollection("sensors", "/data/measuresControllerTest/createMeasure.json")

        val sensorId = "606bd277cf6fd961f78436a1"

        val body = """
            {
                "sensorId":"$sensorId",
                "channelNumber": 0,
                "measure": "1"
            }
        """.trimIndent()

        val request = MockMvcRequestBuilders.post("/measures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        val response = mockMvc.perform(request)

        response.andExpect(MockMvcResultMatchers.status().isOk)

        val measure = measureRepository.findAll().first()

        Assertions.assertEquals(sensorId, measure.sensorId)
        Assertions.assertEquals(0, measure.channelNumber)
        Assertions.assertEquals(1.0, measure.measure)
        Assertions.assertNotNull(measure.takenAt)
    }

}