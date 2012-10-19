<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="page-header">
  <h1>Noticias</h1>
</div>

<c:if test="${empty noticias}">
    <p>No hay Noticias</p>
</c:if>

<c:if test="${not empty noticias}">

    <c:forEach var="noticia" items="${noticias}">
    	
    	<div class="row">
    		<div class="span1">  
    			<img src="http://placehold.it/200x200" class="img-polaroid">
    		</div>
    		<div>  
             <h1><c:out value="${noticia.titulo}" /></h1>
             <c:out value="${noticia.fechaPublicacion}" />
             <p><c:out value="${noticia.subtitulo}" /></p>
             </div>
             <p>
                 <a class="btn btn-primary btn-large" href="/noticias/${noticia.id}">Ver Completo &raquo;</a>
             </p>
              </div>
         
            
    </c:forEach>
    
</c:if>