package com.parentcalendar.controller

import com.parentcalendar.domain.security.User
import com.parentcalendar.domain.ui.UICalendar
import com.parentcalendar.domain.ui.page.CalendarModel
import com.parentcalendar.services.data.CalendarDataService
import com.parentcalendar.services.data.CalendarEventDataService
import com.parentcalendar.services.security.UserAuthenticationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import java.text.SimpleDateFormat

@Secured(['ROLE_USER'])
class CalendarController extends BaseController {

    private static final log = LogFactory.getLog(this)

    @Autowired
    CalendarDataService service

    @Autowired
    CalendarEventDataService eventDataService

    @Autowired
    UserAuthenticationService authenticationService

    CalendarModel model

    def index() {

        model = new CalendarModel()

        model.uiCalendar = new UICalendar()

        try {
            model.userCalendars = service.getAllCalendars(sessionUser.id, false)
        } catch (Exception ex) {
            handleException(ex)
            model.userCalendars = []
        }

        [ pageModel: model ]
    }

    UICalendar getUICalendar() {
        if (!model.uiCalendar) {
            model.uiCalendar = new UICalendar()
        }
        model.uiCalendar
    }

    def changeCalendarYear = {

        getUICalendar().changeYear(Integer.parseInt(params.adjust))
        render (template: "monthView", model: [ pageModel: model ])
    }

    def changeCalendarMonth = {

        getUICalendar().changeMonth(Integer.parseInt(params.adjust))
        render (template: "monthView", model: [ pageModel: model ])
    }

    def changeCalendarWeek = {

        getUICalendar().changeWeek(Integer.parseInt(params.adjust))
        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def changeCalendarToday = {

        getUICalendar().setToday()
        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def selectCalendarDay = {

        def newDate = new SimpleDateFormat("MM-dd-yyyy").parse(params.selectedDay)
        getUICalendar().date = newDate
        model.uiCalendar.build()

        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def switchView = {

        getUICalendar().weekView = (params.viewType.toLowerCase() == "week") ? true : false
        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def createCalendarEvent = {

        Date eventDate = new SimpleDateFormat("MM/dd/yyyy").parse(params.eventDate as String)
        String eventDescription = params.eventDescription
        Long eventCalendarId = Long.parseLong(params.eventCalendarId.toString())
        String eventFromTime = params.eventFromTime
        String eventToTime = params.eventToTime
        boolean allDayEvent = params.allDayEvent

        eventDataService.createCalendarEvent(eventDate, sessionUser, eventCalendarId, eventDescription, eventFromTime, eventToTime, allDayEvent)

        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }
}
