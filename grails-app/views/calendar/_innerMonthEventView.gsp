<%@ page import="com.parentcalendar.services.util.DateUtility; com.parentcalendar.domain.enums.EventTimespan; java.text.SimpleDateFormat" %>

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

<g:render template="innerEventView" />