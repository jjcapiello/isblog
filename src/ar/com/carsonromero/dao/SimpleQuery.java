package ar.com.carsonromero.dao;

public class SimpleQuery {
	private Class<?> clazz;
    private String filter;
    private String ordering;
    private Integer rangeStart;
    private Integer rangeEnd;

    public SimpleQuery(Class<?> clazz) {
        super();
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean hasFilter() {
        return (filter != null);
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean hasOrdering() {
        return (ordering != null);
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public boolean hasRange() {
        return ((rangeStart != null) && (rangeEnd != null));
    }

    public void setRange(Integer rangeStart, Integer rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public Integer getRangeStart() {
        return rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }
}
