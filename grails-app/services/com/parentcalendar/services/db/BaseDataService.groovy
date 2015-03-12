package com.parentcalendar.services.db

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parentcalendar.domain.exception.DataAuthenticationException
import com.parentcalendar.domain.exception.GenericDataException
import com.parentcalendar.domain.security.User
import com.parentcalendar.services.cache.RedisCacheService
import com.parentcalendar.services.db.IDataService
import com.parentcalendar.services.rest.RestDataService
import com.parentcalendar.services.security.UserAuthenticationService
import com.parentcalendar.services.security.UserTokenService
import grails.plugins.rest.client.RestResponse
import org.springframework.beans.factory.annotation.Autowired

import java.lang.reflect.Type


abstract class BaseDataService implements IDataService {

    def grailsApplication

    @Autowired
    UserAuthenticationService userAuthenticationService

    @Autowired
    RestDataService restDataService

    @Autowired
    RedisCacheService cacheService

    @Autowired
    UserTokenService userTokenService

    Gson gson

    /**
     * Gets all entities for the specified type.
     * @param type
     * @param listType
     * @param allData (Retrieve data irrespective of user
     * @return List<T>
     * @throws DataAuthenticationException
     * @throws GenericDataException
     */
    public <T> List<T> getAll(Type type, Type listType, boolean allUsers)
        throws DataAuthenticationException, GenericDataException  {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data = []
        def cacheKey = getCacheKey("getAll-" + String.valueOf(allUsers))
        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        def cachedData = cacheService.getCache(cacheKey)
        if (cachedData) {
            data = gson.fromJson(cachedData, listType);
            return data
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                allUsers) as RestResponse

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
              def msg = "Invalid response code returned from REST getAll at [$endpoint] with response: [$response.status]"
              log.error msg
              throw new GenericDataException(msg)
        }

        if (data && !data.isEmpty()) {
          cacheService.setCache(cacheKey, gson.toJson(data, List.class), TTL)
        }

        data
    }

    public Object create(Type type, Object obj) throws DataAuthenticationException, GenericDataException  {

        def returnObj

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        String payload = gson.toJson(obj, type)

        def response = restDataService.post(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                payload) as RestResponse

        switch (response?.status) {
            case 200:
            case 201:
                returnObj = gson.fromJson(response.json.toString(), type)
                break
            case 401:
                def msg = "Authentication failed on REST create() at $endpoint"
                log.error msg
                throw new DataAuthenticationException(msg)
            default:
                def msg = "Invalid response code returned from REST create at [$endpoint] with response: [$response.status]"
                log.error msg
                throw new GenericDataException(msg)
        }

        flushCache()

        returnObj
    }

    public Object getById(Type type, Long id) throws DataAuthenticationException, GenericDataException  {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data
        def cacheKey = getCacheKey("getById")
        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        def cachedData = cacheService.getCache(cacheKey)
        if (cachedData) {
            data = gson.fromJson(cachedData, type);
            return data
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String) as RestResponse

        switch (response?.status) {
            case 200:
                data = gson.fromJson(response?.json.toString(), type)
                break
            case 401:
                def msg = "Authentication failed on REST getById() at $endpoint"
                log.error msg
                throw new DataAuthenticationException(msg)
            default:
                def msg = "Invalid response code returned from REST getById at [$endpoint] with response: [$response.status]"
                log.error msg
                throw new GenericDataException(msg)
        }

        if (data) {
            cacheService.setCache(cacheKey, gson.toJson(data, type), TTL)
        }

        data
    }

    public Object getBy(Type type, String col, Object val, boolean allUsers) throws DataAuthenticationException, GenericDataException  {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data
        // def cacheKey = getCacheKey("getBy/${col}/${val}")
        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${col}/${val}" as String

        def cachedData = cacheService.getCache(cacheKey)
        if (cachedData) {
            data = gson.fromJson(cachedData, type);
            return data
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                allUsers) as RestResponse

        switch (response?.status) {
            case 200:
                data = gson.fromJson(response?.json.toString(), type)
                break
            case 401:
                def msg = "Authentication failed on REST getById() at $endpoint"
                log.error msg
                throw new DataAuthenticationException(msg)
            default:
                def msg = "Invalid response code returned from REST getById at [$endpoint] with response: [$response.status]"
                log.error msg
                throw new GenericDataException(msg)
        }

        if (data) {
            cacheService.setCache(cacheKey, gson.toJson(data, type), TTL)
        }

        data
    }

    public void delete(Long id) throws DataAuthenticationException, GenericDataException  {

        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        def response = restDataService.delete(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String) as RestResponse

        switch (response?.status) {
            case 200:
                flushCache()
                break
            case 401:
                def msg = "Authentication failed on REST delete() at $endpoint"
                log.error msg
                throw new DataAuthenticationException(msg)
            default:
                def msg = "Invalid response code returned from REST delete at [$endpoint] with response: [$response.status]"
                log.error msg
                throw new GenericDataException(msg)
        }
    }

    void flushCache() {
        cacheService.flushCache(getCacheKey())
    }


}
