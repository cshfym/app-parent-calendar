package com.parentcalendar.services.data

import com.parentcalendar.domain.core.CalendarEvent
import com.parentcalendar.services.db.BaseDataService
import org.apache.commons.logging.LogFactory
import org.springframework.transaction.annotation.Transactional


@Transactional
class CalendarEventDataService extends BaseDataService {

    private static final log = LogFactory.getLog(this)

    CalendarEvent getCalendarEventById(Long id) {
        super.getById(CalendarEvent.class, id)
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
