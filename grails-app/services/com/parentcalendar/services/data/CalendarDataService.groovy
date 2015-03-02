package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.domain.core.CoreUser
import com.parentcalendar.services.db.BaseDataService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import java.lang.reflect.Type


@Transactional
class CalendarDataService extends BaseDataService {

    private static final log = LogFactory.getLog(this)

    @Autowired
    UserDataService userDataService

    private Type typeToken = new TypeToken<ArrayList<Calendar>>(){}.getType();

    List<Calendar> getAllCalendars() {
        super.getAll(Calendar.class, typeToken)
    }

    Calendar createCalendar(Long userId, String description = "") {

        def user = userDataService.getById(CoreUser.class, userId)

        if (!user) { return null }

        Calendar cal = new Calendar()
        cal.user = user
        cal.description = description
        cal.events = []
        cal.active = true

        super.create(Calendar.class, cal)
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar" }
}
