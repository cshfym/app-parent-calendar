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