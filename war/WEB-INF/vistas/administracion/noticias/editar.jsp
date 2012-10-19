<%@page import="com.ckeditor.CKEditorConfig"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://ckeditor.com" prefix="ckeditor" %>

<div class="hero-unit">
	<h1>Noticia</h1>
</div>

<form:form modelAttribute="articulo" action="/administracion/noticias/guardar" method="POST"
	id="formularioArticulo">
	
	<form:hidden path="id"/>
	
	<form:label path="titulo">Titulo</form:label>
	<form:input path="titulo" cssClass="input-xxlarge" />
	
	<form:label path="subtitulo">Subtitulo</form:label>
	<form:textarea path="subtitulo" cssClass="input-xxlarge" rows="4" />
	
	<form:label path="cuerpo">Cuerpo</form:label>
	<form:textarea path="cuerpo" rows="5" cols="100"/>
	
	<div class="form-actions">
		<input type="submit" value="Guardar" class="btn btn-primary"> 
		<a href="/administracion/noticias" class="btn">Cancelar</a>
	</div>
	
</form:form>

<% 
	CKEditorConfig settings = new CKEditorConfig();
	//settings.addConfigValue("width","500");
%>

<ckeditor:replace replace="cuerpo" basePath="/recursos/ckeditor/" config="<%=settings %>" />