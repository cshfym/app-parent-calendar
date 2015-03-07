<%@ page import="java.text.SimpleDateFormat" %>

<!-- Calendar Navigation -->

<div class="calendar-navigation-wrapper">
    <div class="btn-toolbar">
        <div class="calendar-month-year">${calendar.monthName()}</div>
        <g:if test="${calendar.weekView}">
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarWeek(-1)" title="Last Week">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </a>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarWeek(1)" title="Next Week">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </a>
        </g:if>
        <g:else>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarMonth(-1)" title="${calendar.getPreviousMonthYearString()}">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </a>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarMonth(1)" title="${calendar.getNextMonthYearString()}">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </a>
        </g:else>

    <!--
    <div class="calendar-month-year">
        ${calendar.monthName()}
        &nbsp;&nbsp;
        <span style="font-size: 10px;">Selected: ${new SimpleDateFormat("MM-dd-YYYY").format(calendar.date)}</span>
    </div>
    -->
    </div>

    <div class="btn-toolbar calendar-nagivation">
        <div class="btn-group">
            <a href="#" class="btn btn-sm btn-primary">Default Calendar</a>
            <a href="#" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each in="${userCalendars}" var="userDefinedCalendar">
                    <li><a href="#">${userDefinedCalendar.description}</a></li>
                </g:each>
                <!-- <li class="divider"></li>
                <li><a href="#">Separated link</a></li> -->
            </ul>
        </div>
    </div>

    <div class="btn-toolbar calendar-nagivation" style="margin-right: 10px;">

        <g:if test="${calendar.weekView}">
            <a href="#" type="button" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('month')">Month View</a>
        </g:if>
        <g:else>
            <a href="#" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('week')">Week View</a>
        </g:else>

        <a href="#" class="btn btn-sm btn-success" style="width: 125px;" onclick="changeCalendarToday()">
            Today&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
        </a>

        <a href="#" class="btn btn-sm btn-warning" onclick="changeNextYear()">${calendar.getNextYearString()}</a>

    </div>


    <div style="clear: both;"></div>
</div>
