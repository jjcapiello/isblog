package ar.edu.kennedy.isblog.seguridad;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import ar.edu.kennedy.isblog.modelo.Usuario;
import ar.edu.kennedy.isblog.persistencia.IUsuarioDao;

import com.google.appengine.api.users.User;

public class GoogleAccountsAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private IUsuarioDao userRegistry;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		User googleUser = (User) authentication.getPrincipal();
		
		Usuario user = userRegistry.findUser(googleUser.getUserId());

        if (user == null) {
            // El usuario debe registrarse
            //user = new GaeUser(googleUser.getUserId(), googleUser.getNickname(), googleUser.getEmail());
        }

        if (!user.isEnabled()) {
            throw new DisabledException("Cuenta Inhabilitada");
        }
        
        return new GaeUserAuthentication(user, authentication.getDetails());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}
	
}
