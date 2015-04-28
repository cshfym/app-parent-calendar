<%@ page import="com.parentcalendar.domain.core.CalendarConstants" %>

<g:form controller="calendar">

    <g:set value="${pageModel.uiCalendar.getCurrentDateWeek()}" var="currentWeek" />

    <div id="calendar-wrapper">

        <input type="hidden" id="weekCount" value="${pageModel.uiCalendar.getVisibleWeekCount()}" />

        <g:render template="calendarNavigation" model="${pageModel.uiCalendar}" />

        <g:if test="${pageModel.uiCalendar.weeklyVisibleHours == 12}">
            <g:set var="timeIntervalValues" value="${CalendarConstants.get12HourTimeIntervals()}" />
        </g:if>
        <g:if test="${pageModel.uiCalendar.weeklyVisibleHours == 18}">
            <g:set var="timeIntervalValues" value="${CalendarConstants.get18HourTimeIntervals()}" />
        </g:if>
        <g:if test="${pageModel.uiCalendar.weeklyVisibleHours == 24}">
            <g:set var="timeIntervalValues" value="${CalendarConstants.get24HourTimeIntervals()}" />
        </g:if>

        <table class="table-month-container">

            <!-- Day of week header -->
            <tr>
                <td class="calendar-day-header">
                    TZ
                </td>
                <g:each in="${pageModel.uiCalendar.weekDayHeaderList()}" var="dayName">
                    <td class="calendar-day-header">${dayName}</td>
                </g:each>
            </tr>

            <!-- All-Day Events Row -->
            <g:set value="0" var="weekAllDayEventCount" />
            <tr id="tr-all-day-events" style="border: 1px solid #a0a0a0; border-bottom: none;">
                <td>&nbsp;</td>
                <g:each in="${currentWeek.days}" var="day">
                    <g:set value="${pageModel.getAllDayCalendarEventsByDate(day.date)}" var="allDayEvents" />
                    <g:set value="${Integer.parseInt(weekAllDayEventCount.toString()) + allDayEvents.size()}" var="weekAllDayEventCount" />
                    <td class="td-day-header-container">
                        <div id="week-all-day_${day.getCondensedDateString()}" class="calendar-day-header-container">
                            <g:render template="innerWeekHeaderView" model="[day: day, events: allDayEvents]" />
                        </div>
                    </td>
                </g:each>
            </tr>
            <input type="hidden" id="weekAllDayEventCount" value="${weekAllDayEventCount}" />

            <!-- Same-Day Events Row -->
            <tr style="border: 1px solid #a0a0a0;">
                <!-- Legend Column -->
                <td>
                    <div class="calendar-hours-container">
                        <table>
                            <g:each in="${timeIntervalValues}" var="timeInterval">
                                <tr>
                                    <td id="td-hour-slice-legend_${timeInterval.replace(" ","")}">
                                        <div id="hour-slice-legend_${timeInterval.replace(" ","")}" class="hour-slice-legend">
                                            ${timeInterval}
                                        </div>
                                    </td>
                                </tr>
                            </g:each>
                        </table>
                    </div>
                </td>
                <!-- Weekly Day Column -->
                <g:each in="${currentWeek.days}" var="day">
                    <g:set value="${pageModel.getSameDayCalendarEventsByDate(day.date)}" var="sameDayEvents" />
                    <td class="td-week-day-container">
                        <g:set var="containerId" value="week-day_${day.getCondensedDateString()}" />
                        <div id="${containerId}" onclick="selectDay(this.id, 'week')"
                             class="week-calendar-day-container ${(day.isToday() ? 'today' : '')}">
                            <g:render template="innerWeekEventView" model="[day: day, events: sameDayEvents, containerId: containerId, weeklyVisibleHours: timeIntervalValues.size()]" />
                        </div>
                    </td>
                </g:each>
            </tr>

        </table>
    </div>

</g:form>