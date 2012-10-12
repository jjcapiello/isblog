<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>

<c:if test="${empty noticias}">
	<p>No hay Noticias</p>
</c:if>

<c:if test="${not empty noticias}">
	<c:set var="contador" value="0" />

	<c:forEach var="noticia" items="${noticias}">
		<c:set var="contador" value="${contador + 1 }" />
		<c:choose>
			<c:when test="${contador == 1}">
				<div class="hero-unit">
					<h1>
						<c:out value="${noticia.titulo}" />
					</h1>
					<c:out value="${noticia.fechaPublicacion}" />
					<p>
						<c:out value="${noticia.subtitulo}" />
					</p>
					<p>
						<a class="btn btn-primary btn-large" href="/noticias/${noticia.id}">Ver Completo
							&raquo;</a>
					</p>
				</div>

				<c:if test="${fn:length(noticias) > 1}">
					<div class="row">
				</c:if>
			</c:when>
			<c:otherwise>
				<div class="span4">
					<h2>
						<c:out value="${noticia.titulo}" />
					</h2>
					<c:out value="${noticia.fechaPublicacion}" />
					<p>
						<c:out value="${noticia.subtitulo}" />
					</p>
					<p>
						<a class="btn" href="/noticias/${noticia.id}">Ver Completo &raquo;</a>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${fn:length(noticias) > 1}">
		</div>
	</c:if>

</c:if>
