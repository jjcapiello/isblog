package ar.edu.kennedy.isblog.dao;

public class SimpleQuery {
	private Class<?> clase;
    private String filtro;
    private String orden;
    private Integer rangoInicio;
    private Integer rangoFin;

    public SimpleQuery(Class<?> clase) {
        super();
        this.clase = clase;
    }

    public Class<?> getClase() {
        return clase;
    }

    public boolean tieneFiltro() {
        return (filtro != null);
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public boolean tieneOrden() {
        return (orden != null);
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public boolean tieneRango() {
        return ((rangoInicio != null) && (rangoFin != null));
    }

    public void setRango(Integer rangoInicio, Integer rangoFin) {
        this.rangoInicio = rangoInicio;
        this.rangoFin = rangoFin;
    }

    public Integer getRangoInicio() {
        return rangoInicio;
    }

    public Integer getRangeEnd() {
        return rangoFin;
    }
}
