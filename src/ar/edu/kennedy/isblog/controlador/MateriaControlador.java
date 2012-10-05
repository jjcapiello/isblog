package ar.edu.kennedy.isblog.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MateriaControlador {

	@RequestMapping("/materia")
    public ModelAndView inicio() {
            
ModelAndView modelo = new ModelAndView();
		
        modelo.addObject("menuSeleccionado", "materia");
        modelo.setViewName("materia/listar");
        
        return modelo;
    }
	
}
