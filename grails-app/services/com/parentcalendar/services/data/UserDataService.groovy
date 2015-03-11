package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.CoreUser
import com.parentcalendar.services.db.BaseDataService
import com.parentcalendar.services.security.UserAuthenticationService
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired

import java.lang.reflect.Type

@Transactional
class UserDataService extends BaseDataService {

  private static final log = LogFactory.getLog(this)

  private Type typeToken = new TypeToken<ArrayList<CoreUser>>(){}.getType();

  List<CoreUser> getAllUsers(boolean noAuth = false) {
    try {
      super.getAll(CoreUser.class, typeToken, true, noAuth)
    } catch (Exception ex) {
      throw ex
    }
  }

  CoreUser getById(Long id, boolean noAuth = false) {
    super.getById(CoreUser.class, id, noAuth)
  }

  List<CoreUser> getBy(String column, String attribute, boolean noAuth = false) {
    super.getBy(CoreUser.class, column, attribute, noAuth)
  }

  void flushCache() {
    def endpoint = grailsApplication.config.calendarData.host + dataPath as String
    super.cacheService.flushCache(endpoint)
  }

  def getTTL() { 30 }
  def getDataPath() { "/user" }
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
