package com.parentcalendar.controller.admin

import com.parentcalendar.domain.enums.SecurityRoles
import com.parentcalendar.domain.security.Role
import com.parentcalendar.domain.security.User
import com.parentcalendar.domain.security.UserRole
import com.parentcalendar.services.data.CalendarDataService
import com.parentcalendar.services.data.CoreUserDataService
import com.parentcalendar.services.security.UserTokenService
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class AdminController {

    @Autowired
    CoreUserDataService coreUserDataService

    @Autowired
    CalendarDataService calendarDataService

    @Autowired
    UserTokenService userTokenService

    def index() {

        def allCalendars
        try {
            allCalendars = calendarDataService.getAllCalendars(null, true)
        } catch (Exception ex) {
            handleException(ex, "")
            return
        }

        def allUsers
        try {
            allUsers = coreUserDataService.getAllUsers(true)
        } catch (Exception ex) {
            handleException(ex, "")
            return
        }

        [users: allUsers, calendars: allCalendars]
    }

    private void handleException(Exception e, String message) {
        flash.message = message
        String eMessage = ExceptionUtils.getRootCauseMessage(e)
        log.error eMessage, e
        redirect controller: "login", action: "auth"
    }

    def createUser = {

        def randomName = "test_${getRandomString(5)}"

        // GORM
        def user = new User(username: randomName, password: 'test', email: "${randomName}@email.com")
        user.save(flush: true)

        // Attach a role.
        def role = Role.find { authority == SecurityRoles.ROLE_USER.name }
        def userRole = new UserRole(user: user, role: role)
        userRole.save(flush: true)

        coreUserDataService.flushCache("getAllUsers")

        calendarDataService.createCalendar(user.id, true, "Default Calendar")

        calendarDataService.flushCache("getAllCalendars")

        render (template: "adminUserList", model: [ users: coreUserDataService.getAllUsers() ])
    }

    def createCalendarForUser = {
        calendarDataService.createCalendar(Long.parseLong(params.userId), false, params.description)
        render (template: "adminCalendarList", model: [ calendars: calendarDataService.getAllCalendars(null, true) ])
    }

    def deleteCalendar = {
        calendarDataService.deleteCalendar(Long.parseLong(params.calendarId))
        render (template: "adminCalendarList", model: [ calendars: calendarDataService.getAllCalendars(null, true) ])
    }

    def deleteUser = {

        // GORM
        def user = User.find { id == params.userId }

        // Delete attached roles.
        def userRoles = UserRole.findAll { user == user }
        userRoles.each {
            it.delete(flush: true)
        }

        // Delete attached calendars.
        def userCalendars = calendarDataService.getAllCalendars(false, user.id, true)
        userCalendars.each {
            calendarDataService.deleteCalendar(it.id)
        }

        // Delete token.
        userTokenService.deleteUserTokens(user)

        user.delete(flush: true)

        coreUserDataService.flushCache("getAllUsers")
        calendarDataService.flushCache("getAllCalendars")

        render (
            template: "adminUserList",
            model: [ users: coreUserDataService.getAllUsers(), calendars: calendarDataService.getAllCalendars(null, true) ]
        )
    }

    // TODO Move to utils class.
    def getRandomString(int len) {
        RandomStringUtils.random(len, (("a".."z") + ("0".."9")).join().toCharArray())
    }
}
