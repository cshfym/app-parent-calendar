package com.parentcalendar.services.security

import com.parentcalendar.domain.security.UserToken
import com.parentcalendar.services.data.UserDataService
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder

@Component
@Transactional
class UserTokenService {

  private static final log = LogFactory.getLog(this)

  def grailsApplication

  @Autowired
  UserDataService userDataService

  @Autowired
  UserAuthenticationService authenticationService

  @Transactional
  public UserToken getToken() {

      def authenticatedUser = authenticationService.user
      if (!authenticatedUser) {
          return null
      }

      def existingToken = UserToken.find { user == authenticatedUser }

      if (existingToken) {
          if (!isExpired(existingToken.issued)) {
              return existingToken
          } else {
              return refreshToken(existingToken)
          }
      }

      // Generate a new token as one doesn't exist.

      def uniqueTokenString = authenticationService.userTokenString

      // Remove any existing tokens for authenticated user.
      deleteUserTokens(authenticatedUser)

      createUserToken(authenticatedUser, uniqueTokenString)
  }

  /* TODO: This method could be more robust in generating a random token. */
  protected UserToken createUserToken(def user, String tokenString) {

    def token = new UserToken(
      user: user,
      sessionId: authenticationService.sessionId,
      token: tokenString,
      issued: new Date())

    // Save new token.
    token.save(flush: true)
  }

  public void deleteUserTokens(def user) {

    def existingToken = UserToken.find { user == user }
    if (existingToken) {
      existingToken.delete(flush: true)
    }
  }

    /**
     * Refresh the given token by updated the issued date to current.
     * @param token
     * @return
     */
  public UserToken refreshToken(UserToken token) {

      token.issued = new Date()
      token.save(flush: true)
  }

  public String getUserTokenStringFromSession() {

      def session = RequestContextHolder.currentRequestAttributes().getSession()

      if (!session["userToken"]) {
        throw new RuntimeException("Could not get userToken from session.")
      }

      def userToken = session["userToken"] as UserToken

      return userToken.token
  }
  public boolean isExpired(def issued) {

    int expirationMinutes = Integer.valueOf(grailsApplication.config.authentication.expiration as String)
    if (Calendar.getInstance().timeInMillis > (issued.time + (1000 * 60 * expirationMinutes))) {
        return true
    }
    false
  }


  /*
  private String getTokenString(int len = 20) {
    RandomStringUtils.random(len, (("a".."z") + ("0".."9")).join().toCharArray())
  }
  */
}