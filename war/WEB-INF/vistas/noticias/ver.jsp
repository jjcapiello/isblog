<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<h1><c:out value="${noticia.titulo}" /></h1>
<c:out value="${noticia.fechaPublicacion}" />
<p class="lead">
<c:out value="${noticia.subtitulo}" />
</p>

${noticia.cuerpo}

<h2>Comentarios</h2>

<c:if test="${not empty comentarios}">
	<div>
	<c:forEach var="mensaje" items="${comentarios}">
		<div>
			<c:out value="${mensaje.fecha}" />
			<c:out value="${mensaje.titulo}" />
			<c:out value="${mensaje.texto}" />
		</div>
	</c:forEach>
	</div>
</c:if>


<form:form modelAttribute="comentario" action="/noticias/${noticia.id}/comentar" method="POST"
	id="formularioComentario">
		
	<form:label path="titulo">Titulo</form:label>
	<form:input path="titulo" cssClass="input-large" />
	
	<form:label path="texto">Mensaje</form:label>
	<form:textarea path="texto" row="4"/>
	
	<div class="form-actions">
		<input type="submit" value="Enviar" class="btn btn-primary"> 
	</div>
	
</form:form>

