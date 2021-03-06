package ar.edu.kennedy.isblog.persistencia.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import ar.edu.kennedy.isblog.dao.SimpleQuery;
import ar.edu.kennedy.isblog.dao.jdo.BaseDao;
import ar.edu.kennedy.isblog.modelo.Comentario;
import ar.edu.kennedy.isblog.persistencia.IComentarioDao;

@Repository
public class ComentarioDao extends BaseDao<Comentario> implements IComentarioDao {

	protected ComentarioDao() {
		super(Comentario.class);
	}

	public Comentario obtenerPorId(Long id){
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			return  pm.getObjectById(Comentario.class, id);
		} finally {
			pm.close();
		}
	}

	@Override
	public List<Comentario> comentariosPorArticulo(Long articuloId) {
		SimpleQuery query = new SimpleQuery(Comentario.class);
		query.setFiltro("articuloId == :articuloId");
		query.setOrden("fecha desc");
        return listar(query, articuloId);
	}

}
