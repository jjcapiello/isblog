<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a class="btn btn-navbar" data-toggle="collapse"
	data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="/"> Investigación de Sistemas </a>
<div class="nav-collapse collapse">
	<ul class="nav">


		<li <c:if test='${menuSeleccionado == "inicio"}'>class="active"</c:if>><a
			href="<c:url value="/" />" rel="tooltip" title="Volver al inicio">Inicio</a></li>
		<li
			<c:if test='${menuSeleccionado == "noticias"}'>class="active"</c:if>><a
			href="<c:url value="/noticias" />"  rel="tooltip" title="Todas las noticias ordenadas">Noticias </a></li>
		<li
			<c:if test='${menuSeleccionado == "materia"}'>class="active"</c:if>><a
			href="<c:url value="/materia" />" rel="tooltip" title="Datos relacionados a la materia" >Materia</a></li>
		<li
			<c:if test='${menuSeleccionado == "contacto"}'>class="active"</c:if>><a
			href="<c:url value="/contacto" />" rel="tooltip" title="Formulario de contacto">Contacto</a></li>

	</ul>
	<sec:authorize access="hasRole('ADMIN')">
		<ul class="nav">
			<li
				class="dropdown <c:if test='${menuSeleccionado == "administracion"}'> active</c:if> "><a
				href="#" class="dropdown-toggle" data-toggle="dropdown">
					Administrar <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li><a href="/administracion/noticias">Noticias</a></li>
					<li><a href="#">Materia</a></li>
					<li><a href="/administracion/comentarios">Comentarios</a></li>
				</ul></li>
		</ul>
	</sec:authorize>

	<ul class="nav pull-right" style="float: right;">
		<sec:authorize access="isAuthenticated()">
			<li	class="dropdown"><a	href="#" class="dropdown-toggle" data-toggle="dropdown">
					<sec:authentication property="principal.nickname" /> <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
					<li>Nombre visible para otros usuarios: <sec:authentication property="principal.nombre" /> </li>
					<li><a href="/logout"> Logout</a></li>
				</ul></li>
			
		</sec:authorize>

		<sec:authorize access="isAnonymous()">
			<li><a href="/login">Login</a></li>
		</sec:authorize>
	</ul>
</div>