<!DOCTYPE html>
<html lang="en">

<head>

  <meta name="layout" content="main">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Parent Calendar</title>

  <g:javascript src="calendar.js" />
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'calendar.css')}" type="text/css">


  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <g:if test="${pageModel.uiCalendar.weekView}">
                            <g:render template="weekView" />
                        </g:if>
                        <g:else>
                            <g:render template="monthView" />
                        </g:else>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

        <g:render template="createCalendarEventDialog" />

    </div>
    <!-- /#wrapper -->

</body>

</html>
