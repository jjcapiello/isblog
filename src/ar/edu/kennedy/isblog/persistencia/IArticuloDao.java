package ar.edu.kennedy.isblog.persistencia;

import java.util.List;

import ar.edu.kennedy.isblog.dao.IBaseDao;
import ar.edu.kennedy.isblog.modelo.Articulo;

public interface IArticuloDao extends  IBaseDao<Articulo> {

	public Articulo tipoMasReciente(int tipoArticulo);
	
	public List<Articulo> noticiasMasRecientes(int cantidad);
	public List<Articulo> listar(int tipoArticulo);

	public Articulo obtenerPorId(Long id);
	
}
