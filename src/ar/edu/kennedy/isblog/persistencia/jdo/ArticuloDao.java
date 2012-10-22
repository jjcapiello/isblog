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
	public Articulo tipoMasReciente(int tipoArticulo) {
		SimpleQuery query = new SimpleQuery(Articulo.class);
		query.setFiltro("tipoArticulo == :tipoArticulo");
        query.setOrden("fechaPublicacion desc");
        return primero(query, tipoArticulo);
	}

	@Override
	public List<Articulo> noticiasMasRecientes(int cantidad) {
		SimpleQuery query = new SimpleQuery(Articulo.class);
		query.setFiltro("tipoArticulo == :tipoArticulo");
		query.setOrden("fechaPublicacion desc");
		query.setRango(0, cantidad);
        return listar(query, Articulo.NOTICIA);
	}
	
	@Override
	public List<Articulo> listar(int tipoArticulo) {
		SimpleQuery query = new SimpleQuery(Articulo.class);
		query.setFiltro("tipoArticulo == :tipoArticulo");
		query.setOrden("fechaPublicacion desc");
        return listar(query, tipoArticulo);
	}

}
