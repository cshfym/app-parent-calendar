<%@ page import="groovy.json.JsonBuilder" %>

<script type="text/javascript">
    var eventList = new Array(
        <g:each in="${events}" var="event" status="ix">
            ${ix ? ',' : ''} createCalendarEventObject(${event.id}, "${event.description}", "${event.fromTime}", "${event.toTime}")
        </g:each>
    );
    var date = "${day.getCondensedDateString()}";

    createCalendarEventHTML(eventList, date, "${containerId}", "${weeklyVisibleHours}");
</script>

