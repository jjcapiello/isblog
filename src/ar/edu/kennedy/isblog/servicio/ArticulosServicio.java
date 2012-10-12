package ar.edu.kennedy.isblog.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.kennedy.isblog.modelo.Articulo;
import ar.edu.kennedy.isblog.persistencia.IArticuloDao;

@Service
public class ArticulosServicio {
	
	@Autowired
	private IArticuloDao articuloDao;
	
	public void guardar(Articulo articulo){
		articuloDao.guardar(articulo);
	}
	
	public Articulo obtenerPorId(Long id){
		return articuloDao.obtenerPorId(id);
	}
	
	public List<Articulo> masRecientes(int cantidad){
		return articuloDao.masRecientes(cantidad);
	}
	
	public void eliminar(Long id){
		articuloDao.eliminar(id);
	}
	
	public List<Articulo> todos(){
		return articuloDao.listar();
	}
}
