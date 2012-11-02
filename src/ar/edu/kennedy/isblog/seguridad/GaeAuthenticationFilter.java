package ar.edu.kennedy.isblog.seguridad;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

public class GaeAuthenticationFilter extends GenericFilterBean {
    private static final String URL_REGISTRACION = "/registrar";

    private final Logger logger = Logger.getLogger(getClass().toString());

    private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> ads = new WebAuthenticationDetailsSource();
    private AuthenticationManager authenticationManager;
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User googleUser = UserServiceFactory.getUserService().getCurrentUser();

        if (authentication != null && !loggedInUserMatchesGaeUser(authentication, googleUser)) {
            SecurityContextHolder.clearContext();
            authentication = null;
            ((HttpServletRequest)request).getSession().invalidate();
        }

        if (authentication == null) {
            if (googleUser != null) {
                logger.info("Actualmente loguado en GAE como " + googleUser);
                logger.info("Autenticando en Spring Security");
                // Usuario retornado despues de autenticar con GAE. Necesario para validar con Spring Security.
                PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(googleUser, null);
                token.setDetails(ads.buildDetails((HttpServletRequest) request));

                try {
                    authentication = authenticationManager.authenticate(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    if (authentication.getAuthorities().contains(AppRole.NUEVO_USER)) {
                        logger.info("Nuevo usuario autenticado. Redirigiendo a la pagina de registracion.");
                        ((HttpServletResponse) response).sendRedirect(URL_REGISTRACION);

                        return;
                    }

                } catch (AuthenticationException e) {
                    failureHandler.onAuthenticationFailure((HttpServletRequest)request, (HttpServletResponse)response, e);

                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    private boolean loggedInUserMatchesGaeUser(Authentication authentication, User googleUser) {
        assert authentication != null;

        if (googleUser == null) {
            // Si el usuario se deslogueo de GAE pero no de la aplicacion
            return false;
        }

        GaeUser gaeUser = (GaeUser)authentication.getPrincipal();

        if (!gaeUser.getEmail().equals(googleUser.getEmail())) {
            return false;
        }

        return true;

    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(authenticationManager, "AuthenticationManager debe setearse");
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }
}