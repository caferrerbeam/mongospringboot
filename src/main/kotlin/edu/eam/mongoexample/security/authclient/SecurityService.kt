package edu.eam.mongoexample.security.authclient

import edu.eam.mongoexample.security.authclient.model.SecurityPayload
import edu.eam.mongoexample.security.authclient.model.Token
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class SecurityService {

    @Autowired
    lateinit var securityClient: SecurityClient

    fun validateToken(token: String): SecurityPayload {
        try {
            return securityClient.validateToken(Token(token))
        } catch (exc: FeignException.Forbidden) {
            exc.printStackTrace()
            throw SecurityException("invalid token")
        } catch (exc: FeignException) {
            exc.printStackTrace()
            throw Exception("Unexpected Exception")
        }
    }
}