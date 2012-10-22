package ar.edu.kennedy.isblog.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/contacto")
    public String contacto(Model modelo) {
            
            modelo.addAttribute("menuSeleccionado", "contacto");
            
            return "implementar";
    }

}
