package com.parentcalendar.domain.exception


class GenericDataException extends Exception {

    String message

    public GenericDataException(String msg) {
        message = msg
    }
}
