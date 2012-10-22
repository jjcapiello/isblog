<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<h1><c:out value="${comentario.titulo}" /></h1>
<c:out value="${comentario.fecha}" />
<p class="lead">
<c:out value="${comentario.texto}" />
</p>

<form action="/administracion/comentarios/${comentario.id}/aprobar" method="POST" >
    <input type="submit" value="Aprobar">
</form>

<form action="/administracion/comentarios/${comentario.id}" method="DELETE" >
	<input type="submit" value="Eliminar">
</form>

