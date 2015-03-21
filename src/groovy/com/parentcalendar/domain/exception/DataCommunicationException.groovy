package com.parentcalendar.domain.exception


class DataCommunicationException extends Exception {

    String message

    public DataCommunicationException(String msg) {
      message = msg
    }
}
