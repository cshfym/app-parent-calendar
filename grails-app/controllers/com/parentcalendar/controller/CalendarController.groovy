package com.parentcalendar.controller

import com.parentcalendar.domain.ui.UICalendar
import com.parentcalendar.services.data.CalendarDataService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured

import java.text.SimpleDateFormat

@Secured(['ROLE_USER'])
class CalendarController extends BaseController {

  private static final log = LogFactory.getLog(this)

  @Autowired
  CalendarDataService service

  UICalendar uiCalendar

  def index() {

    uiCalendar = new UICalendar()

    def allCalendars = []
    try {
      allCalendars = service.getAllCalendars()
    } catch (Exception ex) {
      println ex
    }

    [ calendar: uiCalendar, allCalendars: allCalendars, today: new Date() ]
  }

  def changeCalendarMonth = {

    if (uiCalendar) {
      uiCalendar.changeMonth(Integer.parseInt(params.adjust))
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: "monthView", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def changeCalendarWeek = {

    if (uiCalendar) {
        uiCalendar.changeWeek(Integer.parseInt(params.adjust))
    } else {
        uiCalendar = new UICalendar()
    }

    render (template: (uiCalendar.weekView) ? "weekView" : "monthView", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def changeCalendarToday = {

    if (uiCalendar) {
      uiCalendar.setToday()
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: (uiCalendar.weekView) ? "weekView" : "monthView", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def selectCalendarDay = {

    if (!uiCalendar) {
      uiCalendar = new UICalendar()
    }

    def newDate = new SimpleDateFormat("MM-dd-yyyy").parse(params.selectedDay)
    uiCalendar.date = newDate

    uiCalendar.build()

    render (template: (uiCalendar.weekView) ? "weekView" : "monthView", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def changeNextYear = {

    if (uiCalendar) {
      uiCalendar.changeNextYear()
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: (uiCalendar.weekView) ? "weekView" : "monthView", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def switchView = {

    if (uiCalendar) {
      uiCalendar.weekView = (params.viewType.toLowerCase() == "week") ? true : false
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: (uiCalendar.weekView) ? "weekView" : "monthView", model: [ calendar: uiCalendar, today: new Date() ])

  }
}
