<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a class="btn btn-navbar" data-toggle="collapse"
	data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="/">Investigacion de Sistemas</a>
<div class="nav-collapse collapse">
	<ul class="nav">


		<li <c:if test='${menuSeleccionado == "inicio"}'>class="active"</c:if>><a href="<c:url value="/" />">Inicio</a></li>
		<li <c:if test='${menuSeleccionado == "noticias"}'>class="active"</c:if>><a href="<c:url value="/noticias" />">Noticias</a></li>
		<li <c:if test='${menuSeleccionado == "materia"}'>class="active"</c:if>><a href="<c:url value="/materia" />">Materia</a></li>
		<li <c:if test='${menuSeleccionado == "contacto"}'>class="active"</c:if>><a href="<c:url value="/contacto" />">Contacto</a></li>

	</ul>
	<ul class="nav">
		<li class="dropdown <c:if test='${menuSeleccionado == "administracion"}'> active</c:if> "><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> Administrar <b class="caret"></b>
		</a>
			<ul class="dropdown-menu">
				<li><a href="/administracion/noticias">Noticias</a></li>
				<li><a href="#">Materia</a></li>
				<li><a href="/administracion/comentarios">Comentarios</a></li>
			</ul></li>
	</ul>
</div>