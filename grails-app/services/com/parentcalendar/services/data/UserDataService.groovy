package com.parentcalendar.services.data

import com.google.gson.reflect.TypeToken
import com.parentcalendar.domain.core.CoreUser
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory

import java.lang.reflect.Type

@Transactional
class UserDataService extends BaseDataService {

  private static final log = LogFactory.getLog(this)

  private Type typeToken = new TypeToken<ArrayList<CoreUser>>(){}.getType();

  List<CoreUser> getAllUsers() {
    super.getAll(typeToken)
  }

  def getTTL() { 30 }
  def getDataPath() { "/user" }

}
