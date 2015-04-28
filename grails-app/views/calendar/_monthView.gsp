<g:form controller="calendar">

    <div id="calendar-wrapper">

      <input type="hidden" id="weekCount" value="${pageModel.uiCalendar.getVisibleWeekCount()}" />

      <g:render template="calendarNavigation" model="${pageModel.uiCalendar}" />

      <div>
        <table class="table-month-container">
          <!-- Day of week header -->
          <tr>
            <g:each in="${pageModel.uiCalendar.monthDayHeaderList()}" var="dayName">
              <td class="calendar-day-header">${dayName}</td>
            </g:each>
          </tr>
          <g:each in="${pageModel.uiCalendar.weeks}" var="week">
            <tr>
              <g:each in="${week.days}" var="day">
                <td id="td_${day.toMillis()}" class="td-month-day-container" onclick="launchCreateCalendarEvent(this.id)">
                    <div id="month-day_${day.getCondensedDateString()}" onclick="selectDay(this.id, 'month')"
                         class="calendar-day-container ${(day.isToday() ? 'today' : 'not-in-month')}">
                        <g:render template="innerMonthEventView" model="[day: day, events: pageModel.getAllCalendarEventsByDate(day.date)]" />
                    </div>
                </td>
              </g:each>
            </tr>
          </g:each>
        </table>
      </div>
    </div>

</g:form>