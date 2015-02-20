package com.parentcalendar.controller

import com.parentcalendar.domain.ui.UICalendar
import com.parentcalendar.domain.ui.UIDay
import com.parentcalendar.domain.ui.UIWeek
import com.parentcalendar.service.TestService
import grails.converters.JSON

class CalendarController {

  TestService testService

  UICalendar uiCalendar

  def index() {

    uiCalendar = new UICalendar()

    [ calendar: uiCalendar, today: new Date() ]
  }

  def changeCalendarMonth = {

    if (uiCalendar) {
      uiCalendar.changeMonth(Integer.parseInt(params.adjust))
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: "calendar", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def changeCalendarToday = {

    if (uiCalendar) {
      uiCalendar.setToday()
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: "calendar", model: [ calendar: uiCalendar, today: new Date() ])
  }

  def changeNextYear = {

    if (uiCalendar) {
      uiCalendar.changeNextYear()
    } else {
      uiCalendar = new UICalendar()
    }

    render (template: "calendar", model: [ calendar: uiCalendar, today: new Date() ])
  }
}
