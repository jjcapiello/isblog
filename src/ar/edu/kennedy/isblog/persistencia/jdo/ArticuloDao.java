package ar.edu.kennedy.isblog.persistencia.jdo;

import org.springframework.stereotype.Repository;

import ar.com.carsonromero.dao.jdo.BaseDao;
import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.persistencia.IArticuloDao;

@Repository
public class ArticuloDao extends BaseDao<Articulo> implements IArticuloDao {

	

}
