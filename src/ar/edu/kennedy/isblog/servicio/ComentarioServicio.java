package ar.edu.kennedy.isblog.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.kennedy.isblog.dao.SimpleQuery;
import ar.edu.kennedy.isblog.modelo.Comentario;
import ar.edu.kennedy.isblog.persistencia.IComentarioDao;

@Service
public class ComentarioServicio {
	
	@Autowired
	private IComentarioDao comentarioDao;
	
	public List<Comentario> todos(){
		SimpleQuery query = new SimpleQuery(Comentario.class);
		query.setOrden("fecha desc");
        return comentarioDao.listar(query);
	}

	public Comentario obtenerPorId(Long id) {
		return comentarioDao.obtenerPorId(id);
	}

	public void eliminar(Long id) {
		comentarioDao.eliminar(id);
	}

	public void aprobar(Long id) {
		Comentario comentario = comentarioDao.obtenerPorId(id);
		comentario.setAprobado(true);
		comentarioDao.guardar(comentario);
	}
}
