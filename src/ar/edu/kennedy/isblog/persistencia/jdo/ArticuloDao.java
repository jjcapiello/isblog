package ar.edu.kennedy.isblog.persistencia.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import ar.edu.kennedy.isblog.dao.SimpleQuery;
import ar.edu.kennedy.isblog.dao.jdo.BaseDao;
import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.persistencia.IArticuloDao;

@Repository
public class ArticuloDao extends BaseDao<Articulo> implements IArticuloDao {

	protected ArticuloDao() {
		super(Articulo.class);
	}

	public Articulo obtenerPorId(Long id){
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			return  pm.getObjectById(Articulo.class, id);
		} finally {
			pm.close();
		}
	}
	
	@Override
	public Articulo masReciente() {
		SimpleQuery query = new SimpleQuery(Articulo.class);
        query.setOrden("fechaPublicacion desc");
        return primero(query);
	}

	@Override
	public List<Articulo> masRecientes(int cantidad) {
		SimpleQuery query = new SimpleQuery(Articulo.class);
		query.setOrden("fechaPublicacion desc");
		query.setRango(0, cantidad);
        return listar(query);
	}
	
	@Override
	public List<Articulo> listar() {
		SimpleQuery query = new SimpleQuery(Articulo.class);
		query.setOrden("fechaPublicacion desc");
        return listar(query);
	}

}
