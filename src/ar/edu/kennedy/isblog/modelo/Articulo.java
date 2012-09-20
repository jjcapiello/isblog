package ar.edu.kennedy.isblog.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import com.google.appengine.api.datastore.Text;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Articulo {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private String titulo;
	
	@Persistent
	private String subtitulo;
	
	@Persistent
	private Text cuerpo;
	
	@Persistent
	private Date fechaPublicacion;

	public Articulo(String titulo, String subtitulo, String cuerpo) {
		super();
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.cuerpo = new Text(cuerpo);
		this.fechaPublicacion = Calendar.getInstance().getTime();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public Text getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(Text cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
