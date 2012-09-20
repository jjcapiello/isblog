package ar.com.carsonromero.dao;

import java.util.List;

public interface IBaseDao<T> {

	void guardar(T modelo);
	
	void eliminar(T modelo);
	
	List<T> listar(String consulta);
	
	List<T> listar(SimpleQuery simpleQuery, Object... args);
	
	T primero(SimpleQuery simpleQuery, Object... args);
	
}
