package com.parentcalendar.domain.core


class CalendarEvent {

    Long id
    boolean active
    Date createDate
    Date updateDate

    @Override
    public String toString() {
        "CalendarEvent: [ id: $id, active: $active, createDate: $createDate, updateDate: $updateDate ]"
    }
}
