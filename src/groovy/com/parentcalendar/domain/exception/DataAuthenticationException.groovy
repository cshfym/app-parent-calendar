package com.parentcalendar.domain.exception


class DataAuthenticationException extends Exception {

    String message

    public DataAuthenticationException(String msg) {
      message = msg
    }
}
