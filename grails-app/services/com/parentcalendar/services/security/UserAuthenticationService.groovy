package com.parentcalendar.services.security

import com.parentcalendar.domain.security.User
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.request.RequestContextHolder

@Transactional
@Component

class UserAuthenticationService {

    private static final log = LogFactory.getLog(this)

    def grailsApplication

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder

    /**
     * Returns a unique user token derived from user ID, session ID, and system token value
     * @return String as unique user token.
     */
    public String getUserTokenString() {

        if (!SecurityContextHolder.context.authentication?.authenticated) { return null }

        String sessionId = RequestContextHolder.currentRequestAttributes().sessionId

        // Encoded user ID + unique session ID + shared application token.
        (SecurityContextHolder.context.authentication.principal.id + "|" +
                sessionId + "|" +
                grailsApplication.config.authentication.token).encodeAsBase64()
    }

    /**
     * Convenience method to return the User object from the currently authenticated user.
     * @return
     */
    public User getUser() {
        User.find { id == userId }
    }

    /**
     * Convenience method to return the ID for the currently authenticated user.
     * @return Long
     */
    public Long getUserId() {
        if (!SecurityContextHolder.context.authentication?.principal) { return null }
        SecurityContextHolder.context.authentication.principal.id
    }

    /*
    public String getSessionId() {
        if (!SecurityContextHolder.context.authentication?.details?.sessionId) { return null }
        SecurityContextHolder.context.authentication?.details?.sessionId
    }
      */

    /**
     * Convenience method to return the currently authenticated username.
     * @return Long
     */
    public Long getUsername() {
        if (!SecurityContextHolder.context.authentication?.principal) { return null }
        SecurityContextHolder.context.authentication.principal.username
    }
}
