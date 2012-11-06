<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
        $(function() {
           

            $("a.confirm").click(function(e) {
                e.preventDefault();
                bootbox.confirm("Are you sure?", function(confirmed) {
                    console.log("Confirmed: "+confirmed);
                });
            });

        });
    </script>
    
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
				<th>Usuario</th>
				<th>Titulo</th>
				<th>Fecha</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="comentario" items="${comentarios}">
				<tr>
					<td><c:out value="${comentario.emailUsuario}" /></td>
					<td><a href="/administracion/comentarios/${comentario.id}"><c:out value="${comentario.titulo}" /></td>
					<td><c:out value="${comentario.fecha}" /></td>
					<td>
					<c:if test="${comentario.aprobado == false}">
						<form action="/administracion/comentarios/${comentario.id}/aprobar" method="POST" >
						    <input type="submit" value="Aprobar" class="btn btn-success">
						</form>
					</c:if>
					</td>
					<td>
					<form action="/administracion/comentarios/${comentario.id}/eliminar" method="POST" >
						<input type="submit" value="Eliminar" class="btn btn-danger">
					</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>