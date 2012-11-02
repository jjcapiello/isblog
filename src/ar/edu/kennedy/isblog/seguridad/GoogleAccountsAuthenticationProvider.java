package ar.edu.kennedy.isblog.seguridad;

import com.google.appengine.api.users.User;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class GoogleAccountsAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private UserRegistry userRegistry;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User googleUser = (User) authentication.getPrincipal();

        GaeUser user = userRegistry.findUser(googleUser.getUserId());

        if (user == null) {
            // Usuario no registredo. Debe registrarse
            user = new GaeUser(googleUser.getUserId(), googleUser.getNickname(), googleUser.getEmail());
        }

        if (!user.isHabilitado()) {
            throw new DisabledException("Cuenta deshabilitada");
        }

        return new GaeUserAuthentication(user, authentication.getDetails());
    }

    public final boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserRegistry(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}