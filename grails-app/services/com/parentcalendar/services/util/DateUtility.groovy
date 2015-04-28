package com.parentcalendar.services.util

import org.springframework.stereotype.Component

import java.text.SimpleDateFormat


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

    public static int calculateMinuteDifference(Date fromDate, Date toDate) {

        // intervalTime format = "5 AM", etc.

        def duration
        use(groovy.time.TimeCategory) {
            duration = toDate - fromDate
        }

        (duration.hours * 60) + duration.minutes
    }

    public static int calculateMinutesFromUpperBound(Date upperBound, Date arg0) {

        def duration
        use(groovy.time.TimeCategory) {
            duration = arg0 - upperBound
        }

        (duration.hours * 60) + duration.minutes
    }

    public static Date convertIntervalTimeToCurrentDateTime(Date date, String intervalTime) {

        def intervalParts = intervalTime.split(" ")

        Calendar cal = Calendar.getInstance()
        cal.setTime(date)

        if (intervalParts[0] == "12") { intervalParts[0] = "0" }

        if(intervalParts[1] == "AM") {
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(intervalParts[0]))
        } else {
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(intervalParts[0]) + 12)
        }

        cal.set(Calendar.MINUTE, 0)

        cal.time
    }

    // Format: 7:00p
    public static String getTimePrefix(Date date) {
        new SimpleDateFormat("h:mm").format(date) + new SimpleDateFormat("a").format(date).substring(0,1).toLowerCase()
    }
}
