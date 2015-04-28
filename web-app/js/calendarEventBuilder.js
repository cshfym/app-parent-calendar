
function createCalendarEventObject(id, description, fromTime, toTime) {

    var event = {
        id: id,
        description: description,
        fromTime: fromTime,
        toTime: toTime
    };

    return event;
}

/* AJAX */
function createCalendarEventHTML(events, date, containerId, weeklyVisibleHours) {

    var link = "/app-parent-calendar/calendarEvent/createCalendarEventHTML";
    var parameters = {
        events: JSON.stringify(events),
        dateFor: date,
        weeklyVisibleHours: weeklyVisibleHours
    };

    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#" + containerId).html(data);
            adjustCalendarVisuals();
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() {
            $("#createCalendarEventModal").modal("hide");
        }
    });
}