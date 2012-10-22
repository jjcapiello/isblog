package ar.edu.kennedy.isblog.modelo;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import com.google.appengine.api.datastore.Text;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Articulo {
	
	@NotPersistent
	public static final int NOTICIA = 1;

	@NotPersistent
	public static final int PROGRAMA = 2;
	
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
	private String imagenGrande;
	
	@Persistent
	private String imagenChica;
	
	@Persistent
	private Date fechaPublicacion;
	
	@Persistent
	private Date fechaModificacion;
	
	@Persistent
	private int tipoArticulo;

	@Persistent
	private boolean publico;
	
	public Articulo(int tipoArticulo) {
		super();
		this.titulo = "";
		this.subtitulo = "";
		this.cuerpo = new Text("");
		this.fechaPublicacion = new Date();
		this.fechaModificacion = new Date();
		this.tipoArticulo = tipoArticulo;
		this.publico = false;
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

	public String getCuerpo() {
		return cuerpo.getValue();
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = new Text(cuerpo);
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagenGrande() {
		return imagenGrande;
	}

	public void setImagenGrande(String imagenGrande) {
		this.imagenGrande = imagenGrande;
	}

	public String getImagenChica() {
		return imagenChica;
	}

	public void setImagenChica(String imagenChica) {
		this.imagenChica = imagenChica;
	}

	public int getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(int tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}
	
	
	
}
