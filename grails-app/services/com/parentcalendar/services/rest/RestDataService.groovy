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
      return null //TODO Implement
    } catch (Exception ex) {
        return null //TODO Implement
    }

    response
  }

  public RestResponse save(String verb, String resource, String authorization, String cType, String payload) {

    def response
    try {
      response = getRestBuilder()."${verb}"(resource) {
          auth authorization
          contentType cType
          json payload
      }
    }catch (ConnectException ex) {
        return null //TODO Implement
    } catch (Exception ex) {
        return null //TODO Implement
    }

    response
  }

    public RestResponse delete(String resource, String authorization, String cType) {

        def response
        try {
            response = getRestBuilder().delete(resource) {
                auth authorization
                contentType cType
            }
        }catch (ConnectException ex) {
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
