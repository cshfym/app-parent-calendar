package com.parentcalendar.services.rest

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional

@Transactional
class RestDataService {

  private static RestBuilder restBuilder

  public RestResponse get(String resource, String authorization, String cType) {

    def response
    try {
      response = getRestBuilder().get(resource) {
        auth authorization
        contentType cType
      }
    } catch (ConnectException ex) {
      return null
    } catch (Exception ex) {
      return null
    }

    response
  }

  protected RestBuilder getRestBuilder() {
    if (!restBuilder) { restBuilder = new RestBuilder() }
    restBuilder
  }

}
