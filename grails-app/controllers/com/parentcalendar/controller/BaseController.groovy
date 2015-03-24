package com.parentcalendar.controller

import com.parentcalendar.domain.exception.DataCommunicationException
import com.parentcalendar.domain.security.User
import com.parentcalendar.domain.security.UserToken
import com.parentcalendar.services.security.UserTokenService
import org.apache.commons.lang.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder


class BaseController {

    @Autowired
    UserTokenService tokenService

    def beforeInterceptor = {

        if (!session["userToken"]) {
            def token = tokenService.token
            if (token) {
                session["userToken"] = token
            } else {
                response.setStatus(500)
                println "No token."
                return
            }
        }

        // Validate session token and refresh if needed.
        def token = session["userToken"] as UserToken
        if (tokenService.isExpired(token.issued)) {
            session["userToken"] = tokenService.refreshToken(token)
        }
    }

    User getSessionUser() {

        if (!session["userToken"]) { return null }

        UserToken token = (UserToken) session["userToken"]

        if (!token.user) { return null }

        token.user
    }

    def handleException(Exception ex) {

        // TODO Make this more client friendly.

        def eMessage = ""

        if (ex.getCause() instanceof DataCommunicationException) {
            eMessage = "Could not communicate with data services. Please contact the system administrator if this error persists."
        } else {
            eMessage = ExceptionUtils.getRootCauseMessage(ex)
        }

        flash.message = eMessage
        log.error eMessage
        SecurityContextHolder.clearContext()

        redirect controller: "login", action: "auth"
    }
}
