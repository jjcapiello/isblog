package ar.edu.kennedy.isblog.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.search.query.QueryParser.primitive_return;

import ar.edu.kennedy.isblog.modelo.Comentario;
import ar.edu.kennedy.isblog.servicio.ComentarioServicio;

@Controller
public class ComentarioControlador {

	@Autowired
    private ComentarioServicio comentarioServicio;
	
	/*Adminsitracion de Comentarios*/
	
	@RequestMapping("/administracion/comentarios")
    public ModelAndView listar() {

		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("comentarios", comentarioServicio.todos());
		
        modelo.addObject("menuSeleccionado", "administracion");
        modelo.setViewName("administracion/comentarios/listar");
        
        return modelo;
    }
	
	
	@RequestMapping(value="/administracion/comentarios/{id}", method=RequestMethod.GET)
    public ModelAndView ver(@PathVariable String id) {
                         
		ModelAndView modelo = new ModelAndView();
		
		modelo.addObject("comentario", comentarioServicio.obtenerPorId(Long.valueOf(id)));
		
        modelo.addObject("menuSeleccionado", "administracion");
        modelo.setViewName("administracion/comentarios/ver");
		
        return modelo;
    }
	
	@RequestMapping(value="/administracion/comentarios/{id}/aprobar", method=RequestMethod.POST)
    public ModelAndView aprobar(@PathVariable String id) {
                         	
		comentarioServicio.aprobar(Long.valueOf(id));
		
        return new ModelAndView("redirect:/administracion/comentarios");
    }
	
	
	@RequestMapping(value="/administracion/comentarios/{id}/eliminar", method=RequestMethod.POST)
    public ModelAndView eliminar(@PathVariable String id) {
                         	
		comentarioServicio.eliminar(Long.valueOf(id));
		
        return new ModelAndView("redirect:/administracion/comentarios");
    }
	
}
