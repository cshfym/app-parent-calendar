package com.parentcalendar.domain.core


class Calendar {

    Long id
    CoreUser user
    boolean active
    Date createDate
    Date updateDate
    String description
    List<CalendarEvent> events

    @Override
    public String toString() {
        "Calendar: [ id: $id, user: $user, active: $active, createDate: $createDate, updateDate: $updateDate," +
                "description: $description, events: $events ]"
    }
}
