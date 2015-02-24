package com.parentcalendar.domain.core


class Calendar {

    Long id
    CoreUser user
    boolean active
    Date createDate
    Date updateDate

    @Override
    public String toString() {
        "Calendar: [ id: $id, user: $user, active: $active, createDate: $createDate, updateDate: $updateDate ]"
    }
}
