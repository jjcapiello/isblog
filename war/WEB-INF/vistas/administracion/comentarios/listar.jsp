<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-header">
  <h1>Listado de Comentarios</h1>
</div>

<c:if test="${empty comentarios}">
	<p>No hay Comentarios</p>
</c:if>

<c:if test="${not empty comentarios}">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Titulo</th>
				<th>Fecha</th>
				<th>Aprobado</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="comentario" items="${comentarios}">
				<tr>
					<td><a href="/administracion/comentarios/${comentario.id}"><c:out value="${comentario.titulo}" /></td>
					<td><c:out value="${comentario.fecha}" /></td>
					<td><c:out value="${comentario.aprobado}" /></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>