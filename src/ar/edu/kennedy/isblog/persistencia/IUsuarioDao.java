package ar.edu.kennedy.isblog.persistencia;

import ar.edu.kennedy.isblog.dao.IBaseDao;
import ar.edu.kennedy.isblog.modelo.Usuario;

public interface IUsuarioDao  extends  IBaseDao<Usuario> {

    Usuario findUser(String userId);

    void registerUser(Usuario newUser);

    void removeUser(String userId);
    
}