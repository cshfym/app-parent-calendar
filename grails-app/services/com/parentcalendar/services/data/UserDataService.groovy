package com.parentcalendar.services.data

import com.parentcalendar.domain.security.User
import grails.transaction.Transactional
import org.springframework.stereotype.Component


@Component
@Transactional
class UserDataService {

    // GORM
    public List<User> getAll() {
        User.findAll()
    }

    public User findByUsername(String username) {
        User.find { username == username }
    }

    public User findByEmail(String email) {
        User.find { email == email }
    }

    public User createUser(Map attributes) {

        User user = new User()

        attributes.each {
            user."$it.key" = it.value
        }

        user.save(flush: true)
    }
}
