package ar.edu.kennedy.isblog;

import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
	private final Logger log = Logger.getLogger(getClass().getName());
	
	@Bean
    public PersistenceManagerFactory pmFactory() {
        log.info("Getting PersistenceManagerFactory");
        return JDOHelper.getPersistenceManagerFactory("transactions-optional");
    }
}
