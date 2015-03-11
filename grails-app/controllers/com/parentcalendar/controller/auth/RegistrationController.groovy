package com.parentcalendar.controller.auth

import com.parentcalendar.domain.core.CoreUser
import com.parentcalendar.services.data.UserDataService
import org.springframework.beans.factory.annotation.Autowired

class RegistrationController {

    @Autowired
    UserDataService userDataService

    def index() {}

    def submitRegistration = {

        flash.message = "Test"

        redirect controller: "registration", action: "index"

    }

    def checkUsername = {

        if (!params.username || params.username.toString().trim().isEmpty()) {
            render ""
            return
        }

        /**
         * Checks for existing username - no user auth required.
         */
        def users = []

        try {
            userDataService.getBy("username", params.username as String, true)
        } catch (Exception ex) {
            render ""  // TODO How to handle this via ajax?
            return
        }

        if (!users) {
            render ""
            return
        }

        render "Username \"${params.username}\" is not available."
    }
}
