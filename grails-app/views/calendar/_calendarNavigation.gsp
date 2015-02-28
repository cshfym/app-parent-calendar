<%@ page import="java.text.SimpleDateFormat" %>

<!-- Calendar Navigation -->

<div class="calendar-navigation-wrapper">
    <div class="calendar-month-year">
        ${calendar.monthName()}
        &nbsp;&nbsp;
        <span style="font-size: 10px;">Selected: ${new SimpleDateFormat("MM-dd-YYYY").format(calendar.date)}</span>
    </div>

    <div class="btn-toolbar calendar-nagivation">
        <g:if test="${calendar.weekView}">
            <button type="button" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('month')">
                Month View
            </button>
            &nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarWeek(-1)">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>&nbsp;&nbsp;Last Week
            </button>
            <button type="button" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarWeek(1)">
                Next Week&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </button>
        </g:if>
        <g:else>
            <button type="button" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('week')">
                Week View
            </button>
            &nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarMonth(-1)">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>&nbsp;&nbsp;${calendar.getPreviousMonthYearString()}
            </button>
            <button type="button" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarMonth(1)">
                ${calendar.getNextMonthYearString()}&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </button>
        </g:else>

        &nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-sm btn-success" style="width: 125px;" onclick="changeCalendarToday()">
            Today&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
        </button>
        &nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-sm btn-warning" style="width: 100px;" onclick="changeNextYear()">
            ${calendar.getNextYearString()}
        </button>
    </div>
    <div style="clear: both;"></div>
</div>
