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
    CoreUserDataService coreUserDataService

    private Type typeToken = new TypeToken<ArrayList<Calendar>>(){}.getType();

    List<Calendar> getAllCalendars(Long userId = null, boolean noCache = false) {

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String
        def cacheKey = (noCache) ? null : buildCacheKey(endpoint)

        try {
            super.getAll(Calendar.class, typeToken, endpoint, cacheKey, userId)
        } catch (Exception ex) {
            log.error ex.getMessage(), ex
            throw ex
        }
    }

    Calendar createCalendar(Long userId, boolean _default, String description = "") {

        def user
        try {
            user = coreUserDataService.getById(CoreUser.class, userId)
        } catch (Exception ex) {
            throw ex
        }

        if (!user) { return null }

        Calendar cal = new Calendar()
        cal.user = user
        cal.description = (description) ? description : ""
        cal.events = []
        cal.active = true
        cal.color = "#800000"  // TODO Do something with this..
        cal._default = _default

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        def calendar
        try {
            calendar = super.create(Calendar.class, cal, endpoint)
        } catch (Exception ex) {
            throw ex
        }

        if (calendar) {
            flushCache(buildCacheKey(endpoint))
        }
    }

    void deleteCalendar(Long calendarId, boolean noCache = false) {

        def flushKey = (noCache) ? null : buildCacheKey("getAllCalendars")

        try {
            super.delete(calendarId, flushKey)
        } catch (Exception ex) {
            throw ex
        }
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar" }
    String getUserToken() { userTokenService.userTokenStringFromSession  }

}
