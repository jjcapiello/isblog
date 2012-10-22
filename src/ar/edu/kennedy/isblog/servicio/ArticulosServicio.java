package ar.edu.kennedy.isblog.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.modelo.Comentario;
import ar.edu.kennedy.isblog.persistencia.IArticuloDao;
import ar.edu.kennedy.isblog.persistencia.IComentarioDao;

@Service
public class ArticulosServicio {
	
	@Autowired
	private IArticuloDao articuloDao;
	
	@Autowired
	private IComentarioDao comentarioDao;
	
	public Articulo guardar(Articulo articulo){
		return articuloDao.guardar(articulo);
	}
	
	public Articulo obtenerPorId(Long id){
		return articuloDao.obtenerPorId(id);
	}
	
	public List<Articulo> noticiasMasRecientes(int cantidad){
		return articuloDao.noticiasMasRecientes(cantidad);
	}
	
	public void eliminar(Long id){
		articuloDao.eliminar(id);
	}
	
	public List<Articulo> todos(int tipoArticulo){
		return articuloDao.listar(tipoArticulo);
	}
	
	public List<Comentario> comentariosPorArticulo(Long articuloId){
		return comentarioDao.comentariosPorArticulo(articuloId);
	}
}
