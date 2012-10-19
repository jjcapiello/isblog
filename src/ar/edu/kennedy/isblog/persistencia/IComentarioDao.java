package ar.edu.kennedy.isblog.persistencia;

import java.util.List;

import ar.edu.kennedy.isblog.dao.IBaseDao;
import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.modelo.Comentario;

public interface IComentarioDao extends  IBaseDao<Comentario> {

	public Comentario obtenerPorId(Long id);
	
	public List<Comentario> comentariosPorArticulo(Long articuloId);
	
	
}
