package com.parentcalendar.controller

import com.parentcalendar.services.security.UserTokenService
import org.springframework.beans.factory.annotation.Autowired


class BaseController {

    @Autowired
    UserTokenService tokenService

    def beforeInterceptor = {

        if (!session["userToken"]) {
            // No token in session - try DB?
            def token = tokenService.token
            if (token) {
                session["userToken"] = token
            } else {
                response.setStatus(500)
                println "Whoops!"
                return
            }
        }
    }
}
