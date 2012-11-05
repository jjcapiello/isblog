<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="page-header" >
  <h1 >Noticias</h1>
</div>

<c:if test="${empty noticias}">
    <p>No hay Noticias</p>
</c:if>

<c:if test="${not empty noticias}">


<div class="row">
<c:forEach var="noticia" items="${noticias}">

	<div class="span12">
			 <div class="thumbnail" >
					<h3 align="center"><c:out value="${noticia.titulo}" /></h3>
		
					<img src="http://placehold.it/140x140" class="img-polaroid">
								
						<p  class=><c:out value="${noticia.subtitulo}" /></p>
							<p align="center" >
								<a class="btn btn-primary btn-large" href="/noticias/${noticia.id}">Leer &raquo;</a>
						</p> 
						 <h5 align="right"> <c:out value="${noticia.fechaPublicacion}" />   </h5><hr>
						
			</div>
		
	</div></c:forEach>
</div>


    
</c:if>