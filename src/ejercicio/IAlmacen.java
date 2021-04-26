package geant;

import tdas.ILista;

public interface IAlmacen {

    public String getDireccion();

    public void setDireccion(String direccion);

    public String getTelefono();

    public void setTelefono(String telefono);

    public String getNombre();

    public void agregarListaProducto(String[] listaProductos);

    public void ventaProductos(String nombreArchivo);

    public ILista<IProducto> getListaProductos();

    /**
     * Incorporar un nuevo producto al supermercado.
     *
     * @param unProducto
     */
    public void insertarProducto(IProducto unProducto);

    /**
     * Eliminar productos que ya no se venden (por no ser comercializados m�s).
     *
     * @param clave
     * @return
     */
    public boolean eliminarProducto(Comparable clave);

    public void eliminarProductos(String nombreArchivo);
    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductos();

    /**
     * Dado un separador ej.:",", ";", imprime los productos separados por tal
     * caracter
     *
     * @param separador
     * @return
     */
    public String imprimirSeparador(String separador);

    /**
     * Agregar stock a un producto existente.
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public Boolean agregarStock(Comparable clave, Integer cantidad);

    /**
     * Simular la venta de un producto (reducir el stock de un producto
     * existente
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public Integer restarStock(Comparable clave, Integer cantidad);

    /**
     * Dado un código de producto, indicar las existencias del mismo en el
     * almacén.
     *
     * @param clave
     * @return
     */
    public IProducto buscarPorCodigo(Comparable clave);

    /**
     * Listar todos los productos registrados, ordenados por nombre, presentando
     * además su stock. Imprime por consola la lista de todos los productos
     * registrados y su stock actual.
     */
    public void listarOrdenadoPorNombre();

    /**
     * Busca un producto por su descripción.
     *
     * @param descripcion
     * @return
     */
    public IProducto buscarPorDescripcion(String descripcion);

    /**
     * Retorna el tamaño del almacen: cantidad de productos. No es lo mismo que
     * el total de stock, sino que ser�a en definitiva el tama�o de la lista.
     *
     * @return
     */
    public int cantidadProductos();
    public void valorTotal();
}
