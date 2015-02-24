package com.parentcalendar.controller

import com.parentcalendar.domain.ui.UICalendar
import org.apache.commons.logging.LogFactory
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_USER'])
class CalendarController {

  private static final log = LogFactory.getLog(this)

  UICalendar uiCalendar

  def index() {

    uiCalendar = new UICalendar()

    [ calendar: uiCalendar, today: new Date()]
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
