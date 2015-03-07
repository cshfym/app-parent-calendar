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

  @Autowired
  UserAuthenticationService userAuthenticationService

  private static final log = LogFactory.getLog(this)

  private Type typeToken = new TypeToken<ArrayList<CoreUser>>(){}.getType();

  List<CoreUser> getAllUsers() {
    super.getAll(CoreUser.class, typeToken)
  }

  CoreUser getUser(Long id) {
    super.getById(id)
  }

  void flushCache() {
    def endpoint = grailsApplication.config.calendarData.host + dataPath as String
    super.cacheService.flushCache(endpoint)
  }

  def getTTL() { 30 }
  def getDataPath() { "/user" }
  String getUserToken() { userAuthenticationService.userToken }

}
