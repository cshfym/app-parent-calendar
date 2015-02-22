package com.parentcalendar.services.rest

import com.google.gson.Gson
import com.parentcalendar.domain.core.User
import com.parentcalendar.services.cache.Cacheable
import com.parentcalendar.services.cache.RedisCacheService
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class UserDataService {

  private static final log = LogFactory.getLog(this)

  def grailsApplication

  private static final String DATA_PATH = "/user"

  @Autowired
  RestDataService restDataService

  @Autowired
  RedisCacheService cacheService

  @Autowired
  Gson gson

  //@Cacheable
  List<User> getAllUsers() {

    def data = []

    def endpoint =
      grailsApplication.config.calendarData.host + DATA_PATH as String

    def cachedData = cacheService.getCache(endpoint)
    if (cachedData) {
      def list = gson.fromJson(cachedData, List.class)
      list.each {
        data << new User(it)
      }
      return data
    }

    def response = restDataService.get(
      endpoint as String,
      grailsApplication.config.authentication.token as String,
      grailsApplication.config.calendarData.contentType as String) as RestResponse

    if (response?.status == 200) {
      response?.json.each {
        data << new User(it)
      }
    }

    cacheService.setCache(endpoint, gson.toJson(data), com.parentcalendar.domain.core.User.TTL)

    data
  }
}
