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
}
