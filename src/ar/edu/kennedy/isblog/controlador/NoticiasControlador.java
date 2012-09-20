package ar.edu.kennedy.isblog.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticiasControlador {
	
	@RequestMapping("/noticias")
    public String inicio(Model modelo) {
            
            modelo.addAttribute("menuSeleccionado", "noticias");
            
            return "implementar";
    }

}
