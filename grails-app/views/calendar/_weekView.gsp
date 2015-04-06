<g:form controller="calendar">

    <div id="calendar-wrapper">

        <input type="hidden" id="weekCount" value="${pageModel.uiCalendar.getVisibleWeekCount()}" />

        <g:render template="calendarNavigation" model="${pageModel.uiCalendar}" />

        <div>
            <table class="table-month-container">

                <g:set value="${pageModel.uiCalendar.getCurrentDateWeek()}" var="currentWeek" />

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
                            <g:if test="${day.isToday()}">
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-header-container today">
                                    <g:render template="innerWeekHeaderView" model="[day: day, events: allDayEvents]" />
                                </div>
                            </g:if>
                            <g:else>
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-header-container">
                                    <g:render template="innerWeekHeaderView" model="[day: day, events: allDayEvents]" />
                                </div>
                            </g:else>
                        </td>
                    </g:each>
                </tr>
                <input type="hidden" id="weekAllDayEventCount" value="${weekAllDayEventCount}" />

                <!-- Same-Day Events Row -->
                <tr style="border: 1px solid #a0a0a0;">
                    <td>
                        <div class="calendar-hours-container">
                            <table>
                                <g:each in="${pageModel.getHourTimeIntervals()}" var="timeInterval">
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
                    <g:each in="${currentWeek.days}" var="day">
                        <g:set value="${pageModel.getSameDayCalendarEventsByDate(day.date)}" var="sameDayDayEvents" />
                        <td class="td-day-container">
                            <g:if test="${day.isToday()}">
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-container today" onclick="selectDay(this.id)">
                                    <g:render template="innerWeekEventView" model="[day: day, events: sameDayDayEvents]" />
                                </div>
                            </g:if>
                            <g:else>
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-container" onclick="selectDay(this.id)">
                                    <g:render template="innerWeekEventView" model="[day: day, events: sameDayDayEvents]" />
                                </div>
                            </g:else>
                        </td>
                    </g:each>
                </tr>

            </table>
        </div>
    </div>

</g:form>