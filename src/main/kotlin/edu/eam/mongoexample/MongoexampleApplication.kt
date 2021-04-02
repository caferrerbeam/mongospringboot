package edu.eam.mongoexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongoexampleApplication

fun main(args: Array<String>) {
	runApplication<MongoexampleApplication>(*args)
}
