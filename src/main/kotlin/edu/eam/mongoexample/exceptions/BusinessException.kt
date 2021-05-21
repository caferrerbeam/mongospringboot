package edu.eam.mongoexample.exceptions

import org.springframework.http.HttpStatus

class BusinessException(message: String?, val status: HttpStatus = HttpStatus.PRECONDITION_FAILED) :
    RuntimeException(message)
