<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<div class="hero-unit">
    <h1>Listado de Noticias</h1>
</div>
<a class="btn btn-primary btn-large"
    href="/administracion/noticias/nuevo">Nueva Noticia</a>
    <table class="table table-hover">
        <thead>
            <tr>
                <th >Titulo</th>
                <th >Fecha</th>
				<th ></th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="noticia" items="${noticias}">
                <tr>
                    <td>
                        <a href="/administracion/noticias/editar/${noticia.id}"><c:out value="${noticia.titulo}" /></a>
                    </td>
                    <td><c:out value="${noticia.fechaPublicacion}" /></td>
					<td><a href="/administracion/noticias/eliminar/${noticia.id}" class="btn btn-danger">Eliminar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
