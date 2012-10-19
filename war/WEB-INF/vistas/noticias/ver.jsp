<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<h1><c:out value="${noticia.titulo}" /></h1>
<c:out value="${noticia.fechaPublicacion}" />
<p class="lead">
<c:out value="${noticia.subtitulo}" />
</p>
<p>
<c:out value="${noticia.cuerpo}" />
</p>