package ar.edu.kennedy.isblog.dao.jdo;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.kennedy.isblog.dao.IBaseDao;
import ar.edu.kennedy.isblog.dao.SimpleQuery;

public abstract class BaseDao<T> implements IBaseDao<T> {

	protected final Logger log = Logger.getLogger(getClass().getName());

	protected Class<T> clase;
	
	@Autowired
	protected PersistenceManagerFactory persistenceManagerFactoryName;

	protected BaseDao(Class<T> clase)
	 {
	  this.clase = clase;
	 }
	
	@Override
	public void guardar(T modelo) {
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			pm.makePersistent(modelo);
		} finally {
			pm.close();
		}
	}

	@Override
	public void eliminar(T modelo) {
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			pm.deletePersistent(modelo);
		} finally {
			pm.close();
		}
	}
	
	@Override
	public void eliminar(Long id) {
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			pm.currentTransaction().begin();

			T modelo = (T) pm.getObjectById(clase, id);
			pm.deletePersistent(modelo);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
	
	@Override
	public T obtener(Long id) {
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {

			return pm.getObjectById(clase, id);
		
		} finally {
			pm.close();
		}
	}
	
	
	@Override
	public List<T> listar(String consulta) {
		log.info(String.format("listar consulta: %s", consulta));
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
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
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			Query query = pm.newQuery(simpleQuery.getClase());
			if (simpleQuery.tieneFiltro()) {
				query.setFilter(simpleQuery.getFiltro());
			}
			if (simpleQuery.tieneOrden()) {
				query.setOrdering(simpleQuery.getOrden());
			}
			if (simpleQuery.tieneRango()) {
				query.setRange(simpleQuery.getRangoInicio(),
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
		PersistenceManager pm = persistenceManagerFactoryName.getPersistenceManager();
		try {
			Query query = pm.newQuery(simpleQuery.getClase());
			if (simpleQuery.tieneFiltro()) {
				query.setFilter(simpleQuery.getFiltro());
			}
			if (simpleQuery.tieneOrden()) {
				query.setOrdering(simpleQuery.getOrden());
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
