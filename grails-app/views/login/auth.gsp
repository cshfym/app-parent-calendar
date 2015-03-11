<html>
<head>
	<meta name="layout" content="main"/>
	<title><g:message code="springSecurity.login.title"/></title>

</head>

<body>
<div class="panel-primary" id="login">

    <div class="panel panel-primary panel-login">

        <div class="panel-heading">
            <h3 class="panel-title">Parent Calendar Login</h3>
        </div>

        <div class="panel-body">
            <g:if test='${flash.message}'>
                <div class="alert alert-dismissible alert-danger">${flash.message}</div>
            </g:if>

            <form class="form-horizontal" action="${postUrl}" method="POST" id="loginForm" autocomplete="off">
                <fieldset>
                    <div class="form-group">
                        <label for="username" class="col-lg-2 control-label">Username</label>
                        <div class="col-lg-4">
                            <input type="text" class="form-control" name="j_username" id="username" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-lg-2 control-label">Password</label>
                        <div class="col-lg-4">
                            <input type="password" class="form-control" name="j_password" id="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 25px;">
                        <div class="col-lg-10 col-lg-offset-2" style="border: 1px solid #80808;">
                            <g:link class="btn btn-link" controller="registration" action="index">Register</g:link>
                        </div>
                    </div>
                </fieldset>

            </form>
        </div>
    </div>





</div>
<g:javascript src="auth.js" />
</body>
</html>
