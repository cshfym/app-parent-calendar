<!DOCTYPE html>
<html lang="en">

<head>

  <meta name="layout" content="main">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Parent Calendar Admin</title>

  <g:javascript src="admin.js" />

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

    <div id="wrapper">
        <g:form controller="admin">

            <div class="well">
                <a id="btnCreateUser" href="#" class="btn btn-success" onclick="createUser();">Create User</a>
                <a id="btnCreateCalendar" href="#" class="btn btn-success" data-toggle="modal" data-target="#createCalendarModal">Create Calendar</a>
            </div>

            <hr />
            <g:render template="adminUserList" />
            <hr />
            <g:render template="adminCalendarList" />
            <hr />
            <g:render template="adminCreateCalendarDialog" />
       </g:form>
    </div>
    <!-- /#wrapper -->

</body>

</html>
