
/*
function launchEventDetailModal(id) {

    var link = "/app-parent-calendar/calendarEvent/buildEventDetailModalDialog";
    var parameters = { eventId: id };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#event-detail-modal-dialog").html(data);   // Inner Content
            $("#event-detail-modal").modal("show");     // Dialog Wrapper
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });

}
*/