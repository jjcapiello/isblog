package ar.com.carsonromero.dao.jdo;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.carsonromero.dao.IBaseDao;
import ar.com.carsonromero.dao.SimpleQuery;

public abstract class BaseDao<T> implements IBaseDao<T> {

	protected final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	protected PersistenceManagerFactory pmFactory;

	@Override
	public void guardar(T modelo) {
		PersistenceManager pm = pmFactory.getPersistenceManager();
		try {
			pm.makePersistent(modelo);
		} finally {
			pm.close();
		}
	}

	@Override
	public void eliminar(T modelo) {
		PersistenceManager pm = pmFactory.getPersistenceManager();
		try {
			pm.deletePersistent(modelo);
		} finally {
			pm.close();
		}
	}

	@Override
	public List<T> listar(String consulta) {
		log.info(String.format("listar consulta: %s", consulta));
		PersistenceManager pm = pmFactory.getPersistenceManager();
		try {
			@SuppressWarnings("unchecked")
			List<T> lista = (List<T>) pm.newQuery(consulta).execute();
			lista.size();
			return lista;
		} finally {
			pm.close();
		}
	}

	@Override
	public List<T> listar(SimpleQuery simpleQuery, Object... args) {
		PersistenceManager pm = pmFactory.getPersistenceManager();
		try {
			Query query = pm.newQuery(simpleQuery.getClazz());
			if (simpleQuery.hasFilter()) {
				query.setFilter(simpleQuery.getFilter());
			}
			if (simpleQuery.hasOrdering()) {
				query.setOrdering(simpleQuery.getOrdering());
			}
			if (simpleQuery.hasRange()) {
				query.setRange(simpleQuery.getRangeStart(),
						simpleQuery.getRangeEnd());
			}
			@SuppressWarnings("unchecked")
			List<T> lista = (List<T>) query.executeWithArray(args);
			lista.size();
			return lista;
		} finally {
			pm.close();
		}
	}

	@Override
	public T primero(SimpleQuery simpleQuery, Object... args) {
		PersistenceManager pm = pmFactory.getPersistenceManager();
		try {
			Query query = pm.newQuery(simpleQuery.getClazz());
			if (simpleQuery.hasFilter()) {
				query.setFilter(simpleQuery.getFilter());
			}
			if (simpleQuery.hasOrdering()) {
				query.setOrdering(simpleQuery.getOrdering());
			}
			query.setRange(0, 1);
			@SuppressWarnings("unchecked")
			List<T> lista = (List<T>) query.executeWithArray(args);
			if (lista.size() > 0) {
				return lista.get(0);
			} else {
				return null;
			}
		} finally {
			pm.close();
		}
	}

}
