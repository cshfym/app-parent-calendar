package com.parentcalendar.domain.ui

import java.text.SimpleDateFormat

/**
 * Represents one day on the UICalendar.
 */
class UIDay {

  Date date

  public String getDayNumber() {
    toCalendar().get(Calendar.DAY_OF_MONTH).toString()
  }

  public String getCondensedDateString() {
    new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).format(date)
  }

  public String dayOfWeekName() {
    new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
  }
  public boolean isToday() {

    Calendar today = Calendar.getInstance()
    today.setTime(new Date())

    Calendar uiDate = toCalendar()

    (uiDate.get(Calendar.YEAR).equals(today.get(Calendar.YEAR))
      && uiDate.get(Calendar.MONTH).equals(today.get(Calendar.MONTH))
      && uiDate.get(Calendar.DAY_OF_MONTH).equals(today.get(Calendar.DAY_OF_MONTH)))
  }

  protected Calendar toCalendar() {
    Calendar cal = Calendar.getInstance()
    cal.setTime(date)
    cal
  }

  @Override
  public String toString() {
    "UIDay > ${date}"
  }
}
