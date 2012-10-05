package ar.edu.kennedy.isblog.controlador;

import java.util.Calendar;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.servicio.ArticulosServicio;


@Controller
public class NoticiasControlador {
	
	protected final Logger log = Logger.getLogger(getClass().getName());
	
	@Autowired
    private ArticulosServicio articuloServicio;
	
	
	@RequestMapping("/noticias/pagina/{pagina}")
	public ModelAndView inicio(@PathVariable String pagina) {
		
		ModelAndView modelo = new ModelAndView();

		PagedListHolder<Articulo> noticias = new PagedListHolder<Articulo>(articuloServicio.todos());
		noticias.setPageSize(10);
		modelo.addObject("noticias", articuloServicio.masRecientes(4));

		modelo.addObject("menuSeleccionado", "noticias");
		modelo.setViewName("noticias/listar");

		return modelo;
	}
	
	
	/*Adminsitracion de Noticias*/
	
	@RequestMapping("/administracion/noticias")
    public ModelAndView listar() {

		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("noticias", articuloServicio.masRecientes(4));
		
        modelo.addObject("menuSeleccionado", "administracion");
        modelo.setViewName("administracion/noticias/listar");
        
        return modelo;
    }
	
	@RequestMapping(value="/administracion/noticias/nuevo", method=RequestMethod.GET)
    public ModelAndView nuevo() {
           
		log.info("Nueva Noticia");
		ModelAndView modelo = new ModelAndView("administracion/noticias/nuevo", "articulo", new Articulo());
		
        modelo.addObject("menuSeleccionado", "administracion");
        
        return modelo;
    }
	
	@RequestMapping(value="/administracion/noticias/guardar", method=RequestMethod.POST)
    public ModelAndView guardar(@ModelAttribute Articulo articulo) {
                         
		log.info(String.format("Guaradando: %s", articulo.getTitulo()));
		Articulo nuevoArticulo = new Articulo(articulo.getTitulo(), articulo.getSubtitulo(), articulo.getCuerpo().toString());
		nuevoArticulo.setFechaPublicacion(Calendar.getInstance().getTime());

		articuloServicio.guardar(nuevoArticulo);
		
        return new ModelAndView("redirect:/administracion/noticias");
    }
	
	@RequestMapping(value="/administracion/noticias/eliminar/{id}", method=RequestMethod.GET)
    public ModelAndView eliminar(@PathVariable String id) {
                         
		log.info(String.format("Eliminando: %s", id));
		
		articuloServicio.eliminar(Long.valueOf(id));
		
        return new ModelAndView("redirect:/administracion/noticias");
    }

}
