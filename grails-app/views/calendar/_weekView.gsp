<g:form controller="calendar">

    <div id="calendar-wrapper">

        <input type="hidden" id="weekCount" value="${pageModel.uiCalendar.getVisibleWeekCount()}" />

        <g:render template="calendarNavigation" model="${pageModel.uiCalendar}" />

        <div>
            <table class="table-month-container">
                <!-- Day of week header -->
                <tr>
                    <g:each in="${pageModel.uiCalendar.dayOfWeekList()}" var="dayName">
                        <td class="calendar-day-header">${dayName}</td>
                    </g:each>
                </tr>

                <tr>
                    <g:set value="${pageModel.uiCalendar.getCurrentDateWeek()}" var="currentWeek" />
                    <g:each in="${currentWeek.days}" var="day">
                        <td class="td-day-container">
                            <g:if test="${day.isToday()}">
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-container today"
                                     onmouseover="highlightToday(this.id)" onmouseout="unHighlightToday(this.id)" onclick="selectDay(this.id)">
                                    <g:if test="${day?.isInCalendarMonth()}">
                                        <div class="day-number">
                                            ${day?.getDayNumber()}
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <div class="day-number-not-in-month">
                                            ${day?.getDayNumber()}
                                        </div>
                                    </g:else>
                                </div>
                            </g:if>
                            <g:else>
                                <div id="day_${day.getCondensedDateString()}" class="calendar-day-container"
                                     onmouseover="highlightDay(this.id)" onmouseout="unHighlightDay(this.id)" onclick="selectDay(this.id)">
                                    <g:if test="${day?.isInCalendarMonth()}">
                                        <div class="day-number">
                                            ${day?.getDayNumber()}
                                        </div>
                                    </g:if>
                                    <g:else>
                                        <div class="day-number-not-in-month">
                                            ${day?.getDayNumber()}
                                        </div>
                                    </g:else>
                                </div>
                            </g:else>
                        </td>
                    </g:each>
                </tr>

            </table>
        </div>
    </div>

</g:form>