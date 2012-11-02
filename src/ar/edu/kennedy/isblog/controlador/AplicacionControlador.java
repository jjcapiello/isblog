package ar.edu.kennedy.isblog.controlador;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserServiceFactory;

import ar.edu.kennedy.isblog.modelo.FormularioContacto;
import ar.edu.kennedy.isblog.modelo.FormularioRegistracion;
import ar.edu.kennedy.isblog.seguridad.AppRole;
import ar.edu.kennedy.isblog.seguridad.GaeUser;
import ar.edu.kennedy.isblog.seguridad.GaeUserAuthentication;
import ar.edu.kennedy.isblog.seguridad.UserRegistry;
import ar.edu.kennedy.isblog.servicio.ArticulosServicio;

@Controller
public class AplicacionControlador {

	@Autowired
    private ArticulosServicio articuloServicio;
	
	@Autowired
    private UserRegistry registry;
	
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
            
		Properties props = new Properties();        
		  Session session = Session.getDefaultInstance(props, null);        
		  String msgBody = "...";        
		  try {            
		   Message msg = new MimeMessage(session);            
		   msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));            
		   msg.addRecipient(Message.RecipientType.TO,                             
		     new InternetAddress("gjlopeztrotta@gmail.com", "Mr. User"));            
		   msg.setSubject("Your Example.com account has been activated");            
		   msg.setText(msgBody);            
		   Transport.send(msg);            
		   } catch (AddressException e) {            
		    // ...        
		    } catch (MessagingException e) {            
		     // ...        
		     } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return new ModelAndView("redirect:/");
    }

	@RequestMapping(value = "/login", method= RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        String loginUrl = UserServiceFactory.getUserService().createLoginURL("/");

        response.sendRedirect(loginUrl);
    }
	
	@RequestMapping(value = "/logout", method= RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        String logoutUrl = UserServiceFactory.getUserService().createLogoutURL("/");

        response.sendRedirect(logoutUrl);
    }
	
	@RequestMapping(value="/registrar", method= RequestMethod.GET)
    public FormularioRegistracion registrationForm() {
        return new FormularioRegistracion();
    }

    @RequestMapping(value="/registrar", method = RequestMethod.POST)
    public String register(FormularioRegistracion form, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GaeUser currentUser = (GaeUser)authentication.getPrincipal();
        Set<AppRole> roles = EnumSet.of(AppRole.USER);

        if (UserServiceFactory.getUserService().isUserAdmin()) {
            roles.add(AppRole.ADMIN);
        }

        GaeUser user = new GaeUser(currentUser.getUserId(), currentUser.getNickname(), currentUser.getEmail(),
                form.getNombre(), roles, true);

        registry.registerUser(user);

        // Update the context with the full authentication
        SecurityContextHolder.getContext().setAuthentication(new GaeUserAuthentication(user, authentication.getDetails()));

        return "redirect:/";
    }
}
