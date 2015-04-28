<%@ page import="com.parentcalendar.services.util.DateUtility; com.parentcalendar.domain.enums.EventTimespan; java.text.SimpleDateFormat" %>

<g:if test="${(event.eventTimespan == EventTimespan.SAME_DAY)}">
    <div class="event-same-day" title="${event.stringTimeSpan + ": " + event.description}">
        ${event.eventStartTimeAndDescription}
    </div>
</g:if>
<g:if test="${(event.eventTimespan == EventTimespan.ALL_DAY)}">
    <div class="event-all-day" title="${event.description}">
        ${event.eventStartTimeAndDescription}
    </div>
</g:if>
<g:if test="${(event.eventTimespan == EventTimespan.MULTI_DAY)}">
    <div class="event-all-day" title="${event.description}">
        <g:if test="${DateUtility.isSameDay(day.date, event.fromTime)}">
            ${event.eventStartTimeAndDescription}
        </g:if>
        <g:elseif test="${DateUtility.isSameDay(day.date, event.toTime)}">
            ${event.eventEndTimeAndDescription}
            <div style="float: right;">
                <span class="glyphicon glyphicon-step-forward" style="color: #7785AA;"></span>
            </div>
        </g:elseif>
        <g:else>
            ${event.description}
        </g:else>
        <g:if test="${DateUtility.isBefore(day.date, event.toTime)}">
            <div style="float: right;">
                <span class="glyphicon glyphicon-play" style="color: #7785AA;"></span>
            </div>
        </g:if>
    </div>
</g:if>
<g:if test="${(event.eventTimespan == EventTimespan.MULTI_DAY_ALL_DAY)}">
    <div class="event-all-day" title="${event.description}">
        ${event.eventStartTimeAndDescription}
        <g:if test="${DateUtility.isBefore(day.date, event.toTime)}">
            <div style="float: right;">
                <span class="glyphicon glyphicon-play" style="color: #7785AA;"></span>
            </div>
        </g:if>
    </div>
</g:if>
