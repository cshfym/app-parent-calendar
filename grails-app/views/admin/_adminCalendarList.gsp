<div id="calendar-list-wrapper">
    <table class="table table-striped table-hover">
        <thead><td><h3>Calendars</h3></td></thead>
        <g:each in="${calendars}" var="calendar">
            <tr>
                <td>${calendar}</td>
                <td>
                    <a class="btn btn-xs btn-danger" href="#" onclick="deleteCalendar(${calendar.id})">Delete</a>
                </td>
            </tr>
        </g:each>
    </table>
</div>
