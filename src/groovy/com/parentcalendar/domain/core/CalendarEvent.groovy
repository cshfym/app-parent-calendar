package com.parentcalendar.domain.core

import com.parentcalendar.domain.security.User


class CalendarEvent {

    Long id
    Long version
    Calendar calendar
    User user
    Date eventDate
    String description
    Date fromTime
    Date toTime
    boolean allDay
    Date createDate
    Date updateDate
    boolean active

    @Override
    public String toString() {
        "CalendarEvent: [ id: $id, active: $active, createDate: $createDate, updateDate: $updateDate ]"
    }
}
