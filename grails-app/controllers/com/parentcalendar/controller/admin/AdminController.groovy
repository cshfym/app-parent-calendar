package com.parentcalendar.controller.admin

import com.parentcalendar.services.data.CalendarDataService
import com.parentcalendar.services.data.UserDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN'])
class AdminController {

    @Autowired
    UserDataService userDataService

    @Autowired
    CalendarDataService calendarDataService

    def index() {

        def userList = userDataService.getAllUsers()
        def calendarList = calendarDataService.getAllCalendars()

        [ users: userList, calendars: calendarList ]
    }
}
