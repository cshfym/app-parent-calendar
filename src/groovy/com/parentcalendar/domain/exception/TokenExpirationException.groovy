package com.parentcalendar.domain.exception


class TokenExpirationException extends Exception {

    String message

    public TokenExpirationException(String msg) {
      message = msg
    }
}
