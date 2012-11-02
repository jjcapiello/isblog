<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="hero-unit">

	<form:form modelAttribute="formularioContacto" action="/contacto" method="POST" id="formularioArticulo">
		
			<h1>Formulario de Contacto</h1>
	
	<form:label path="nombre">Nombre</form:label>
	<form:input path="nombre" cssClass="input-xxlarge" />
	
	<form:label path="mail">Mail</form:label>
	<form:textarea path="mail" cssClass="input-xxlarge"  />
	
	<form:label path="asunto">Asunto</form:label>
	<form:textarea path="asunto" cssClass="input-xxlarge"/>
	
	
	<form:label path="asunto">Texto</form:label>
	<form:textarea path="texto" cssClass="input-xxlarge" rows="20"/>
	
	<br><br>
		<input type="submit" value="Enviar" class="btn btn-primary"> 
		<input type="Reset" value="Borrar formulario">
	
</form:form>
</div>

