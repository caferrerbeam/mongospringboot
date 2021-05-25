package edu.eam.mongoexample.security

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Secured(
    val permissions: Array<String> = arrayOf(),
    //val groups: Array<String> = arrayOf()
)
