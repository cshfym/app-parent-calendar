package com.parentcalendar.domain.ui.page

import com.parentcalendar.domain.ui.UICalendar
import com.parentcalendar.domain.core.Calendar

/**
 * UI.Page model for the Calendar page.
 */
class CalendarModel {

  /* Calendar grid. */
  UICalendar uiCalendar

  /* User-specific calendars. */
  List<Calendar> userCalendars

  /* Current date. */
  Date today = new Date()

  public CalendarModel() {
    //
  }

}
