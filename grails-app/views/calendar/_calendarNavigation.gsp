<%@ page import="java.text.SimpleDateFormat" %>

<!-- Calendar Navigation -->

<div class="calendar-navigation-wrapper">

    <div class="btn-toolbar calendar-navigation-left">
        <g:if test="${pageModel.uiCalendar.weekView}">
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarWeek(-1)" title="Last Week">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </a>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarWeek(1)" title="Next Week">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            </a>
        </g:if>
        <g:else>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarYear(-1)" title="Last Year">
                <span class="glyphicon glyphicon-fast-backward" aria-hidden="true"></span>
            </a>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarMonth(-1)" title="${pageModel.uiCalendar.getPreviousMonthYearString()}">
                <span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
            </a>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarMonth(1)" title="${pageModel.uiCalendar.getNextMonthYearString()}">
                <span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
            </a>
            <a href="#" class="btn btn-sm btn-info" onclick="changeCalendarYear(1)" title="Next Year">
                <span class="glyphicon glyphicon-fast-forward" aria-hidden="true"></span>
            </a>
        </g:else>
    <!--
    <div class="calendar-month-year">
        ${pageModel.uiCalendar.monthName()}
        &nbsp;&nbsp;
        <span style="font-size: 10px;">Selected: ${new SimpleDateFormat("MM-dd-YYYY").format(pageModel.uiCalendar.date)}</span>
    </div>
    -->
    </div>

    <div class="calendar-navigation-left">
        <div class="calendar-month-year">${pageModel.uiCalendar.monthName()}</div>
    </div>

    <div class="btn-toolbar calendar-nagivation-right">
        <div class="btn-group">
            <a href="#" class="btn btn-sm btn-primary">Default Calendar</a>
            <a href="#" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each in="${pageModel.userCalendars}" var="userDefinedCalendar">
                    <g:if test="${!userDefinedCalendar._default}">
                        <li><a href="#">${userDefinedCalendar.description}</a></li>
                    </g:if>
                </g:each>
                <g:if test="${pageModel.userCalendars.size() > 1}">
                    <li class="divider"></li>
                </g:if>
                <li><a href="#">Create New...</a></li>
            </ul>
        </div>
    </div>

    <div class="btn-toolbar calendar-nagivation-right" style="margin-right: 10px;">

        <g:if test="${pageModel.uiCalendar.weekView}">
            <a href="#" type="button" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('month')">Month View</a>
        </g:if>
        <g:else>
            <a href="#" class="btn btn-sm btn-success" style="width: 125px;" onclick="switchView('week')">Week View</a>
        </g:else>

        <a href="#" class="btn btn-sm btn-success" style="width: 125px;" onclick="changeCalendarToday()">
            Today&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
        </a>

    </div>


    <div class="clearfix"></div>
</div>
