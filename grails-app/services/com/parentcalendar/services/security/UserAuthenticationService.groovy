package com.parentcalendar.services.security

import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component

class UserAuthenticationService {

    private static final log = LogFactory.getLog(this)

    def grailsApplication

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder

    public String getUserToken() {

      if (!SecurityContextHolder.context.authentication?.authenticated) { return null }

      String sessionId = SecurityContextHolder.context.authentication?.details?.sessionId as String
      if (!sessionId) {
          log.info "No sessionId available in SecurityContextHolder while calling getUserToken."
          return null
      }

      // Encoded user ID + unique session ID + shared application token.
      (SecurityContextHolder.context.authentication.principal.id + "|" +
        sessionId + "|" +
        grailsApplication.config.authentication.token).encodeAsBase64()
    }

    /**
     * Convenience method to return the currently authenticated user ID.
     * @return Long
     */
    public Long getUserId() {
      if (!SecurityContextHolder.context.authentication?.principal) { return null }
      SecurityContextHolder.context.authentication.principal.id
    }

    /**
     * Convenience method to return the currently authenticated username.
     * @return Long
     */
    public Long getUsername() {
        if (!SecurityContextHolder.context.authentication?.principal) { return null }
        SecurityContextHolder.context.authentication.principal.username
    }
}
