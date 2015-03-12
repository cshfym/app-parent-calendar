
function checkUsername() {

    if (!validateUsername()) {
        return
    }

    var link = "/app-parent-calendar/registration/checkUsername";
    var parameters = { username: $("#username").val() };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            if ($.isEmptyObject(data)) {
                $("#checkUsernameDiv").attr("class", "form-group has-success has-feedback");
                $("#checkUsernameErrorMessage").html("");
            } else {
                $("#checkUsernameDiv").attr("class", "form-group has-error has-feedback");
                $("#checkUsernameErrorMessage").html(data);
            }
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function checkEmail() {

    if (!validateUserEmail()) {
        return
    }

    var link = "/app-parent-calendar/registration/checkEmail";
    var parameters = { email: $("#email").val() };
    $.ajax({
        type: "POST",
        url: link,
        dataType: 'html',
        data: parameters,
        success: function(data) {
            if ($.isEmptyObject(data)) {
                $("#checkEmailDiv").attr("class", "form-group has-success has-feedback");
                $("#checkEmailErrorMessage").html("");
            } else {
                $("#checkEmailDiv").attr("class", "form-group has-error has-feedback");
                $("#checkEmailErrorMessage").html(data);
            }
        },
        error: function(request, status, error) {
            alert(error);
        },
        complete: function() { }
    });
}

function validateAllInputs() {

    if (!validateUsername()) {
        $("#username").focus();
        alert("Invalid username.");
        return false;
    }

    if (!validateUserEmail()) {
        $("#email").focus();
        alert("Invalid email address.");
        return false;
    }

    return true;
}

function validateUsername() {

    $("#username").val($.trim($("#username").val()));

    $("#checkUsernameDiv").attr("class", "form-group");
    $("#checkUsernameErrorMessage").html("");

    if ($.isEmptyObject($("#username").val())) {
        $("#checkUsernameDiv").attr("class", "form-group has-error has-feedback");
        $("#checkUsernameErrorMessage").html("Please enter a valid username.");
        return false;
    }

    if (($("#username").val().length < 6) || ($("#username").val().length > 20)) {
        $("#checkUsernameDiv").attr("class", "form-group has-error has-feedback");
        $("#checkUsernameErrorMessage").html("Username must be between 6 and 20 characters.");
        return false;
    }

    return true;
}
function validateUserEmail() {

    $("#email").val($.trim($("#email").val()));

    $("#checkEmailDiv").attr("class", "form-group");
    $("#checkEmailErrorMessage").html("");

    if ($.isEmptyObject($("#email").val())) {
        $("#checkEmailDiv").attr("class", "form-group has-error has-feedback");
        $("#checkEmailErrorMessage").html("Please enter a valid email address.");
        return false;
    }

    if (!validateEmail($("#email").val())) {
        $("#checkEmailDiv").attr("class", "form-group has-error has-feedback");
        $("#checkEmailErrorMessage").html("Invalid email format.");
        return false;
    }

    return true;
}
