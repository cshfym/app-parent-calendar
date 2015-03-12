package com.parentcalendar.controller.auth

import com.parentcalendar.services.data.CoreUserDataService
import com.parentcalendar.services.data.UserDataService
import org.springframework.beans.factory.annotation.Autowired


class RegistrationController {

    @Autowired
    CoreUserDataService coreUserDataService

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
        def user

        try {
            user = userDataService.findByUsername(params.username as String)
        } catch (Exception ex) {
            render ""  // TODO How to handle this via ajax?
            return
        }

        if (!user) {
            render ""
            return
        }

        render "Username \"${params.username}\" is not available."
    }

    def checkEmail = {

        if (!params.email || params.email.toString().trim().isEmpty()) {
            render ""
            return
        }

        /**
         * Checks for existing username - no user auth required.
         */
        def user

        try {
            user = userDataService.findByEmail(params.email as String)
        } catch (Exception ex) {
            render ""  // TODO How to handle this via ajax?
            return
        }

        if (!user) {
            render ""
            return
        }

        render "The specified email address is associated with another user."
    }
}
