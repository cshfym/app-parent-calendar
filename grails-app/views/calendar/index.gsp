<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Parent Calendar</title>

  <g:javascript src="jquery.js" />
  <g:javascript src="bootstrap.min.js" />
  <g:javascript src="application.js" />
  <g:javascript src="calendar.js" />

  <!-- Bootstrap Core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/global.css" rel="stylesheet">
  <link href="css/calendar.css" rel="stylesheet">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Parent Calendar
                    </a>
                </li>
                <li>
                    <a href="#">Dashboard</a>
                </li>
                <li>
                    <a href="#">Shortcuts</a>
                </li>
                <li>
                    <a href="#">Overview</a>
                </li>
                <li>
                    <a href="#">Events</a>
                </li>
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">Services</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">

                      <g:form controller="calendar">
                        <div style="float: left;">
                          <h1>Parent Calendar</h1>
                          <p><span style="font-style: italic;">Time management for parents and co-parents.</span></p>
                        </div>
                        <div style="float:right">
                          <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                        </div>
                        <div style="clear: both;" action="nextCalendarMonth" />

                        <g:render template="calendar" />

                      </g:form>

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

        <div>
          <table style="width: 200px; margin-top: 20px;" border="1">
            <thead><td>ID</td><td>Active</td><td>Email</td></thead>
            <g:each in="${users}" var="user">
              <tr>
                <td>${user.id}</td><td>${user.active}</td><td>${user.email}</td>
              </tr>
            </g:each>
          </table>

        </div>
    </div>
    <!-- /#wrapper -->

</body>

</html>
