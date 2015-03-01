package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.domain.core.CalendarEvent
import com.parentcalendar.services.db.BaseDataService
import org.apache.commons.logging.LogFactory
import org.springframework.transaction.annotation.Transactional

import java.lang.reflect.Type


@Transactional
class CalendarEventDataService extends BaseDataService {

    private static final log = LogFactory.getLog(this)

    private Type typeToken = new TypeToken<ArrayList<Calendar>>(){}.getType();

    CalendarEvent getCalendarEventById(Long id) {
        super.get(CalendarEvent.class, typeToken)
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar/event" }
}
