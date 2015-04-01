<%@ page import="com.parentcalendar.services.util.DateUtility; com.parentcalendar.domain.enums.EventTimespan; java.text.SimpleDateFormat" %>

<g:if test="${events?.size() > 0}">
    <g:each in="${events}" var="event" status="i">
        <g:if test="${i < 4}">
            <g:if test="${(event.eventTimespan == EventTimespan.SAME_DAY)}">
                <div class="event-same-day">
                    <g:link>${event.eventTimeAndDescription}</g:link>
                </div>
            </g:if>
            <g:if test="${(event.eventTimespan == EventTimespan.ALL_DAY)}">
                <div class="event-all-day">
                    <g:link>${event.eventTimeAndDescription}</g:link>
                </div>
            </g:if>
            <g:if test="${(event.eventTimespan == EventTimespan.MULTI_DAY)}">
                <div class="event-all-day">
                    <g:if test="${DateUtility.isSameDay(day.date, event.fromTime)}">
                        <g:link>${event.eventTimeAndDescription}</g:link>
                    </g:if>
                    <g:else>
                        <g:link>${event.description}</g:link>
                    </g:else>
                    <g:if test="${DateUtility.isBefore(day.date, event.toTime)}">
                        <div style="float: right;">
                            <span class="glyphicon glyphicon-chevron-right" style="color: #7785AA;"></span>
                        </div>
                    </g:if>
                </div>
            </g:if>
            <g:if test="${(event.eventTimespan == EventTimespan.MULTI_DAY_ALL_DAY)}">
                <div class="event-all-day">
                    <g:link>${event.eventTimeAndDescription}</g:link>
                    <g:if test="${DateUtility.isBefore(day.date, event.toTime)}">
                        <div style="float: right;">
                            <span class="glyphicon glyphicon-chevron-right" style="color: #7785AA;"></span>
                        </div>
                    </g:if>
                </div>
            </g:if>
        </g:if>
    </g:each>
</g:if>