<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Investigacion de Sistemas</title>

<link rel="stylesheet" type="text/css"
	href="/recursos/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/recursos/bootstrap/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css" href="/recursos/estilos.css" />

<script type="text/javascript"
	src="/recursos/jquery/jquery-1.8.1.min.js"></script>
<script type="text/javascript"
	src="/recursos/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<c:import url="/WEB-INF/vistas/tags/menu_superior.jsp" />
			</div>
		</div>
	</div>

	<div class="container">
		<div class="contenedor">
			<decorator:body />
		</div>
		<footer>
			<p>&copy; Investigacion de Sistemas 2012, Universidad Argentina
				John F. Kennedy</p>
		</footer>
	</div>

</body>
</html>