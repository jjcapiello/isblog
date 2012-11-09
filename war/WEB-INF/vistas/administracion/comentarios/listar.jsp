<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
    
	function eliminarModal(form) {
		return confirm('¿Seguro desea eliminar el comentario?');
	};

	$(document).ready(function() {
		$(".tablafila").click(function() {
			$(this).next(".tablasubfila").slideDown("slow");
		});

		$(".tablasubfila").click(function() {
			$(".tablasubfila").hide();
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
	<table class="table table-striped table-hover">
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
				<tr class="tablafila">
					<td><c:out value="${comentario.nombre}" /></td>
					<td><c:out value="${comentario.titulo}" /></td>
					<td><c:out value="${comentario.fecha}" /></td>
					<td><c:if test="${comentario.aprobado == false}">
							<form style="margin: 0 0 0px;"
								action="/administracion/comentarios/${comentario.id}/aprobar"
								method="POST">
								<input type="submit" value="Aprobar" class="btn btn-success">
							</form>
						</c:if></td>
					<td>
						<form style="margin: 0 0 0px;"
							onSubmit="return eliminarModal(this);"
							action="/administracion/comentarios/${comentario.id}/eliminar"
							method="POST">
							<input type="submit" value="Eliminar" class="btn btn-danger">
						</form>
					</td>
				</tr>
				<tr class="tablasubfila" style="display: none;">
					<td colspan="1">Email: <c:out value="${comentario.emailUsuario}" />
					</td>
					<td colspan="2"><c:out value="${comentario.texto}" /></td>
					<td colspan="2"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</c:if>



