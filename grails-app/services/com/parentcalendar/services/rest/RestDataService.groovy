package com.parentcalendar.services.rest

import com.parentcalendar.domain.enums.Constants
import com.parentcalendar.services.security.UserAuthenticationService
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class RestDataService {

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
    } catch (ConnectException ex) {
      return null //TODO Implement
    } catch (Exception ex) {
      return null //TODO Implement
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
    } catch (ConnectException ex) {
        return null //TODO Implement
    } catch (Exception ex) {
        return null //TODO Implement
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
    } catch (ConnectException ex) {
      return null //TODO Implement
    } catch (Exception ex) {
      return null //TODO Implement
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
    } catch (ConnectException ex) {
      return null //TODO Implement me...
    } catch (Exception ex) {
      return null //TODO Implement me...
    }

    response
  }

  protected RestBuilder getRestBuilder() {
    if (!restBuilder) { restBuilder = new RestBuilder() }
    restBuilder
  }
}
