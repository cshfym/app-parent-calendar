package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.CoreUser
import com.parentcalendar.services.db.BaseDataService
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import java.lang.reflect.Type

@Transactional
class CoreUserDataService extends BaseDataService {

    private static final log = LogFactory.getLog(this)

    private Type typeToken = new TypeToken<ArrayList<CoreUser>>(){}.getType();

    List<CoreUser> getAllUsers(boolean noCache = false) {

        def cacheKey = (noCache) ? null : buildCacheKey("getAllUsers")

        try {
            super.getAll(CoreUser.class, typeToken, true, null, cacheKey)
        } catch (Exception ex) {
            throw ex
        }
    }

    CoreUser getById(Long id, boolean noCache = false) {

        def cacheKey = (noCache) ? null : buildCacheKey("getById?id=${id}")

        try {
            super.getById(CoreUser.class, id, cacheKey)
        } catch (Exception ex) {
            throw ex
        }
    }

    List<CoreUser> getBy(String column, String attribute, boolean allUsers, Long userId, boolean noCache = false) {

        def cacheKey = (noCache) ? null : buildCacheKey("getBy/${column}/${attribute}?allUsers=${allUsers}&userId=${userId}")

        try {
            super.getBy(CoreUser.class, column, attribute, allUsers, userId, cacheKey)
        } catch (Exception ex) {
            throw ex
        }
    }

    def getTTL() { 30 }
    def getDataPath() { "/user" }
    String getUserToken() { userTokenService.userTokenStringFromSession }

}
