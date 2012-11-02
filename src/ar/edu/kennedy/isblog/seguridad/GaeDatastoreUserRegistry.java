package ar.edu.kennedy.isblog.seguridad;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.*;
import java.util.logging.Logger;

public class GaeDatastoreUserRegistry implements UserRegistry {
	private final Logger logger = Logger.getLogger(getClass().toString());

    private static final String USUARIO_TIPO = "GaeUser";
    private static final String USUARIO_NOMBRE = "nombre";
    private static final String USUARIO_NICKNAME = "nickname";
    private static final String USUARIO_EMAIL = "email";
    private static final String USUARIO_ENABLED = "enabled";
    private static final String USUARIO_AUTHORITIES = "authorities";

    public GaeUser findUser(String userId) {
        Key key = KeyFactory.createKey(USUARIO_TIPO, userId);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        try {
            Entity user = datastore.get(key);

            long binaryAuthorities = (Long)user.getProperty(USUARIO_AUTHORITIES);
            Set<AppRole> roles = EnumSet.noneOf(AppRole.class);

            for (AppRole r : AppRole.values()) {
                if ((binaryAuthorities & (1 << r.getBit())) != 0) {
                    roles.add(r);
                }
            }

            GaeUser gaeUser = new GaeUser(
                    user.getKey().getName(),
                    (String)user.getProperty(USUARIO_NICKNAME),
                    (String)user.getProperty(USUARIO_EMAIL),
                    (String)user.getProperty(USUARIO_NOMBRE),
                    roles,
                    (Boolean)user.getProperty(USUARIO_ENABLED));

            return gaeUser;

        } catch (EntityNotFoundException e) {
            logger.info(userId + " no se encuentra en la base de datos");
            return null;
        }
    }

    public void registerUser(GaeUser newUser) {
        logger.info("Creando usuario " + newUser);

        Key key = KeyFactory.createKey(USUARIO_TIPO, newUser.getUserId());
        Entity user = new Entity(key);
        user.setProperty(USUARIO_EMAIL, newUser.getEmail());
        user.setProperty(USUARIO_NICKNAME, newUser.getNickname());
        user.setProperty(USUARIO_NOMBRE, newUser.getNombre());
        user.setUnindexedProperty(USUARIO_ENABLED, newUser.isHabilitado());

        Collection<AppRole> roles = newUser.getAuthorities();

        long binaryAuthorities = 0;

        for (AppRole r : roles) {
            binaryAuthorities |= 1 << r.getBit();
        }

        user.setUnindexedProperty(USUARIO_AUTHORITIES, binaryAuthorities);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(user);
    }

    public void removeUser(String userId) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = KeyFactory.createKey(USUARIO_TIPO, userId);

        datastore.delete(key);
    }
}