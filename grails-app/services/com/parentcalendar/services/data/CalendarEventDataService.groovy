package com.parentcalendar.services.data

import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.domain.core.CalendarEvent
import com.parentcalendar.domain.security.User
import com.parentcalendar.services.db.BaseDataService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional


@Transactional
class CalendarEventDataService extends BaseDataService {

    private static final log = LogFactory.getLog(this)

    @Autowired
    CalendarDataService calendarDataService

    CalendarEvent createCalendarEvent(Date eventDate, User user, Long calendarId, String description, String fromTime, String toTime, boolean allDay) {

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        def event = new CalendarEvent(
            calendar: calendarDataService.getById(Calendar.class, calendarId),
            user: user,
            eventDate: eventDate,
            description: description,
            fromTime: buildFullDateFromEventTime(fromTime, eventDate),
            toTime: buildFullDateFromEventTime(toTime, eventDate),
            allDay: allDay,
            active: true)

        def calendarEvent
        try {
            super.create(CalendarEvent.class, event, endpoint)
        } catch (Exception ex) {
            throw ex
        }

        if (calendarEvent) {
            flushCache(buildCacheKey(endpoint))
        }
    }

    protected Date buildFullDateFromEventTime(String partial, Date eventDate) {

        // Parse HH:MM A/P
        def partialSplit = partial.split(" ")
        def splitHoursSeconds = partialSplit[0].split(":")

        int tHour = (partialSplit[1] == "AM") ? 0 : 12
        int hour = Integer.parseInt(splitHoursSeconds[0]) + tHour
        int minute = Integer.parseInt(splitHoursSeconds[1])

        // Apply to eventDate
        java.util.Calendar cal = java.util.Calendar.getInstance()
        cal.setTime(eventDate)
        cal.set(java.util.Calendar.HOUR_OF_DAY, hour)
        cal.set(java.util.Calendar.MINUTE, minute)

        cal.getTime()
    }

    /*
    CalendarEvent getCalendarEventById(Long id, boolean noCache = false) {

        def cacheKey = (noCache) ? null : buildCacheKey("getCalendarEventById")

        try {
            super.getById(CalendarEvent.class, id, cacheKey)
        } catch (Exception ex) {
            throw ex
        }
    }
         */

    def getTTL() { 30 }
    def getDataPath() { "/calendar/event" }
    String getUserToken() { userTokenService.userTokenStringFromSession }

}
