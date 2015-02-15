package com.parentcalendar.domain.ui

/**
 * Represents one week on the UICalendar
 */
class UIWeek {

  List<UIDay> days

  @Override
  public String toString() {
    "UIWeek > ${days}"
  }
}
