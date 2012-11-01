<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Bienvenido al sitio de investigacion de sistemas, <sec:authentication property="principal.nickname" />.
Por favor ingrese sus datos de registracion para poder comentar y debatir articulos en el sitio.
</p>

<form:form id="register" method="post" modelAttribute="formularioRegistracion">
  	<fieldset>
  		<form:label path="nombre">
  		Nombre y Apellido:
 		</form:label> <form:errors path="nombre" cssClass="fieldError" /><br />
  		<form:input path="nombre" cssClass="input-xxlarge" /> <br />

	</fieldset>
	<input type="submit" value="Registrar">
</form:form>