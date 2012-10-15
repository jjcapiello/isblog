<div class="hero-unit">
	<h1>Nueva Noticia</h1>
</div>
<form action="/administracion/noticias/guardar" method="POST"
	id="formularioArticulo">
	<input type="hidden" name="id" value="${articulo.id}" /> <input
		type="hidden" name="fechaPublicacion"
		value="$articulo.fechaPublicacion" /> <label for="titulo">Titulo</label>
	<input type="text" name="titulo" id="titulo" value="${articulo.titulo}" />
	<label for="subtitulo">Subtitulo</label> <input type="text"
		name="subtitulo" id="subtitulo" value="${articulo.subtitulo}" /> <label
		for="cuerpo">Cuerpo</label> <input type="text" name="cuerpo"
		id="cuerpo" value="${articulo.cuerpo}" />
	<div class="actions">
		<input type="submit" value="Guardar" class="btn primary"> o <a
			href="/administracion/noticias" class="btn">Cancelar</a>
	</div>
</form>
