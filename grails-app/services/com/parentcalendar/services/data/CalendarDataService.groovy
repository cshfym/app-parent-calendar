package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.domain.security.User
import org.apache.commons.logging.LogFactory
import org.springframework.transaction.annotation.Transactional

import java.lang.reflect.Type


@Transactional
class CalendarDataService extends BaseDataService {

    private static final log = LogFactory.getLog(this)

    private Type typeToken = new TypeToken<ArrayList<User>>(){}.getType();

    List<Calendar> getAllCalendars() {
        super.getAll(typeToken)
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar" }
}
