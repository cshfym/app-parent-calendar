package com.parentcalendar.controller.admin

import com.parentcalendar.domain.security.User
import com.parentcalendar.services.data.CalendarDataService
import com.parentcalendar.services.data.UserDataService
import org.apache.commons.lang.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class AdminController {

    @Autowired
    UserDataService userDataService

    @Autowired
    CalendarDataService calendarDataService

    def index() {

        def allCalendars = []
        try {
            allCalendars = calendarDataService.getAllCalendars()
        } catch (Exception ex) {
            println ex
        }

        [users: userDataService.getAllUsers(), calendars: allCalendars]
    }

    def createUser = {

        def randomName = "test_${getRandomString(5)}"

        // GORM
        def user = new User(username: randomName, password: 'test', email: "${randomName}@email.com")
        user.save(flush: true)

        userDataService.flushCache()

        render (template: "adminUserList", model: [ users: userDataService.getAllUsers() ])
    }

    def createCalendarForUser = {
        calendarDataService.createCalendar(Long.parseLong(params.userId), params.description)
        render (template: "adminCalendarList", model: [ calendars: calendarDataService.getAllCalendars() ])
    }

    def deleteCalendar = {
        calendarDataService.deleteCalendar(Long.parseLong(params.calendarId))
        render (template: "adminCalendarList", model: [ calendars: calendarDataService.getAllCalendars() ])
    }

    def deleteUser = {

        // GORM
        def user = User.find { id == params.userId }
        user.delete(flush: true)

        userDataService.flushCache()

        render (template: "adminUserList", model: [ users: userDataService.getAllUsers() ])
    }

    // TODO Move to utils class.
    def getRandomString(int len) {
        RandomStringUtils.random(len, (("a".."z") + ("0".."9")).join().toCharArray())
    }
}
