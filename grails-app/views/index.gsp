<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Parent Calendar</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

    <link href="css/global.css" rel="stylesheet">

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
                        <h1>Parent Calendar</h1>
                        <p>Parent calendar management.</p>
                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>

                        <!-- Calendar Area -->
                        <table style="margin: 25px 0px 0px 0px; border: 1px solid #aaa;">
                            <tr>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                            </tr>
                            <tr>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                            </tr>
                            <tr>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                            </tr>
                            <tr>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                            </tr>
                            <tr>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                            </tr>
                            <tr>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                                <td class="td-day-container"><div class="calendar-day-container"></div></td>
                            </tr>
                        </table>



                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>
