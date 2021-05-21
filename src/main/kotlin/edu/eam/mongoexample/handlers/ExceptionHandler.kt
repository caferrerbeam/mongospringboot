package edu.eam.mongoexample.handlers

import edu.eam.mongoexample.exceptions.BusinessException
import edu.eam.mongoexample.security.SecurityException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFoundError(exception: EntityNotFoundException): ErrorResponse {
        exception.printStackTrace()
        return ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.message)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleError(exception: java.lang.Exception): ErrorResponse {
        exception.printStackTrace()
        return ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleBadRequestError(exception: HttpMessageNotReadableException): ErrorResponse {
        exception.printStackTrace()
        return ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    }

    @ResponseBody
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessError(exception: BusinessException): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        return ResponseEntity(ErrorResponse(exception.status.value(), exception.message), exception.status)
    }

    @ResponseBody
    @ExceptionHandler(SecurityException::class)
    fun handleSecurityError(exception: SecurityException): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        return ResponseEntity(ErrorResponse(403, exception.message), HttpStatus.FORBIDDEN)
    }
}
