package com.parentcalendar.services.data

import com.google.gson.GsonBuilder
import com.parentcalendar.domain.security.UserToken
import com.parentcalendar.services.db.BaseDataService
import com.parentcalendar.services.rest.RestDataService
import com.parentcalendar.services.security.UserAuthenticationService
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired


@Transactional
class UserTokenDataService extends BaseDataService {

  def grailsApplication

  private static final log = LogFactory.getLog(this)

  @Autowired
  UserAuthenticationService userAuthenticationService

  @Autowired
  RestDataService restDataService

  UserToken getToken(Long userId) {

      if (!userId) { return null }

      gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

      def data

      def response = restDataService.post(
              grailsApplication.config.calendarData.host + dataPath as String,
              grailsApplication.config.calendarData.contentType as String,
              "{ \"userId\": $userId }",
              grailsApplication.config.authentication.token as String) // Default token

      if (response?.status == 200) {
          data = gson.fromJson(response?.json.toString(), UserToken.class)
      } else {
          // TODO Implement me...
      }

      data
  }

  def getTTL() { 0 }
  def getDataPath() { "/user/token" }
  String getUserToken() { userAuthenticationService.userToken }
}
