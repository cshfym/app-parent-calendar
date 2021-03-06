<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap Core CSS -->
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css" />

        <!-- Global CSS -->
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'global.css')}" type="text/css" />

        <g:javascript src="jquery.js" />
        <g:javascript src="bootstrap.min.js" />
        <g:javascript src="application.js" />
        <g:javascript src="validations.js" />

        <g:javascript src="jquery.tooltipster.js" />

        <link rel="stylesheet" href="${resource(dir: 'css', file: "tooltipster.css")}" type="text/css" />

		<g:layoutHead/>

		<r:layoutResources />
	</head>
	<body>
        <g:render template="/common/navigation" />

        <g:render template="/common/errors" />

		<g:layoutBody />

		<r:layoutResources />

	</body>
</html>
