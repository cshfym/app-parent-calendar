package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.domain.core.CalendarEvent
import com.parentcalendar.services.db.BaseDataService
import com.parentcalendar.services.security.UserAuthenticationService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import java.lang.reflect.Type


@Transactional
class CalendarEventDataService extends BaseDataService {

    @Autowired
    UserAuthenticationService userAuthenticationService

    private static final log = LogFactory.getLog(this)

    CalendarEvent getCalendarEventById(Long id) {
        super.getById(CalendarEvent.class, id)
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar/event" }
    String getUserToken() { userAuthenticationService.userToken }
}