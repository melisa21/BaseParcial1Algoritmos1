package geant;

public class Producto implements IProducto {
    private Comparable etiqueta;
    private Integer precio;
    private Integer stock;
    private String descripcion;

    public Producto(Comparable etiqueta, String descripcion, Integer precio, Integer stock){
        this.etiqueta = etiqueta;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public Integer getPrecio() {
        return this.precio;
    }

    @Override
    public void setPrecio(Integer precio) {
        this.precio = precio;

    }

    @Override
    public Integer getStock() {
        return this.stock;
    }

    @Override
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String getNombre() {
        return this.descripcion;
    }

    @Override
    public void setNombre(String nombre) {
        this.descripcion = nombre;
    }
    
}