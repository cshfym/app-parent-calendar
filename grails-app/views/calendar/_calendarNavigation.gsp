<%@ page import="java.text.SimpleDateFormat" %>

<!-- Calendar Navigation -->

<div class="calendar-navigation-wrapper">
    <div class="calendar-month-year">
        ${calendar.monthName()}
        &nbsp;&nbsp;
        <span style="font-size: 10px;">Selected: ${new SimpleDateFormat("MM-dd-YYYY").format(calendar.date)}</span>
    </div>


    <div class="btn-toolbar calendar-nagivation">
        <div class="btn-group">
            <a href="#" class="btn btn-sm btn-primary">Default Calendar</a>
            <a href="#" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Calendar #2</a></li>
                <li><a href="#">Calendar #3</a></li>
                <li><a href="#">Calendar #4</a></li>
                <!-- <li class="divider"></li>
                <li><a href="#">Separated link</a></li> -->
            </ul>
        </div>
    </div>

    <div class="btn-toolbar calendar-nagivation" style="margin-right: 10px;">

        <g:if test="${calendar.weekView}">
            <a href="#" type="button" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('month')">
                Month View
            </a>
            &nbsp;&nbsp;&nbsp;
            <a href="#" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarWeek(-1)">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>&nbsp;&nbsp;Last Week
            </a>
            <a href="#" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarWeek(1)">
                Next Week&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </a>
        </g:if>
        <g:else>
            <a href="#" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('week')">Week View</a>
            <a href="#" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarMonth(-1)">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>&nbsp;&nbsp;${calendar.getPreviousMonthYearString()}
            </a>
            <a href="#" class="btn btn-sm btn-info" style="width: 150px;" onclick="changeCalendarMonth(1)">
                ${calendar.getNextMonthYearString()}&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </a>
        </g:else>

        <a href="#" class="btn btn-sm btn-success" style="width: 125px;" onclick="changeCalendarToday()">
            Today&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
        </a>

        <a href="#" class="btn btn-sm btn-warning" onclick="changeNextYear()">${calendar.getNextYearString()}</a>

    </div>


    <div style="clear: both;"></div>
</div>
