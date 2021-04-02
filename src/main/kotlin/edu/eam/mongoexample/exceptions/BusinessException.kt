package edu.eam.mongoexample.exceptions

import java.lang.RuntimeException

class BusinessException(message: String?) : RuntimeException(message) {

}