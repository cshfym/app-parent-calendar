package com.parentcalendar.services.db

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parentcalendar.domain.exception.DataAuthenticationException
import com.parentcalendar.domain.exception.GenericDataException
import com.parentcalendar.domain.security.User
import com.parentcalendar.services.cache.RedisCacheService
import com.parentcalendar.services.db.IDataService
import com.parentcalendar.services.rest.RestDataService
import grails.plugins.rest.client.RestResponse
import org.springframework.beans.factory.annotation.Autowired

import java.lang.reflect.Type


abstract class BaseDataService implements IDataService {

    def grailsApplication

    @Autowired
    RestDataService restDataService

    @Autowired
    RedisCacheService cacheService

    Gson gson

    public <T> List<T> getAll(Type type, Type listType, boolean isGlobal = false) throws DataAuthenticationException, GenericDataException  {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data = []

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        def cachedData = cacheService.getCache(endpoint)
        if (cachedData) {
            data = gson.fromJson(cachedData, listType);
            return data
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken,
                isGlobal) as RestResponse

        throw new DataAuthenticationException("Dummy exception.")
        /*
        switch (response?.status) {
          case 200:
              response?.json.each {
                  def obj = gson.fromJson(it.toString(), type)
                  data << obj
              }
              break
          case 401:
              def msg = "Authentication failed on REST getAll() at $endpoint"
              log.error msg
              throw new DataAuthenticationException(msg)
          default:
              def msg = "Invalid response code returned from REST getAll at [$endpoint] with response: [$response]"
              log.error msg
              throw new GenericDataException(msg)
        }

        if (data && !data.isEmpty()) {
          cacheService.setCache(endpoint, gson.toJson(data, List.class), TTL)
        }

        data
        */
    }

    public Object create(Type type, Object obj) throws DataAuthenticationException, GenericDataException  {

        def returnObj

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        String payload = gson.toJson(obj, type)

        def response = restDataService.post(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                payload,
                userToken) as RestResponse

        if (response?.status == 201) {
          returnObj = gson.fromJson(response.json.toString(), type)
        }

        flushCache()

        returnObj
    }

    public Object getById(Type type, Long id) throws DataAuthenticationException, GenericDataException  {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data

        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        def cachedData = cacheService.getCache(endpoint)
        if (cachedData) {
            data = gson.fromJson(cachedData, type);
            return data
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken) as RestResponse

        if (response?.status == 200) {
            data = gson.fromJson(response?.json.toString(), type)
        } else {
            // TODO Implement me...
        }

        cacheService.setCache(endpoint, gson.toJson(data, type), TTL)

        data
    }

    public void delete(Long id) throws DataAuthenticationException, GenericDataException  {

        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        def response = restDataService.delete(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken) as RestResponse

        if (response?.status == 200) {
            flushCache()
        } else {
            // TODO Implement me... "Could not delete"
        }
    }

    private void flushCache() {
        def endpoint = grailsApplication.config.calendarData.host + dataPath as String
        cacheService.flushCache(endpoint)
    }

}
