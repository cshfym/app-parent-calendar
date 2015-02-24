package com.parentcalendar.domain.core


class CoreUser {

    Long id
    Long version
    Boolean expired
    Boolean locked
    Boolean active
    Boolean passwordExpired
    Boolean enabled
    String email
    String username
    String password

    @Override
    public String toString() {
        "CoreUser: [ id: $id, version: $version, expired: $expired, locked: $locked, active: $active, " +
                "passwordExpired: $passwordExpired, enabled: $enabled, email: $email, username: $username, password: $password ]"

    }
}
