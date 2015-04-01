<g:form controller="calendar">

    <div id="calendar-wrapper">

        <input type="hidden" id="weekCount" value="${pageModel.uiCalendar.getVisibleWeekCount()}" />

        <g:render template="calendarNavigation" model="${pageModel.uiCalendar}" />

        <div>
            <table class="table-month-container">
                <!-- Day of week header -->
                <tr>
                    <td class="calendar-day-header">
                        TimeZone
                    </td>
                    <g:each in="${pageModel.uiCalendar.weekDayHeaderList()}" var="dayName">
                        <td class="calendar-day-header">${dayName}</td>
                    </g:each>
                </tr>
                <tr>
                    <g:set value="${pageModel.uiCalendar.getCurrentDateWeek()}" var="currentWeek" />
                    <td>
                        Hours
                    </td>
                    <g:each in="${currentWeek.days}" var="day">
                        <td class="td-day-container">
                            <g:if test="${day.isToday()}">
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-container today"
                                     onmouseover="highlightToday(this.id)" onmouseout="unHighlightToday(this.id)" onclick="selectDay(this.id)">
                                    <g:render template="innerWeekEventView" model="[day: day, events: pageModel.getCalendarEventsByDate(day.date)]" />
                                </div>
                            </g:if>
                            <g:else>
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-container"
                                     onmouseover="highlightDay(this.id)" onmouseout="unHighlightDay(this.id)" onclick="selectDay(this.id)">
                                    <g:render template="innerWeekEventView" model="[day: day, events: pageModel.getCalendarEventsByDate(day.date)]" />
                                </div>
                            </g:else>
                        </td>
                    </g:each>
                </tr>

            </table>
        </div>
    </div>

</g:form>