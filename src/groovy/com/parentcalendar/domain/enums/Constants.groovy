package com.parentcalendar.domain.enums


public enum Constants {

    X_AUTH_ALL_USERS("X-Auth-All-Users"),
    X_AUTH_USER_ID("X-Auth-User-Id") // User-specific data ID

    String value

    public Constants(String value) {
        this.value = value
    }

}