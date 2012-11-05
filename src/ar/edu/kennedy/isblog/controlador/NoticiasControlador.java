package ar.edu.kennedy.isblog.controlador;

import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.modelo.Comentario;
import ar.edu.kennedy.isblog.seguridad.GaeUser;
import ar.edu.kennedy.isblog.seguridad.GaeUserAuthentication;
import ar.edu.kennedy.isblog.servicio.ArticulosServicio;


@Controller
public class NoticiasControlador {
	
	protected final Logger log = Logger.getLogger(getClass().getName());
	
	@Autowired
    private ArticulosServicio articuloServicio;

	@RequestMapping("/noticias")
	public ModelAndView noticias() {

		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("noticias", articuloServicio.todos(Articulo.NOTICIA));
		
		modelo.addObject("menuSeleccionado", "noticias");
        modelo.setViewName("noticias/listar");

		return modelo;
	}
	
	@RequestMapping(value="/noticias/{id}", method=RequestMethod.GET)
    public ModelAndView ver(@PathVariable String id) {
                         
		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("noticia", articuloServicio.obtenerPorId(Long.valueOf(id)));
		
		modelo.addObject("comentarios", articuloServicio.comentariosPorArticulo(Long.valueOf(id)));
		
		modelo.addObject("comentario", new Comentario());
		
        modelo.addObject("menuSeleccionado", "noticias");
        modelo.setViewName("noticias/ver");
		
        return modelo;
    }
	
	// Crea Comentario
	@PreAuthorize("hasAnyRole('ADMIN,USER')")
	@RequestMapping(value="/noticias/{id}/comentar", method=RequestMethod.POST)
    public ModelAndView comentar(@PathVariable String id, @ModelAttribute Comentario comentario) {
                         
		log.info(String.format("Creando Comentario"));

		Comentario nuevoComentario = new Comentario();
		nuevoComentario.setArticuloId(Long.valueOf(id));
		nuevoComentario.setFecha(Calendar.getInstance().getTime());
		
		nuevoComentario.setTitulo(comentario.getTitulo());
		nuevoComentario.setTexto(comentario.getTexto());
		nuevoComentario.setAprobado(false);
		
		Authentication auth = (GaeUserAuthentication) SecurityContextHolder.getContext().getAuthentication();
	    nuevoComentario.setEmailUsuario(((GaeUser)auth.getPrincipal()).getEmail());
	    nuevoComentario.setNombre(((GaeUser)auth.getPrincipal()).getNombre());
		
		articuloServicio.guardarComentario(nuevoComentario);
		
        return new ModelAndView("redirect:/noticias/"+id);
    }
	
	/*Adminsitracion de Noticias*/
	
	@RequestMapping("/administracion/noticias")
    public ModelAndView listar() {

		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("noticias", articuloServicio.todos(Articulo.NOTICIA));
		
        modelo.addObject("menuSeleccionado", "administracion");
        modelo.setViewName("administracion/noticias/listar");
        
        return modelo;
    }
	
	@RequestMapping(value="/administracion/noticias/nuevo", method=RequestMethod.GET)
    public ModelAndView nuevo() {
           
		log.info("Nuevo articulo");
		
		// creo articulo	
		Articulo nuevoArticulo = new Articulo(Articulo.NOTICIA);
		
		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("articulo", nuevoArticulo);

        modelo.addObject("menuSeleccionado", "administracion");
        
        modelo.setViewName("administracion/noticias/nuevo");
        
        return modelo;
        
    }
	
	@RequestMapping(value="/administracion/noticias/editar/{id}", method=RequestMethod.GET)
    public ModelAndView editar(@PathVariable String id) {
                         
		log.info(String.format("Cargado articulo: %s", id));
		
		Articulo articulo = articuloServicio.obtenerPorId(Long.valueOf(id));

		ModelAndView modelo = new ModelAndView();
	
		modelo.addObject("articulo", articulo);

        modelo.addObject("menuSeleccionado", "administracion");
        
        modelo.setViewName("administracion/noticias/editar");
        
        return modelo;
    }
	
	@RequestMapping(value="/administracion/noticias/crear", method=RequestMethod.POST)
    public ModelAndView crear(@ModelAttribute Articulo articulo) {
                         
		log.info(String.format("Creando Articulo"));

		articulo.setFechaPublicacion(Calendar.getInstance().getTime());
		articulo.setFechaModificacion(Calendar.getInstance().getTime());
		
		articuloServicio.guardar(articulo);
		
        return new ModelAndView("redirect:/administracion/noticias");
    }
	
	@RequestMapping(value="/administracion/noticias/guardar", method=RequestMethod.POST)
    public ModelAndView guardar(@ModelAttribute Articulo articulo) {
                         
		log.info(String.format("Actualizando: %s", articulo.getId()));

		Articulo articuloAModificar = articuloServicio.obtenerPorId(articulo.getId());
		
		articuloAModificar.setTitulo(articulo.getTitulo());
		articuloAModificar.setSubtitulo(articulo.getSubtitulo());
		articuloAModificar.setCuerpo(articulo.getCuerpo());
		articuloAModificar.setFechaModificacion(Calendar.getInstance().getTime());
		
		articuloServicio.guardar(articuloAModificar);
		
        return new ModelAndView("redirect:/administracion/noticias");
    }
	
	
	@RequestMapping(value="/administracion/noticias/eliminar/{id}", method=RequestMethod.GET)
    public ModelAndView eliminar(@PathVariable String id) {
                         
		log.info(String.format("Eliminando: %s", id));
		
		articuloServicio.eliminar(Long.valueOf(id));
		
        return new ModelAndView("redirect:/administracion/noticias");
    }

}
