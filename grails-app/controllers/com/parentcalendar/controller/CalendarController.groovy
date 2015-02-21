package com.parentcalendar.controller

import com.parentcalendar.domain.ui.UICalendar
import com.parentcalendar.services.rest.UserDataService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired

class CalendarController {

  private static final log = LogFactory.getLog(this)

  @Autowired
  UserDataService userDataService

  UICalendar uiCalendar

  def index() {

    def userlist = userDataService.getAllUsers()

    uiCalendar = new UICalendar()

    [ calendar: uiCalendar, today: new Date(), users: userlist ]
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
