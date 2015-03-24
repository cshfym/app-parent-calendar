package com.parentcalendar.services.db

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parentcalendar.domain.exception.DataAuthenticationException
import com.parentcalendar.domain.exception.DataCommunicationException
import com.parentcalendar.domain.exception.GenericDataException
import com.parentcalendar.domain.exception.TokenExpirationException
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
     * @param allData (Retrieve data irrespective of user)
     * @param userId (Retrieve user-specific data)
     * @return List<T>
     * @throws DataAuthenticationException
     * @throws GenericDataException
     */
    public <T> List<T> getAll(Type type, Type listType, String endpoint, String cacheKey = null, Long userId = null)
        throws DataAuthenticationException, GenericDataException {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data = []

        if (cacheKey) {
            def cachedData = cacheService.getCache(cacheKey)
            if (cachedData) {
                data = gson.fromJson(cachedData, listType);
                return data
            }
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken,
                userId) as RestResponse

        if (!response) {
          // No response from restDataService
            throw new DataCommunicationException("No response received from data service")
        }

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

        if (cacheKey && data && !data.isEmpty()) {
          cacheService.setCache(cacheKey, gson.toJson(data, List.class), TTL)
        }

        data
    }

    public Object create(Type type, Object obj, String endpoint) throws DataAuthenticationException, GenericDataException {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def returnObj

        String payload = gson.toJson(obj, type)

        def response = restDataService.post(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                payload,
                userToken) as RestResponse

        if (!response) {
            throw new DataCommunicationException("No response received from data service")
        }

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

        returnObj
    }

    public Object getById(Type type, Long id, String cacheKey = null) throws DataAuthenticationException, GenericDataException {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data
        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        if (cacheKey) {
            def cachedData = cacheService.getCache(cacheKey)
            if (cachedData) {
                data = gson.fromJson(cachedData, type);
                return data
            }
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken,
                null) as RestResponse

        if (!response) {
            // No response from restDataService
            throw new DataCommunicationException("No response received from data service")
        }

        switch (response?.status) {
            case 200:
                data = gson.fromJson(response?.json.toString(), type)
                break
            case 401:
                def msg = "Authentication failed on REST getById() at $endpoint"
                log.error msg
                throw new DataAuthenticationException(msg)
                break
            default:
                def msg = "Invalid response code returned from REST getById at [$endpoint] with response: [$response.status]"
                log.error msg
                throw new GenericDataException(msg)
                break
        }

        if (cacheKey && data) {
            cacheService.setCache(cacheKey, gson.toJson(data, type), TTL)
        }

        data
    }

    public Object getBy(Type type, String col, Object val, boolean allUsers, Long userId = null, String cacheKey = null)
        throws DataAuthenticationException, GenericDataException {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data
        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${col}/${val}" as String

        if (cacheKey) {
            def cachedData = cacheService.getCache(cacheKey)
            if (cachedData) {
                data = gson.fromJson(cachedData, type);
                return data
            }
        }

        def response = restDataService.get(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken,
                allUsers,
                userId) as RestResponse

        if (!response) {
            // No response from restDataService
            throw new DataCommunicationException("No response received from data service")
        }

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

    public void delete(Long id, String flushKey = null) throws DataAuthenticationException, GenericDataException {

        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        def response = restDataService.delete(
                endpoint as String,
                grailsApplication.config.calendarData.contentType as String,
                userToken) as RestResponse

        if (!response) {
            // No response from restDataService
            throw new DataCommunicationException("No response received from data service")
        }

        switch (response?.status) {
            case 200:
                if (flushKey) { flushCache(flushKey) }
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

        if (flushKey) {
            flushCache(flushKey)
        }
    }

    String buildCacheKey(String endpoint) {
        new StringBuilder(userAuthenticationService.userId.toString())
                .append("/")
                .append(endpoint)
                .toString()
    }

    void flushCache(String flushKey) {
        cacheService.flushCache(buildCacheKey(flushKey))
    }
}
