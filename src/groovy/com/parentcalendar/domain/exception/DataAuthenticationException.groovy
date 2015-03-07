package com.parentcalendar.domain.exception


class DataAuthenticationException extends Throwable {

    String message

    public DataAuthenticationException(String msg) {
      message = msg
    }
}
