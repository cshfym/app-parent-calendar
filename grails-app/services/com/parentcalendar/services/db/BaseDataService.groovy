package com.parentcalendar.services.db

import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
        }

        cacheService.setCache(endpoint, gson.toJson(data, List.class), TTL)

        data
    }

    public Object getById(Type type) {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.dateformat).create()

        def data

        def endpoint = grailsApplication.config.calendarData.host + dataPath as String

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
            response?.json.each {
                def obj = gson.fromJson(it.toString(), type)
                data << obj
            }
        }

        cacheService.setCache(endpoint, gson.toJson(data, type), TTL)

        data

    }
}
