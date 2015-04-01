package com.parentcalendar.domain.ui

import java.text.SimpleDateFormat

/**
 * Represents a UICalendar instance.
 */
class UICalendar {

    /* CONSTANTS */
    def DAYS_PER_WEEK = 7
    def DAYS_IN_MONTH = [31,28,31,30,31,30,31,31,30,31,30,31]
    def DAYS_IN_MONTH_LY = [31,29,31,30,31,30,31,31,30,31,30,31]

    /* INSTANCE ATTRIBUTES */
    Date date
    List<UIWeek> weeks = []
    boolean weekView = false

    /**
     * Default Constructor
     */
    public UICalendar() {
        this.date = new Date()

        build()
    }

    /**
     * Convenience Constructor
     * @param Date date
     */
    public UICalendar(Date date) {
        this.date = date
        build()
    }

    public String monthName() {
        new SimpleDateFormat("MMMM yyyy").format(this.date)
    }

    /**
     * Returns a list of days of the week given the current calendar configuration, i.e. "Sunday, Monday, Tuesday, ..."
     * @return List<String>
     */
    public List<String> monthDayHeaderList() {
        def list = []
        int cnt = 1
        weeks[0].days.each { day ->
            list.add(day.dayOfWeekName())
            cnt++
            if (cnt == 7) {
                return
            }
        }
        list
    }

    public List<String> weekDayHeaderList() {
        def list = []
        int cnt = 1
        currentDateWeek.days.each { day ->
            list.add(
                    day.dayOfWeekName() + " " +
                    (day.toCalendar().get(Calendar.MONTH) + 1) + "/" +
                    day.toCalendar().get(Calendar.DAY_OF_MONTH)
            )
            cnt++
            if (cnt == 7) {
                return
            }
        }
        list
    }

    public int getVisibleWeekCount() {
        (this.weekView) ? 1 : this.weeks.size()
    }

    /**
     * Returns the week belonging to the currently selected date.
     * @return UIWeek
     */
    public UIWeek getCurrentDateWeek() {

        UIWeek currentWeek

        this.weeks.each { week ->
            week.days.each { day ->
                if (day.date == this.date) {
                    currentWeek = week
                    return
                }
            }
            if (currentWeek) { return }
        }

        currentWeek
    }

    /*
  public boolean isSelectedDate(Date date) {
    DateTime compareDate = new DateTime(date.getTime())
    compareDate.withTimeAtStartOfDay().equals(new DateTime(this.date.getTime()).withTimeAtStartOfDay())
  }
      */

    public void setToday() {
        Calendar today = Calendar.getInstance()
        today.setTime(new Date())
        this.date = today.getTime()

        build()
    }

    public void changeMonth(int value) {
        Calendar adjMonth = Calendar.getInstance()
        adjMonth.setTime(this.date)
        adjMonth.add(Calendar.MONTH, value)
        this.date = adjMonth.getTime()

        build()
    }

    public void changeWeek(int value) {
        Calendar adjWeek = Calendar.getInstance()
        adjWeek.setTime(this.date)
        adjWeek.add(Calendar.WEEK_OF_YEAR, value)
        this.date = adjWeek.getTime()

        build()
    }

    public void changeYear(int value) {
        Calendar year = Calendar.getInstance()
        year.setTime(this.date)
        year.add(Calendar.YEAR, value)
        this.date = year.getTime()

        build()
    }

    public String getPreviousMonthYearString() {
        Calendar previousMonth = Calendar.getInstance()
        previousMonth.setTime(this.date)
        previousMonth.add(Calendar.MONTH, -1)
        new SimpleDateFormat("MMMM yyyy").format(previousMonth.getTime())
    }

    public String getNextMonthYearString() {
        Calendar nextMonth = Calendar.getInstance()
        nextMonth.setTime(this.date)
        nextMonth.add(Calendar.MONTH, 1)
        new SimpleDateFormat("MMMM yyyy").format(nextMonth.getTime())
    }

    public String getNextYearString() {
        Calendar nextYear = Calendar.getInstance()
        nextYear.setTime(this.date)
        nextYear.add(Calendar.YEAR, 1)
        new SimpleDateFormat("yyyy").format(nextYear.getTime())
    }

    /**
     * Builds the current UICalendar object based on the object date.
     */
    public void build() {

        // NOTE - Calendar operations are ZERO-based and assume SUNDAY as the first day of the week.
        // Can be modified using today.setFirstDayOfWeek(Calendar.MONDAY) ??

        weeks = []

        // Get the first day of the current month.
        Calendar instanceCalendar = Calendar.getInstance()
        instanceCalendar.setTime(this.date)
        instanceCalendar.set(Calendar.DAY_OF_MONTH, 1)

        // First position in the week should be the first day of the week (Sunday or Monday - configurable!).

        // Get date of first day of week.
        Calendar startingDay = Calendar.getInstance()
        startingDay.setTime(instanceCalendar.getTime())
        startingDay.add(Calendar.DAY_OF_YEAR, (-1 * (instanceCalendar.get(Calendar.DAY_OF_WEEK) - 1)))

        // Check leap year.
        def daysInMonth = (isLeapYear(instanceCalendar.get(Calendar.YEAR))) ? DAYS_IN_MONTH_LY : DAYS_IN_MONTH

        // Derive total number of days displayed on the calendar.
        def todayDayOfWeek = instanceCalendar.get(Calendar.DAY_OF_WEEK)
        def totalCalendarDays = daysInMonth.get([instanceCalendar.get(Calendar.MONTH)]) + (todayDayOfWeek - 1)
        def totalWeeksDisplayed = Math.ceil(totalCalendarDays / 7).intValue()

        def days = []
        def weekNumber = 1

        (1..totalWeeksDisplayed).each {
            (1..DAYS_PER_WEEK).each { dayNumber ->
                days.add(new UIDay(date: startingDay.time, inCalendarMonth: (startingDay.get(Calendar.MONTH) == instanceCalendar.get(Calendar.MONTH))))
                if ((dayNumber % 7) == 0) {
                    this.weeks.add(new UIWeek(days: days))
                    days = []
                    weekNumber++
                }
                startingDay.add(Calendar.DAY_OF_MONTH, 1) // Advance day.
            }
        }
    }

    protected boolean isLeapYear(int year) {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance()
        cal.isLeapYear(year);
    }

    @Override
    public String toString() {
        "UICalendar > [$weeks]"
    }
}
