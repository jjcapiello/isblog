package ar.edu.kennedy.isblog.controlador;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.kennedy.isblog.modelo.Articulo;

@Controller
public class AplicacionControlador {

	@RequestMapping("/")
    public String inicio(Model modelo) {

            List<Articulo> noticias = dummy();
            
            modelo.addAttribute("ultimaNoticia", noticias.get(0));
            modelo.addAttribute("noticias234", noticias.subList(1, 4));
            
            modelo.addAttribute("menuSeleccionado", "inicio");
            
            return "inicio";
    }
	
	@RequestMapping("/contacto")
    public String contacto(Model modelo) {
            
            modelo.addAttribute("menuSeleccionado", "contacto");
            
            return "implementar";
    }
	
	
	private List<Articulo> dummy(){
		List<Articulo> noticias = new ArrayList<Articulo>();
		
		noticias.add(new Articulo("Titulo 1", "lkamsldkmfas","Algo mas"));
		noticias.add(new Articulo("Titulo 2", "qwwqerpoqewmpwmdpweikrqpw,aspodkapds, oapsd,f pasdofapsd ,pofpasodkapsdf asdofpak poawewq","Algo mas"));
		noticias.add(new Articulo("Titulo 3", "qwwqerpoqewmpwmdpweikrqpw,aspodkapds, oapsd,f pasdofapsd ,pofpasodkapsdf asdofpak poawewq","Algo mas"));
		noticias.add(new Articulo("Titulo 4", "qwwqerpoqewmpwmdpweikrqpw,aspodkapds, oapsd,f pasdofapsd ,pofpasodkapsdf asdofpak poawewq","Algo mas"));
		
		return noticias;
		
	}
}
