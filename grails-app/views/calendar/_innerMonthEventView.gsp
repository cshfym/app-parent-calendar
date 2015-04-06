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

<g:if test="${events?.size() > 0}">
    <g:each in="${events}" var="event" status="i">
        <g:if test="${eventNumber < 4}">
            <g:render template="blockEventView" model="[event: event, eventNumber: i]" />
        </g:if>
    </g:each>
</g:if>