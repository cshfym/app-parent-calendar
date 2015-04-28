package com.parentcalendar.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parentcalendar.domain.core.CalendarConstants
import com.parentcalendar.domain.core.CalendarEvent
import com.parentcalendar.services.util.DateUtility
import grails.converters.JSON
import groovy.xml.MarkupBuilder
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_USER'])
class CalendarEventController {

    private
    Gson gson

    double pixelsPerHour = 41.958

    // AJAX
    def createCalendarEventHTML = {

        gson = new GsonBuilder().setDateFormat(grailsApplication.config.gson.jsdateformat).create()

        def events = params.events
        def dateFor = params.dateFor as String
        def weeklyVisibleHours = params.weeklyVisibleHours

        if (!events || events.isEmpty()) { return "<div></div>" }

        List<CalendarEvent> eventList = []
        def eventJson = JSON.parse(events)
        eventJson.each {
            eventList << gson.fromJson(it.toString(), CalendarEvent.class)
        }

        render buildHourlyEventGrid(dateFor, eventList, weeklyVisibleHours)
    }

    private String buildHourlyEventGrid(String dateFor, List<CalendarEvent> eventList, def weeklyVisibleHours) {

        def timeIntervals = CalendarConstants."get${weeklyVisibleHours}HourTimeIntervals"()

        buildHourlyEventGrid(dateFor, eventList, timeIntervals, Integer.parseInt(weeklyVisibleHours as String))
    }

    /* AJAX
    def buildEventDetailModalDialog = {
        render (template: "/calendarEvent/eventDetailTooltip", model: [eventId: params.eventId] )
    }
      */

    private String buildHourlyEventGrid(def dateFor, def eventList, def timeIntervals, int hours) {

        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)
        setMarkupAttributes(html)

        def totalEventPixels = pixelsPerHour * hours // XXX.XX pixels per N-hour period
        def minutesPerHour = 60 * hours // Minutes per N-hour period
        def pixelsPerMinute = totalEventPixels / minutesPerHour

        def firstIntervalTime = timeIntervals[0]

        html.div(class: "event-container-outer") {
            renderWeekGridTable(timeIntervals, html, dateFor)

            eventList.each { event ->
                def firstIntervalDate = DateUtility.convertIntervalTimeToCurrentDateTime(event.fromTime, firstIntervalTime)
                def upperBound = new Date(firstIntervalDate.getTime()) // 8 AM in 12-hour weekly view
                def eventTotalMinutes = DateUtility.calculateMinuteDifference(event.fromTime, event.toTime)
                def eventMinutesFromTop = DateUtility.calculateMinutesFromUpperBound(upperBound, event.fromTime)
                def eventHeight = Math.round(eventTotalMinutes * pixelsPerMinute)

                // Push top to upper bound if the from time exceeds the bound (< 0)
                def eventTop = Math.round(eventMinutesFromTop * pixelsPerMinute)
                if (eventTop < 0) {
                    eventHeight -= Math.abs(eventTop)
                    eventTop = 0
                }

                def eventLowerBound = eventTop + eventHeight
                if (eventLowerBound > totalEventPixels) {
                    eventHeight -= (eventLowerBound - totalEventPixels)
                }

                renderEventDiv(event, eventHeight, eventTop, html)
            }
        }

