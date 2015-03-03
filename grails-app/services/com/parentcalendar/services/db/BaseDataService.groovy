package com.parentcalendar.services.db

import com.google.gson.Gson
import com.google.gson.GsonBuilder
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

    public <T> List<T> getAll(Type type, Type listType) {

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
                grailsApplication.config.authentication.token as String,
                grailsApplication.config.calendarData.contentType as String) as RestResponse

        if (response?.status == 200) {
            response?.json.each {
                def obj = gson.fromJson(it.toString(), type)
                data << obj
            }
        } else {
            // TODO Implement
        }

        cacheService.setCache(endpoint, gson.toJson(data, List.class), TTL)

        data
    }

    public Object create(Type type, Object obj) {

        def returnObj

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

        String payload = gson.toJson(obj, type)

        def response = restDataService.save(
                "post",
                endpoint as String,
                grailsApplication.config.authentication.token as String,
                grailsApplication.config.calendarData.contentType as String,
                payload) as RestResponse

        if (response?.status == 201) {
          returnObj = gson.fromJson(response.json.toString(), type)
        }

        flushCache()

        returnObj
    }

    public Object getById(Type type, Long id) {

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
                grailsApplication.config.authentication.token as String,
                grailsApplication.config.calendarData.contentType as String) as RestResponse

        if (response?.status == 200) {
            data = gson.fromJson(response?.json.toString(), type)
        } else {
            // TODO Implement me...
        }

        cacheService.setCache(endpoint, gson.toJson(data, type), TTL)

        data
    }

    public void delete(Long id) {

        def endpoint = grailsApplication.config.calendarData.host + dataPath + "/${id}" as String

        def response = restDataService.delete(
                endpoint as String,
                grailsApplication.config.authentication.token as String,
                grailsApplication.config.calendarData.contentType as String) as RestResponse

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
