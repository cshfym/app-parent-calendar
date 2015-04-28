package com.parentcalendar.controller

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

        loadCalendarInModel(false)

        [ pageModel: model ]
    }

    protected loadCalendarInModel(boolean noCache) {

        try {
            model.userCalendars = service.getAllCalendars(sessionUser.id, noCache)
        } catch (Exception ex) {
            handleException(ex)
            model.userCalendars = []
        }
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
        getUICalendar().build()

        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def switchView = {

        getUICalendar().weekView = (params.viewType.toLowerCase() == "week") ? true : false
        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def setWeeklyVisibleHours = {

        getUICalendar().weeklyVisibleHours = Integer.parseInt(params.weeklyVisibleHours as String)
        getUICalendar().build()
        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }

    def createCalendarEvent = {

        Date fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(params.fromDate as String)
        Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(params.toDate as String)
        String fromTime = params.fromTime
        String toTime = params.toTime
        String description = params.description
        Long calendarId = Long.parseLong(params.calendarId.toString())
        boolean allDayEvent = Boolean.parseBoolean(params.allDayEvent as String)

        eventDataService.createCalendarEvent(sessionUser, calendarId, description, fromDate, toDate, fromTime, toTime, allDayEvent)

        service.flushCache(service.endpoint)

        loadCalendarInModel(true)

        getUICalendar().build()

        render (template: (model.uiCalendar.weekView) ? "weekView" : "monthView", model: [ pageModel: model ])
    }
}
