package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.CoreUser
import com.parentcalendar.services.db.BaseDataService
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import java.lang.reflect.Type

@Transactional
class CoreUserDataService extends BaseDataService {

  private static final log = LogFactory.getLog(this)

  private Type typeToken = new TypeToken<ArrayList<CoreUser>>(){}.getType();

  List<CoreUser> getAllUsers() {
    try {
      super.getAll(CoreUser.class, typeToken, true, null)
    } catch (Exception ex) {
      throw ex
    }
  }

  CoreUser getById(Long id) {
      try {
          super.getById(CoreUser.class, id)
      } catch (Exception ex) {
          throw ex
      }
  }

  List<CoreUser> getBy(String column, String attribute, boolean allUsers, Long userId) {
      try {
          super.getBy(CoreUser.class, column, attribute, allUsers, userId)
      } catch (Exception ex) {
          throw ex
      }
  }

  void flushCache() {
      !! Fix me.
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
