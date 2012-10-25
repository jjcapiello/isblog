package ar.edu.kennedy.isblog.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.kennedy.isblog.modelo.FormularioContacto;
import ar.edu.kennedy.isblog.servicio.ArticulosServicio;

@Controller
public class AplicacionControlador {

	@Autowired
    private ArticulosServicio articuloServicio;
	
	@RequestMapping("/")
    public String inicio(Model modelo) {
           
            modelo.addAttribute("noticias", articuloServicio.noticiasMasRecientes(4));
            
            modelo.addAttribute("menuSeleccionado", "inicio");
            
                     
            return "inicio";
    }
	
	@RequestMapping(value="/contacto", method=RequestMethod.GET)
    public ModelAndView contacto() {
            
            ModelAndView modelo = new ModelAndView();
    		
            FormularioContacto fc = new FormularioContacto();
            
    		modelo.addObject("formularioContacto", fc);

    		modelo.addObject("menuSeleccionado", "contacto");
            
            modelo.setViewName("contacto");
            
            return modelo;
            
    }
	
	@RequestMapping(value="/contacto", method=RequestMethod.POST)
    public ModelAndView enviarFormularioContacto(@ModelAttribute FormularioContacto formularioContacto) {
            
            
            
            return new ModelAndView("redirect:/");
    }

}
