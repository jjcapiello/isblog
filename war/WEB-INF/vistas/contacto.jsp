<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="page-header">
  <h1>Comentario</h1>
</div>

<form:form modelAttribute="formularioContacto" action="/contacto" method="POST"
	id="formularioArticulo">
	
	
	<form:label path="nombre">Titulo</form:label>
	<form:input path="nombre" cssClass="input-xxlarge" />
	
	<form:label path="mail">Subtitulo</form:label>
	<form:textarea path="mail" cssClass="input-xxlarge" rows="4" />
	
	<form:label path="asunto">Cuerpo</form:label>
	<form:textarea path="asunto"/>
	
	<form:label path="texto">Cuerpo</form:label>
	<form:textarea path="texto"/>
	
	<div class="form-actions">
		<input type="submit" value="Enviar" class="btn btn-primary"> 
	</div>
	
</form:form>