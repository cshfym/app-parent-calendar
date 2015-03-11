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

    private static final log = LogFactory.getLog(this)

    CalendarEvent getCalendarEventById(Long id, boolean noAuth = false) {
        super.getById(CalendarEvent.class, id, noAuth)
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar/event" }
    String getUserToken() { userTokenService.userTokenStringFromSession }

    String getCacheKey(String method) {
        new StringBuffer()
                .append(userAuthenticationService.userId)
                .append("|")
                .append(dataPath)
                .append("|")
                .append(method)
                .toString()
    }
}
