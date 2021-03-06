package com.parentcalendar.domain.ui.page

import com.parentcalendar.domain.core.CalendarEvent
import com.parentcalendar.domain.enums.EventTimespan
import com.parentcalendar.domain.ui.UICalendar
import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.services.util.DateUtility

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

    // Same-day calendar events.
    public List<CalendarEvent> getSameDayCalendarEventsByDate(Date date) {

        def events = []

        userCalendars.each { calendar ->
            calendar.events.each { event ->

                if (event.eventTimespan != EventTimespan.SAME_DAY) { return }

                // Date argument falls between event from/to time
                if (DateUtility.isInDateRange(event.fromTime, event.toTime, date)) {
                    events << event
                }
            }
        }

        events.sort { it.fromTime }
        events
    }

    // All-day calendar events, including a non-all-day multi-day events that spans this entire day.
    public List<CalendarEvent> getAllDayCalendarEventsByDate(Date date) {

        def events = []

        userCalendars.each { calendar ->
            calendar.events.each { event ->

                // Not all or multi-day.
                if (event.eventTimespan == EventTimespan.SAME_DAY) { return }

                // Not valid for the specified date (falls before or after).
                if (!DateUtility.isInDateRange(event.fromTime, event.toTime, date)) { return }

                events << event
            }
        }

        events.sort { it.fromTime }
        events
    }

    /* All calendar events for the specified date, irrespective of type (same-day, multi, etc.) */
    protected List<CalendarEvent> getAllCalendarEventsByDate(Date date) {

        def events = []

        userCalendars.each { calendar ->
            calendar.events.each { event ->
                // Date argument falls between event from/to time
                if (DateUtility.isInDateRange(event.fromTime, event.toTime, date)) {
                    events << event
                }
            }
        }

        events.sort { it.fromTime }
        events
    }

    public String getNextEventStartTime() {
        getNextEventTime(0)
    }
    public String getNextEventFinishTime() {
        getNextEventTime(1)
    }

    private String getNextEventTime(int offset) {

        java.util.Calendar cal = java.util.Calendar.getInstance()
        cal.add(java.util.Calendar.HOUR_OF_DAY, offset)

        def minute = cal.get(java.util.Calendar.MINUTE)
        def amPM = (cal.get(java.util.Calendar.HOUR_OF_DAY) > 12) ? " PM" : " AM"
        def nextFifteen = getRoundedFifteenInterval(minute)
        def nextHour = (minute > 45) ? cal.get(java.util.Calendar.HOUR) + 1 : cal.get(java.util.Calendar.HOUR)

        nextHour + ":" + nextFifteen + amPM
    }

    private String getRoundedFifteenInterval(int minute) {

        if (minute < 15) {
            return "15"
        } else if (minute < 30) {
            return "30"
        } else if (minute < 45) {
            return "45"
        }

        "00"
    }

    public List<String> getEventTimeIntervals() {
        [
            "12:00 AM", "12:15 AM", "12:30 AM", "12:45 AM",
            "1:00 AM",  "1:15 AM",  "1:30 AM",  "1:45 AM",
            "2:00 AM",  "2:15 AM",  "2:30 AM",  "2:45 AM",
            "3:00 AM",  "3:15 AM",  "3:30 AM",  "3:45 AM",
            "4:00 AM",  "4:15 AM",  "4:30 AM",  "4:45 AM",
            "5:00 AM",  "5:15 AM",  "5:30 AM",  "5:45 AM",
            "6:00 AM",  "6:15 AM",  "6:30 AM",  "6:45 AM",
            "7:00 AM",  "7:15 AM",  "7:30 AM",  "7:45 AM",
            "8:00 AM",  "8:15 AM",  "8:30 AM",  "8:45 AM",
            "9:00 AM",  "9:15 AM",  "9:30 AM",  "9:45 AM",
            "10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM",
            "11:00 AM", "11:15 AM", "11:30 AM", "11:45 AM",
            "12:00 PM", "12:15 PM", "12:30 PM", "12:45 PM",
            "1:00 PM",  "1:15 PM",  "1:30 PM",  "1:45 PM",
            "2:00 PM",  "2:15 PM",  "2:30 PM",  "2:45 PM",
            "3:00 PM",  "3:15 PM",  "3:30 PM",  "3:45 PM",
            "4:00 PM",  "4:15 PM",  "4:30 PM",  "4:45 PM",
            "5:00 PM",  "5:15 PM",  "5:30 PM",  "5:45 PM",
            "6:00 PM",  "6:15 PM",  "6:30 PM",  "6:45 PM",
            "7:00 PM",  "7:15 PM",  "7:30 PM",  "7:45 PM",
            "8:00 PM",  "8:15 PM",  "8:30 PM",  "8:45 PM",
            "9:00 PM",  "9:15 PM",  "9:30 PM",  "9:45 PM",
            "10:00 PM", "10:15 PM", "10:30 PM", "10:45 PM",
            "11:00 PM", "11:15 PM", "11:30 PM", "11:45 PM"
        ]
    }
}
