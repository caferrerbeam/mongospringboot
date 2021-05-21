package edu.eam.mongoexample.security.authclient.model

data class SecurityPayload(
    val permissions : List<String>,
    val user : String,
    val iat : Int,
    val exp : Int
)
