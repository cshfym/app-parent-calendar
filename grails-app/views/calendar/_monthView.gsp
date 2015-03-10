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
                    <div id="day_${day.getCondensedDateString()}" class="calendar-day-container not-in-month"
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
          </g:each>
        </table>
      </div>
    </div>

</g:form>