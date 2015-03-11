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
            <g:if test='${flash.message}'>
                <div class="alert alert-dismissible alert-danger">${flash.message}</div>
            </g:if>

            <g:form class="form-horizontal" controller="registration" action="submitRegistration">
                <fieldset>
                    <div class="form-group">
                        <label for="username" class="col-lg-2 control-label">Username</label>
                        <div class="col-lg-4">
                            <input type="text" class="form-control" name="j_username" id="username"
                                onblur="return checkUsername();"
                            />
                        </div>
                        <div id="checkUsernameResult">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-lg-2 control-label">Email</label>
                        <div class="col-lg-4">
                            <input type="text" class="form-control" name="email" id="email" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-lg-2 control-label">Password</label>
                        <div class="col-lg-4">
                            <input type="password" class="form-control" name="j_password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword" class="col-lg-2 control-label">Confirm Password</label>
                        <div class="col-lg-4">
                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary">Submit</button>
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
