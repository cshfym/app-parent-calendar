<!DOCTYPE html>
<html lang="en">

<head>

  <meta name="layout" content="main">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Parent Calendar :: Admin</title>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

    <div id="wrapper">
      <table class="table table-striped table-hover">
        <thead><td>Users</td></thead>
        <g:each in="${users}" var="user">
          <tr>
            <td>${user}</td>
          </tr>
        </g:each>
      </table>
      <table class="table table-striped table-hover">
        <thead><td>Calendars</td></thead>
        <g:each in="${calendars}" var="calendar">
            <tr>
                <td>${calendar}</td>
            </tr>
        </g:each>
      </table>
    </div>
    <!-- /#wrapper -->

</body>

</html>
