<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="layout" content="main"/>
    <title>Parent Calendar - User Registration</title>
    <g:javascript src="registration.js" />
</head>

<body>
<div class="panel-primary" id="login">

    <div class="panel panel-primary panel-login">

        <div class="panel-heading">
            <h3 class="panel-title">Parent Calendar - User Registration</h3>
        </div>

        <div class="panel-body">
            <g:if test="${flash.message}">
                <div class="alert alert-dismissible alert-danger validation-error-inverse">
                    ${flash.message}
                    <br/><br/>
                    <ul>
                        <g:each in="${flash.validationErrors}" var="error">
                            <li>${error}</li>
                        </g:each>
                    </ul>
                </div>
            </g:if>

            <g:form method="POST" class="form-horizontal" controller="registration" action="submitRegistration">
                <fieldset>
                    <div id="checkUsernameDiv" class="form-group">
                        <label for="username" class="col-lg-2 control-label">Username</label>
                        <div class="col-lg-4">
                            <input type="text" class="form-control" name="username" id="username" value="${username}"
                                   onblur="return checkUsername();"/>
                        </div>
                        <div id="checkUsernameErrorMessage" class="col-lg-5 validation-error"></div>
                    </div>
                    <div id="checkEmailDiv" class="form-group">
                        <label for="email" class="col-lg-2 control-label">Email</label>
                        <div class="col-lg-4">
                            <input type="text" class="form-control" name="email" id="email" value="${email}"
                                   onblur="return checkEmail();" />
                        </div>
                        <div id="checkEmailErrorMessage" class="col-lg-5 validation-error"></div>
                    </div>
                    <div id="checkPasswordDiv" class="form-group">
                        <label for="password" class="col-lg-2 control-label">Password</label>
                        <div class="col-lg-4">
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Password" onblur="return checkPassword();">
                        </div>
                        <div id="checkPasswordErrorMessage" class="col-lg-5 validation-error"></div>
                    </div>
                    <div id="checkConfirmPasswordDiv"  class="form-group">
                        <label for="confirmPassword" class="col-lg-2 control-label">Confirm Password</label>
                        <div class="col-lg-4">
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                                   placeholder="" onblur="return checkConfirmationPassword();">
                        </div>
                        <div id="checkPasswordConfirmationErrorMessage" class="col-lg-5 validation-error"></div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button id="btnSubmitRegistration" type="submit" class="btn btn-primary" onclick="return validateAllInputs();">Submit</button>
                            <g:link class="btn btn-link" controller="login" action="auth">Login</g:link>
                        </div>
                    </div>

                </fieldset>
            </g:form>

        </div>
    </div>





</div>
<g:javascript src="auth.js" />
</body>
</html>
