
function createUser() {
    var link = "/app-parent-calendar/admin/createUser";
    var parameters = { };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#user-list-wrapper").html(data);
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function createCalendarForUser(id) {
    var link = "/app-parent-calendar/admin/createCalendarForUser";
    var parameters = { userId: id };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-list-wrapper").html(data);
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

/* Triggered from modal create calendar dialog. */
function createCalendarForSelectedUser() {

    var link = "/app-parent-calendar/admin/createCalendarForUser";
    var parameters = {
        userId: $("#selectUser").val(),
        description: $("#calendarDescription").val()
    };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#calendar-list-wrapper").html(data);
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });

    $("#createCalendarModal").modal("hide");
}

function deleteUser(id) {

    if (!confirm("Delete user, are you sure?")) {
        return false;
    }

    var link = "/app-parent-calendar/admin/deleteUser";
    var parameters = { userId: id };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            $("#user-list-wrapper").html(data);
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}
