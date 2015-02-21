package com.parentcalendar.services.rest

import com.parentcalendar.domain.core.User
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class UserDataService {

  def grailsApplication

  @Autowired
  RestDataService restDataService

  List<User> getAllUsers() {

    def data = []

    def endpoint =
      grailsApplication.config.calendarData.host +
      grailsApplication.config.calendarData.endpoints.user

    def response = restDataService.get(
      endpoint as String,
      grailsApplication.config.authentication.token as String,
      grailsApplication.config.calendarData.contentType as String) as RestResponse

    if (response?.status == 200) {
      response?.json.each {
        println it
        data << new User(it)
      }
    }

    data
  }
}
