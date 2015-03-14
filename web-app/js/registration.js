
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

function checkPassword() {

    $("#password").val($.trim($("#password").val()));

    if ($.isEmptyObject($("#password").val())) {
        $("#checkPasswordDiv").attr("class", "form-group");
        $("#checkPasswordErrorMessage").html("");
        return false;
    }

    if ($("#password").val().length < 6) {
        $("#checkPasswordDiv").attr("class", "form-group has-error has-feedback");
        $("#checkPasswordErrorMessage").html("Password must be at least 6 characters");
        return false;
    }

    $("#checkPasswordDiv").attr("class", "form-group has-success has-feedback");
    $("#checkPasswordErrorMessage").html("");

    return true;
}

function checkConfirmationPassword() {

    $("#confirmPassword").val($.trim($("#confirmPassword").val()));

    if ($.isEmptyObject($("#password").val())) {
        $("#checkConfirmPasswordDiv").attr("class", "form-group");
        $("#checkPasswordConfirmationErrorMessage").html("");
        return false;
    }

    if ($("#confirmPassword").val() != $("#password").val()) {
        $("#checkConfirmPasswordDiv").attr("class", "form-group has-error has-feedback");
        $("#checkPasswordConfirmationErrorMessage").html("Passwords do not match");
        return false;
    }

    $("#checkConfirmPasswordDiv").attr("class", "form-group has-success has-feedback");
    $("#checkPasswordConfirmationErrorMessage").html("");

    return true;
}

function validateAllInputs() {

    return true;

    if (!validateUsername()) {
        $("#username").focus();
        alert("Invalid username");
        return false;
    }

    if (!validateUserEmail()) {
        $("#email").focus();
        alert("Invalid email address");
        return false;
    }

    if (!checkPassword()) {
        $("#password").focus();
        alert("Invalid password");
        return false;
    }

    if (!checkConfirmationPassword()) {
        $("#confirmPassword").focus();
        alert("Passwords do not match");
        return false;
    }

    return true;

}

function validateUsername() {

    $("#username").val($.trim($("#username").val()));

    if ($.isEmptyObject($("#username").val())) {
        $("#checkUsernameDiv").attr("class", "form-group");
        $("#checkUsernameErrorMessage").html("");
        return false;
    }

    if (($("#username").val().length < 6) || ($("#username").val().length > 20)) {
        $("#checkUsernameDiv").attr("class", "form-group has-error has-feedback");
        $("#checkUsernameErrorMessage").html("Username must be between 6 and 20 characters");
        return false;
    }

    return true;
}
function validateUserEmail() {

    $("#email").val($.trim($("#email").val()));

    if ($.isEmptyObject($("#email").val())) {
        $("#checkEmailDiv").attr("class", "form-group");
        $("#checkEmailErrorMessage").html("");
        return false;
    }

    if (!validateEmail($("#email").val())) {
        $("#checkEmailDiv").attr("class", "form-group has-error has-feedback");
        $("#checkEmailErrorMessage").html("Invalid email format.");
        return false;
    }

    return true;
}

