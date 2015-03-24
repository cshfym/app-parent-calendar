package com.parentcalendar.services.rest

import com.parentcalendar.domain.enums.Constants
import com.parentcalendar.services.security.UserAuthenticationService
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class RestDataService {

  private static final log = LogFactory.getLog(this)

  @Autowired
  UserAuthenticationService authenticationService

  private static RestBuilder restBuilder

  public RestResponse get(String resource, String cType, String userToken, Long userId = null) {

    def response
    try {
      response = getRestBuilder().get(resource) {
        auth userToken
        contentType cType
        header Constants.X_AUTH_USER_ID.value, (userId) ? userId.toString() : ""
      }
    } catch (ConnectException | Exception ex) {
      log.error ex.message, ex
      throw ex
    }

    response
  }

  public RestResponse post(String resource, String cType, String payload, String userToken) {

    def response
    try {
      response = getRestBuilder().post(resource) {
        auth userToken
        contentType cType
        json payload
      }
    } catch (ConnectException | Exception ex) {
      log.error ex.message, ex
      throw ex
    }


    response
  }

  public RestResponse put(String resource, String cType, String payload, String userToken) {

    def response
    try {
      response = getRestBuilder().put(resource) {
        auth userToken
        contentType cType
        json payload
      }
    } catch (ConnectException | Exception ex) {
      log.error ex.message, ex
      throw ex
    }


    response
  }

  public RestResponse delete(String resource, String cType, String userToken) {

    def response
    try {
      response = getRestBuilder().delete(resource) {
        auth userToken
        contentType cType
      }
    } catch (ConnectException | Exception ex) {
      log.error ex.message, ex
      throw ex
    }


    response
  }

  protected RestBuilder getRestBuilder() {
    if (!restBuilder) { restBuilder = new RestBuilder() }
    restBuilder
  }
}
