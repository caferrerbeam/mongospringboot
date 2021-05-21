package edu.eam.mongoexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class MongoexampleApplication

fun main(args: Array<String>) {
	runApplication<MongoexampleApplication>(*args)
}
