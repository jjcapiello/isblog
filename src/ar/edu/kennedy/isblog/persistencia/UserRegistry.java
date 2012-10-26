package ar.edu.kennedy.isblog.persistencia;

import ar.edu.kennedy.isblog.modelo.GaeUser;

public interface UserRegistry {

    GaeUser findUser(String userId);

    void registerUser(GaeUser newUser);

    void removeUser(String userId);
}