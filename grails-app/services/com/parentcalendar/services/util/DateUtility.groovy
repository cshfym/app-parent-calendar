package com.parentcalendar.services.util

import org.springframework.stereotype.Component


@Component
class DateUtility {

    public static boolean isSameDay(Date arg0, Date arg1) {

        def normDate1 = new Date(arg0.getTime())
        def normDate2 = new Date(arg1.getTime())

        normDate1.clearTime() == normDate2.clearTime()
    }

    // Is arg0 before arg1
    public static boolean isBefore(Date arg0, Date arg1) {

        def normDate1 = new Date(arg0.getTime())
        def normDate2 = new Date(arg1.getTime())

        normDate1.clearTime() < normDate2.clearTime()
    }

    // Is arg0 after arg1
    public static boolean isAfter(Date arg0, Date arg1) {

        def normDate1 = new Date(arg0.getTime())
        def normDate2 = new Date(arg1.getTime())

        normDate1.clearTime() > normDate2.clearTime()
    }

    /* Arg date is before fromDate, or arg date is after toDate = not in range. */
    public static boolean isInDateRange(Date fromDate, Date toDate, Date date) {

        if (isBefore(date, fromDate)) { return false }
        if (isAfter(date, toDate)) { return false }

        true
    }

    public static boolean eventSpansDate(Date fromDate, Date toDate, Date date) {

         if (isAfter(date, fromDate) && isBefore(date, toDate)) {
            return true
        }

        false
    }
}
