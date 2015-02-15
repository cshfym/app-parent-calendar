<div id="calendar-wrapper">

  <input type="hidden" id="weekCount" value="${calendar.weeks.size()}" />

  <div class="calendar-navigation-wrapper">
    <div class="calendar-month-year">
      ${calendar.monthName()}
    </div>
    <!-- Calendar Navigation -->
    <div class="calendar-nagivation">
      <button type="button" class="btn btn-success" style="width: 100px;" onclick="changeCalendarToday()">
        Today&nbsp;<span class="glyphicon glyphicon-calendar"></span>
      </button>
      &nbsp;&nbsp;&nbsp;
      <button type="button" class="btn btn-info" style="width: 150px;" onclick="changeCalendarMonth(-1)">
        &lt;&nbsp;&nbsp;${calendar.getPreviousMonthYearString()}
      </button>
      <button type="button" class="btn btn-info" style="width: 150px;" onclick="changeCalendarMonth(1)">
        ${calendar.getNextMonthYearString()}&nbsp;&nbsp;&gt;
      </button>
      &nbsp;&nbsp;&nbsp;
      <button type="button" class="btn btn-info" style="width: 100px;" onclick="changeNextYear()">
        ${calendar.getNextYearString()}
      </button>
    </div>
    <div style="clear: both;"></div>
  </div>

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
            <td class="td-day-container">
              <g:if test="${day.isToday()}">
                <div class="calendar-day-container today">${day.getDayNumber()}</div>
              </g:if>
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
