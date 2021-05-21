package edu.eam.mongoexample.handlers

data class ErrorResponse(
    val status: Int? = 500,
    val message: String? = "Exception"
)
