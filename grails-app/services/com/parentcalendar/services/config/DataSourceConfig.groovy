package com.parentcalendar.services.config

import org.codehaus.groovy.grails.io.support.IOUtils
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

public static ConfigObject getDataSourcesConfig() {
    Properties dataSourcesProps = new Properties()
    InputStream resourceStream
    try {
        resourceStream = new PathMatchingResourcePatternResolver().getResource("file:/etc/grails/sf2_mysql.groovy").inputStream
        dataSourcesProps.load(resourceStream)
    } finally {
        IOUtils.closeQuietly(resourceStream)
    }
    new ConfigSlurper().parse(dataSourcesProps)
}