import com.parentcalendar.domain.security.Role
import com.parentcalendar.domain.security.User
import com.parentcalendar.domain.security.UserRole

class BootStrap {

    def init = { servletContext ->
/*
      def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
      def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

      //def adminRole = Role.find { authority == "ROLE_ADMIN" }

      def testUser = new User(username: 'test_1', password: 'neo', email: "test_1@gmail.com")
      testUser.save(flush: true)
*/

      //def adminRole = Role.find { authority == "ROLE_ADMIN" }

      def testUser = User.find { username == "test_1" }
      testUser.setPassword("test_1")
      testUser.save(flush: true)

      // UserRole.create(testUser,adminRole,true)
    }

    def destroy = {
    }
}
