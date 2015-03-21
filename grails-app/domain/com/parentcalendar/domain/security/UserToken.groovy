package com.parentcalendar.domain.security

class UserToken {

    Long id
    User user
    String token
    Date issued

    @Override
    public String toString() {
        "UserToken: [ id: $id, user: $user, token: $token, issued: $issued ]"
    }
}
