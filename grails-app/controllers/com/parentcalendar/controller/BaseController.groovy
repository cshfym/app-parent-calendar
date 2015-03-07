package com.parentcalendar.controller


class BaseController {

    def beforeInterceptor = {
      println "Do something with me."
    }
}
