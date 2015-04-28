package com.parentcalendar.domain.core

import com.parentcalendar.domain.enums.EventTimespan
import com.parentcalendar.domain.security.User
import com.parentcalendar.services.util.DateUtility

import java.text.SimpleDateFormat

/**
 * CalendarEvent can have the following timespan:
 *   * Same-Day
 *   * Same-Day All-Day
 *   * Multi-Day with Start & Stop Times
 *   * Multi-Day All-Day
 */
class CalendarEvent {

    Long id
    Long version
    Calendar calendar
    User user
    String description
    Date fromTime
    Date toTime
    boolean allDay
    Date createDate
    Date updateDate
    boolean active

    public String getEventStartTimeAndDescription() {

        if (!fromTime || !description) {
            return ""
        }

        def eventPrefix = ""
        if (eventTimespan == EventTimespan.SAME_DAY) { eventPrefix = DateUtility.getTimePrefix(fromTime) }
        if (eventTimespan == EventTimespan.MULTI_DAY) { eventPrefix = "(" + DateUtility.getTimePrefix(fromTime) + ")" }

        new StringBuilder()
            .append(eventPrefix)
            .append(" ")
            .append(description)
    }

    public EventTimespan getEventTimespan() {

        if (allDay && DateUtility.isSameDay(fromTime, toTime)) {
            return EventTimespan.ALL_DAY
        }
        if (!allDay && DateUtility.isBefore(fromTime, toTime)) {
            return EventTimespan.MULTI_DAY
        }
        if (allDay && DateUtility.isBefore(fromTime, toTime)) {
            return EventTimespan.MULTI_DAY_ALL_DAY
        }

        // Defaults to same-day.
        EventTimespan.SAME_DAY
    }

    // Format: 7:00p - 9:00p
    public String getStringTimeSpan() {
        DateUtility.getTimePrefix(fromTime) + " - " + DateUtility.getTimePrefix(toTime)
    }

    @Override
    public String toString() {
        "CalendarEvent: [ id: $id, active: $active, createDate: $createDate, updateDate: $updateDate ]"
    }
}
