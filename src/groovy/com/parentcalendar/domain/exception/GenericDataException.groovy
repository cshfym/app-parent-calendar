package com.parentcalendar.domain.exception


class GenericDataException extends Throwable {

    String message

    public GenericDataException(String msg) {
        message = msg
    }
}
