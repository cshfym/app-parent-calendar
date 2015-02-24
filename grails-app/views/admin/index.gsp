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

        <div>
          <table style="width: 100%; margin: 20px; font-size: 10px;" border="1">
            <thead><td>Users</td></thead>
            <g:each in="${users}" var="user">
              <tr>
                <td>${user}</td>
              </tr>
            </g:each>
          </table>
          <table style="width: 100%; margin: 20px; font-size: 10px;" border="1">
            <thead><td>Calendars</td></thead>
            <g:each in="${calendars}" var="calendar">
                <tr>
                    <td>${calendar}</td>
                </tr>
            </g:each>
          </table>
        </div>
    </div>
    <!-- /#wrapper -->

</body>

</html>
