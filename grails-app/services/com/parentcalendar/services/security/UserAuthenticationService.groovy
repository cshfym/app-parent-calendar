package com.parentcalendar.services.security

import com.parentcalendar.domain.security.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class UserAuthenticationService {

    public User getPrincipal() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication()

        if (authentication instanceof UsernamePasswordAuthenticationToken) {

        }
    }
}
