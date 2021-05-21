package edu.eam.mongoexample.security.authclient

import edu.eam.mongoexample.security.authclient.model.SecurityPayload
import edu.eam.mongoexample.security.authclient.model.Token
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "SecurityClient", url = "\${externalapi.security-ms}/api/people/security")
interface SecurityClient {
    @RequestMapping(method = [RequestMethod.POST], path = ["/validate-token"])
    fun validateToken(@RequestBody token: Token): SecurityPayload
}
