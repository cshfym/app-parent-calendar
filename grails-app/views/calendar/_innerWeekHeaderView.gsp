<table>
    <g:each in="${events}" var="event" status="i">
        <tr>
            <td id="td-header-event_${day.getCondensedDateString()}" class="td-header-event-container">
                <g:render template="blockEventView" model="[event: event, eventNumber: i]" />
            </td>
        </tr>
    </g:each>
</table>