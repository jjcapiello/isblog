<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
	
<h1><c:out value="${noticia.titulo}" /></h1>
<c:out value="${noticia.fechaPublicacion}" />
<p class="lead">
<c:out value="${noticia.subtitulo}" />
</p>

${noticia.cuerpo}

<h2>Comentarios</h2>

<c:if test="${not empty comentarios}">
	<div class="row">
	<c:forEach var="mensaje" items="${comentarios}">
		<div>
			<h4><c:out value="${mensaje.nombre}" /></h4> el <c:out value="${mensaje.fecha}" />
			<h5><c:out value="${mensaje.titulo}" /></h5>
			<p><c:out value="${mensaje.texto}" /></p>
		</div>
	</c:forEach>
	</div>
</c:if>

<sec:authorize access="isAuthenticated()">
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
</sec:authorize>

<sec:authorize access="isAnonymous()">
<p>
Para comentar debe estar registrado.
</p>
</sec:authorize>
