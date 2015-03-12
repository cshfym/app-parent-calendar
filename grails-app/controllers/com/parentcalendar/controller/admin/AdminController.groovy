package com.parentcalendar.controller.admin

import com.parentcalendar.domain.enums.SecurityRoles
import com.parentcalendar.domain.security.Role
import com.parentcalendar.domain.security.User
import com.parentcalendar.domain.security.UserRole
import com.parentcalendar.services.data.CalendarDataService
import com.parentcalendar.services.data.CoreUserDataService
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

    def index() {

        def allCalendars
        try {
            allCalendars = calendarDataService.getAllCalendars(true)
        } catch (Exception ex) {
            handleException(ex, ex.getMessage())
            return
        }

        def allUsers
        try {
            allUsers = coreUserDataService.getAllUsers()
        } catch (Exception ex) {
            handleException(ex, ex.getMessage())
            return
        }

        [users: allUsers, calendars: allCalendars]
    }

    private void handleException(Exception e, String message) {
        flash.message = message
        String eMessage = ExceptionUtils.getRootCauseMessage(e)
        log.error eMessage, e
        redirect(action: "index")
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

        coreUserDataService.flushCache()

        render (template: "adminUserList", model: [ users: coreUserDataService.getAllUsers() ])
    }

    def createCalendarForUser = {
        calendarDataService.createCalendar(Long.parseLong(params.userId), params.description)
        render (template: "adminCalendarList", model: [ calendars: calendarDataService.getAllCalendars(true) ])
    }

    def deleteCalendar = {
        calendarDataService.deleteCalendar(Long.parseLong(params.calendarId))
        render (template: "adminCalendarList", model: [ calendars: calendarDataService.getAllCalendars(true) ])
    }

    def deleteUser = {

        // GORM
        def user = User.find { id == params.userId }
        user.delete(flush: true)

        coreUserDataService.flushCache()

        render (template: "adminUserList", model: [ users: coreUserDataService.getAllUsers() ])
    }

    // TODO Move to utils class.
    def getRandomString(int len) {
        RandomStringUtils.random(len, (("a".."z") + ("0".."9")).join().toCharArray())
    }
}
