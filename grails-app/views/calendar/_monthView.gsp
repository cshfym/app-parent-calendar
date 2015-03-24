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
          <g:each in="${pageModel.uiCalendar.weeks}" var="week">
            <tr>
              <g:each in="${week.days}" var="day">
                <td id="td_${day.toMillis()}" class="td-day-container" onclick="launchCreateCalendarEvent(this.id)">
                  <g:if test="${day.isToday()}">
                    <div id="day_${day.getCondensedDateString()}" class="calendar-day-container today"
                         onmouseover="highlightToday(this.id)" onmouseout="unHighlightToday(this.id)" onclick="selectDay(this.id)">
                        <g:render template="innerCalendarEventView" model="[day: day, events: pageModel.getCalendarEventsByDate(day.date)]" />
                    </div>
                  </g:if>
                  <g:else>
                    <div id="day_${day.getCondensedDateString()}" class="calendar-day-container not-in-month"
                       onmouseover="highlightDay(this.id)" onmouseout="unHighlightDay(this.id)" onclick="selectDay(this.id)">
                        <g:render template="innerCalendarEventView" model="[day: day, events: pageModel.getCalendarEventsByDate(day.date)]" />
                    </div>
                  </g:else>
                </td>
              </g:each>
            </tr>
          </g:each>
        </table>
      </div>
    </div>

</g:form>