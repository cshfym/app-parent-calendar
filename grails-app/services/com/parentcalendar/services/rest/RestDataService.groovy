package com.parentcalendar.services.rest

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional

@Transactional
class RestDataService {

  private static RestBuilder restBuilder

  public RestResponse get(String resource, String authorization, String cType) {

    getRestBuilder().get(resource) {
      auth authorization
      contentType cType
    }
  }

  protected RestBuilder getRestBuilder() {

    if (!restBuilder) {
      restBuilder = new RestBuilder()
    }

    restBuilder
  }

}
