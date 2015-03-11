
function checkUsername() {
    var link = "/app-parent-calendar/registration/checkUsername";
    var parameters = { username: $("#username").val() };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            //$("#user-list-wrapper").html(data);
            $("#checkUsernameResult").html(data);
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

/*
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
*/
