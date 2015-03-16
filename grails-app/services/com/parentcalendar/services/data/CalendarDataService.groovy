package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.Calendar
import com.parentcalendar.domain.core.CoreUser
import com.parentcalendar.domain.exception.TokenExpirationException
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

    List<Calendar> getAllCalendars(boolean allUsers, Long userId) {
      try {
        super.getAll(Calendar.class, typeToken, allUsers, userId)
      } catch (Exception ex) {
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
        cal.description = description
        cal.events = []
        cal.active = true
        cal._default = _default

        super.create(Calendar.class, cal)
    }

    void deleteCalendar(Long calendarId) {
        try {
            super.delete(calendarId)
        } catch (Exception ex) {
            throw ex
        }
    }

    def getTTL() { 30 }
    def getDataPath() { "/calendar" }
    String getUserToken() { userTokenService.userTokenStringFromSession  }

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
