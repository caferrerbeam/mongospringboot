package edu.eam.mongoexample.security

import edu.eam.mongoexample.security.authclient.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecurityInterceptor : HandlerInterceptor {

    @Autowired
    lateinit var securityService: SecurityService

    @Throws(Exception::class)
    fun getToken(request: HttpServletRequest): String {
        val bearer = request.getHeader("Authorization") ?: throw SecurityException("token required")

        if (!bearer.startsWith(prefix = "Bearer ")) {
            throw SecurityException("invalid token")
        }

        return bearer.substring(7)
    }

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            //extrayendo del handler la anotacion secured.
            val securityInfo: Secured = handler.method.getAnnotation(Secured::class.java) ?: return true

            if (securityInfo!=null) {
                //si el flujo del codigo llego hasta aca, es porque el handler esta anotado con secured
                val token = getToken(request)

                val securityPayload = securityService.validateToken(token)
                val userPermissions = securityPayload.permissions

                val handlerPermissions = securityInfo.permissions

                if (handlerPermissions.isEmpty()) {
                    return true;
                }

                if (handlerPermissions.intersect(userPermissions).isEmpty()) {
                    throw SecurityException("this user has no access")
                }
            }

        }
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest, response: HttpServletResponse,
        handler: Any, modelAndView: ModelAndView?
    ) {
        println("Inside the Post Handle method")
    }

    @Throws(Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        exception: Exception?
    ) {
        println("After completion of request and response")
    }
}