        stringWriter.toString()
    }

    private String buildHourlyEventGrid18Hours(def dateFor, def eventList, def timeIntervals) {

        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)
        setMarkupAttributes(html)

        def pixelsPer18Hour = pixelsPerHour * 18 // 755.25 pixels per 18-hour period
        def minutesPer18Hour = 60 * 18 // Minutes per 18-hour period
        def pixelsPerMinute = pixelsPer18Hour/minutesPer18Hour

        def firstIntervalTime = timeIntervals[0]

        html.div(class: "event-container-outer") {
            renderWeekGridTable(timeIntervals, html, dateFor)

            eventList.each { event ->
                def firstIntervalDate = DateUtility.convertIntervalTimeToCurrentDateTime(event.fromTime, firstIntervalTime)
                def upperBound = new Date(firstIntervalDate.getTime()) // 5 AM in 18-hour weekly view
                def eventTotalMinutes = DateUtility.calculateMinuteDifference(event.fromTime, event.toTime)
                def eventMinutesFromTop = DateUtility.calculateMinutesFromUpperBound(upperBound, event.fromTime)
                def eventHeight = Math.round(eventTotalMinutes * pixelsPerMinute)

                // Push top to upper bound if the from time exceeds the bound (< 0)
                def eventTop = Math.round(eventMinutesFromTop * pixelsPerMinute)
                if (eventTop < 0) {
                    eventHeight -= Math.abs(eventTop)
                    eventTop = 0
                }

                def eventLowerBound = eventTop + eventHeight
                if (eventLowerBound > pixelsPer18Hour) {
                    eventHeight -= (eventLowerBound - pixelsPer18Hour)
                }

                renderEventDiv(event, eventHeight, eventTop, html)
            }
        }

        stringWriter.toString()
    }

    private String buildHourlyEventGrid24Hours(def dateFor, def eventList, def timeIntervals) {

        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)
        setMarkupAttributes(html)

        def pixelsPer24Hour = pixelsPerHour * 24 // 1007 pixels per 24-hour period
        def minutesPer24Hour = 24 * 60 // 1440 Minutes per 24-hour period
        def pixelsPerMinute = pixelsPer24Hour/minutesPer24Hour

        html.div(class: "event-container-outer") {
            renderWeekGridTable(timeIntervals, html, dateFor)
            eventList.each { event ->
                def midnight = new Date(event.fromTime.getTime()).clearTime() // Midnight in 24-hour weekly view
                def eventTotalMinutes = DateUtility.calculateMinuteDifference(event.fromTime, event.toTime)
                def eventMinutesFromTop = DateUtility.calculateMinutesFromUpperBound(midnight, event.fromTime)
                def eventHeight = Math.round(eventTotalMinutes * pixelsPerMinute)
                def eventTop = Math.round(eventMinutesFromTop * pixelsPerMinute)

                renderEventDiv(event, eventHeight, eventTop, html)
            }
        }

        stringWriter.toString()
    }

    private void renderEventDiv(CalendarEvent event, Double eventHeight, Double eventTop, MarkupBuilder html) {

        def fullEventTime
        if (event.fromTime != event.toTime) {
            fullEventTime = event.stringTimeSpan
        } else {
            fullEventTime = DateUtility.getTimePrefix(event.fromTime)
        }
        fullEventTime = fullEventTime.replace(":00","")

        def eventDescription = event.description
        if (eventHeight < (pixelsPerHour * 0.5)) {
            eventHeight = pixelsPerHour * 0.5
            eventDescription = ""
        }

        def title = event.stringTimeSpan + ": " + event.description
        html.a (id: "event-container-link_${event.id}", href: "#", title: title) {
            html.div(id: "event-container_${event.id}", class: "event-container", style:"height: ${eventHeight}px; top: ${eventTop}px;") {
                div(class: "event-container-time") {
                    mkp.yield(fullEventTime)
                }
                div(class: "event-container-description") {
                    mkp.yield(eventDescription)
                }
            }
        }
    }

    private void renderWeekGridTable(List timeIntervals, MarkupBuilder html, String dateFor) {
        html.table {
            timeIntervals.each { timeInterval ->
                renderInnerWeekSliceRow("1",  dateFor.concat(timeInterval.replace(" ","")) as String, html)
                renderInnerWeekSliceRow("2",  dateFor.concat(timeInterval.replace(" ","")) as String, html)
            }
        }
    }

    private void renderInnerWeekSliceRow(String number, String dateTimeID, MarkupBuilder html) {
        html.tr {
            td(id: "td-inner-week-slice-${number}_${dateTimeID}", class: "td-inner-week-slice-1") {
                div(id: "half-hour-slice-day-1_${dateTimeID}", class: "half-hour-slice-day-1") { }
            }
        }
    }

    private void setMarkupAttributes(MarkupBuilder builder) {
        builder.doubleQuotes = false
        builder.expandEmptyElements = true
        builder.omitEmptyAttributes = false
        builder.omitNullAttributes = false
    }

}
