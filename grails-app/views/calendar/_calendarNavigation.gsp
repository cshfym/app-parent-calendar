<!-- Calendar Navigation -->

<div class="calendar-navigation-wrapper">
    <div class="calendar-month-year">
        ${calendar.monthName()}&nbsp;<span style="font-size: 10px;">${calendar.date.toString()}</span>
    </div>

    <div class="btn-toolbar calendar-nagivation">
        <g:if test="${calendar.weekView}">
            <button type="button" class="btn btn-default" style="width: 125px;" onclick="switchView('month')">
                Month View
            </button>
            &nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-info" style="width: 150px;" onclick="changeCalendarWeek(-1)">
                &lt;&nbsp;&nbsp;Last Week
            </button>
            <button type="button" class="btn btn-info" style="width: 150px;" onclick="changeCalendarWeek(1)">
                Next Week&nbsp;&nbsp;&gt;
            </button>
        </g:if>
        <g:else>
            <button type="button" class="btn btn-default" style="width: 125px;" onclick="switchView('week')">
                Week View
            </button>
            &nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-info" style="width: 150px;" onclick="changeCalendarMonth(-1)">
                &lt;&nbsp;&nbsp;${calendar.getPreviousMonthYearString()}
            </button>
            <button type="button" class="btn btn-info" style="width: 150px;" onclick="changeCalendarMonth(1)">
                ${calendar.getNextMonthYearString()}&nbsp;&nbsp;&gt;
            </button>
        </g:else>

        &nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-success" style="width: 100px;" onclick="changeCalendarToday()">
            Today
        </button>
        &nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-info" style="width: 100px;" onclick="changeNextYear()">
            ${calendar.getNextYearString()}
        </button>
    </div>
    <div style="clear: both;"></div>
</div>
