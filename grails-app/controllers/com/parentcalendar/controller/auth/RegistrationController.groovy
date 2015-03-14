package com.parentcalendar.controller.auth

import com.parentcalendar.domain.security.Role
import com.parentcalendar.domain.security.UserRole
import com.parentcalendar.services.data.CalendarDataService
import com.parentcalendar.services.data.CoreUserDataService
import com.parentcalendar.services.data.UserDataService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.apache.commons.validator.EmailValidator
import org.springframework.beans.factory.annotation.Autowired


class RegistrationController {

    def springSecurityService

    @Autowired
    CoreUserDataService coreUserDataService

    @Autowired
    UserDataService userDataService

    @Autowired
    CalendarDataService calendarDataService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        }
    }

    def submitRegistration = {

        flash.validationErrors = []

        if (!params.username || (params.username.trim().length() < 6 || params.username.trim().length() > 20)) {
            flash.validationErrors << "Invalid username"
        }

        if (!params.email) {
            flash.validationErrors << "Please enter an email address"
        }

        def validEmail = EmailValidator.instance.isValid(params.email as String)
        if (!validEmail) {
            flash.validationErrors << "Please enter a valid email address"
        }

        if (userDataService.findByEmail(params.email as String)) {
            flash.validationErrors << "The specified email address is associated with another user"
        }

        if (!params.password || (params.password.trim().length() < 6)) {
            flash.validationErrors << "Password must be at least 6 characters"
        }

        if (!params.confirmPassword || (params.confirmPassword.trim() != params.password.trim())) {
            flash.validationErrors << "Passwords do not match"
        }

        if (flash.validationErrors) {
            flash.message = "Please correct the following errors:"
            // redirect (controller: "registration", action: "index")
            chain (controller: "registration", action: "index", model: [username: params.username, email: params.email])
            return
        } else {

        }

        // TODO Exception handling.
        // On-board: create User, UserRole, and default Calendar object.
        def user = createUser()
        createUserRole(user)
        createUserCalendar(user)

        redirect controller: "registrationComplete", action: "index"
    }

    def createUser() {

        userDataService.createUser([
            username: params.username.toString().trim(),
            email: params.email.toString().trim(),
            password: params.password.toString().trim()])

    }

    def createUserRole(def user) {
        def userRole = Role.find { authority == "ROLE_USER" }
        new UserRole(user: user, role: userRole).save(flush: true)
    }

    def createUserCalendar(def user) {
        calendarDataService.createCalendar(user.id as Long, true, "Default Calendar")
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
         * Checks for existing email - no user auth required.
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
