<g:form controller="calendar">

    <div id="calendar-wrapper">

      <input type="hidden" id="weekCount" value="${calendar.getVisibleWeekCount()}" />

      <g:render template="calendarNavigation" model="${calendar}" />

      <div>
        <table class="table-month-container">
          <!-- Day of week header -->
          <tr>
            <g:each in="${calendar.dayOfWeekList()}" var="dayName">
              <td class="calendar-day-header">${dayName}</td>
            </g:each>
          </tr>
          <g:each in="${calendar.weeks}" var="week">
            <tr>
              <g:each in="${week.days}" var="day">
                <td id="day_${day.getCondensedDateString()}" class="td-day-container"
                    onmouseover="highlightDay(this.id)"
                    onmouseout="unHighlightDay(this.id)"
                    onclick="selectDay(this.id)"> <!-- TODO Move this to inner div. -->
                  <g:if test="${day.isToday()}">
                    <div class="calendar-day-container today">${day.getDayNumber()}</div>
                  </g:if>
                  <g:elseif test="${calendar.isSelectedDate(day.date)}">
                        <div class="calendar-day-container selected-date">${day.getDayNumber()}</div>
                  </g:elseif>
                  <g:else>
                    <div class="calendar-day-container">${day.getDayNumber()}</div>
                  </g:else>
                </td>
              </g:each>
            </tr>
          </g:each>
        </table>
      </div>
    </div>

</g:form>