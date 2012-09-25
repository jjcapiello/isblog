package ar.edu.kennedy.isblog.persistencia;

import java.util.List;

import ar.edu.kennedy.isblog.dao.IBaseDao;
import ar.edu.kennedy.isblog.modelo.Articulo;

public interface IArticuloDao extends  IBaseDao<Articulo> {

	public Articulo masReciente();
	
	public List<Articulo> masRecientes(int cantidad);
	public List<Articulo> listar();

	public Articulo obtenerPorId(Long id);
	
}
