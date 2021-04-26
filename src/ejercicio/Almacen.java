package geant;

import tdas.ILista;
import tdas.INodo;
import tdas.Nodo;
import archivos.ManejadorArchivosGenerico;
import tdas.Lista;
import tdas.ListaOrdenada;

public class Almacen implements IAlmacen {
    private String direccion;
    private String telefono;
    private String nombre;
    private ILista<IProducto> listaProductos = new Lista<IProducto>();

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public ILista<IProducto> getListaProductos() {
        return this.listaProductos;
    }

    /**
     * Incorporar un nuevo producto al supermercado.
     *
     * @param unProducto
     */
    public void insertarProducto(IProducto unProducto) {
        INodo<IProducto> nodo = new Nodo(unProducto.getEtiqueta(), unProducto);
        this.listaProductos.insertarUltimo(nodo);
    }

    public void agregarListaProducto(String[] listaNuevoProductos) {
        INodo<IProducto> nodo;
        for (String linea : listaNuevoProductos) {
            String[] lineaProducto = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            Comparable etiqueta = lineaProducto[0];
            String descripcion = lineaProducto[1];
            int precio = Integer.parseInt(lineaProducto[2]);
            int stock = Integer.parseInt(lineaProducto[3]);
            IProducto producto = new Producto(etiqueta, descripcion, precio, stock);
            nodo = this.listaProductos.buscar(etiqueta);
            if (nodo != null) {
                nodo.getDato().setStock(stock + nodo.getDato().getStock());
            } else {
                this.insertarProducto(producto);
            }
        }
    }


    /**
     * Eliminar productos que ya no se venden (por no ser comercializados mas).
     *
     * @param clave
     * @return
     */
    public boolean eliminarProducto(Comparable clave) {
        return this.listaProductos.eliminar(clave);
    }

    public void eliminarProductos(String nombreArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        for (String linea : lineas) {
            String[] detalleVenta = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            String etiqueta = detalleVenta[0];
            this.eliminarProducto(etiqueta);
        }
    }

    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductos() {
        return listaProductos.imprimir();

    }

    /**
     * Dado un separador ej.:",", ";", imprime los productos separados por tal
     * caracter
     *
     * @param separador
     * @return
     */
    public String imprimirSeparador(String separador) {
        return null;
    }

    /**
     * Agregar stock a un producto existente.
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        INodo<IProducto> nodo = this.listaProductos.buscar(clave);
        if (nodo != null) {
            IProducto producto = nodo.getDato();
            Integer stock = producto.getStock();
            producto.setStock(stock + cantidad);
            return true;
        }
        return false;

    }

    /**
     * Simular la venta de un producto (reducir el stock de un producto existente)
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public Integer restarStock(Comparable clave, Integer cantidad) {
        INodo<IProducto> nodo = this.listaProductos.buscar(clave);
        if (nodo != null) {
            IProducto producto = nodo.getDato();
            Integer stock = producto.getStock();
            producto.setStock(Math.max(stock - cantidad, 0));
            return producto.getStock();
        }
        return 0;
    }

    /**
     * Dado un código de producto, indicar las existencias del mismo en el almacén.
     *
     * @param clave
     * @return
     */
    public IProducto buscarPorCodigo(Comparable clave) {
        INodo<IProducto> nodo = this.getListaProductos().buscar(clave);
        if (nodo == null) {
            return null;
        }
        return nodo.getDato();
    }

    /**
     * Listar todos los productos registrados, ordenados por nombre, presentando
     * además su stock. Imprime por consola la lista de todos los productos
     * registrados y su stock actual.
     * T(N) = O(N^2)
     */
    public void listarOrdenadoPorNombre() {
        INodo<IProducto> aux = listaProductos.getPrimero();
        ListaOrdenada<IProducto> listOrdenada = new ListaOrdenada<>();
        while (aux != null){
            INodo<IProducto> nodoNuevo = new Nodo<>(aux.getDato().getNombre().replaceAll("[^\\w\\s]", "")+", Stock: "+aux.getDato().getStock().toString(), aux.getDato());
            listOrdenada.insertarOrdenado(nodoNuevo);
            aux = aux.getSiguiente();
        }
        listOrdenada.imprimir();

    }

    /**
     * Busca un producto por su descripción.
     *
     * @param descripcion
     * @return
     */
    public IProducto buscarPorDescripcion(String descripcion) {
        ILista<IProducto> lista = this.getListaProductos();
        INodo<IProducto> aux = lista.getPrimero();
        while (aux != null && !aux.getDato().getNombre().equals(descripcion))
            aux = aux.getSiguiente();
        return aux.getDato();
    }

    /**
     * Retorna el tamaño del almacen: cantidad de productos. No es lo mismo que el
     * total de stock, sino que seria en definitiva el tamaño de la lista.
     *
     * @return
     */
    public int cantidadProductos() {
        return this.getListaProductos().cantElementos();
    }

    public void ventaProductos(String nombreArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        for (String linea : lineas) {
            String[] detalleVenta = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            String etiqueta = detalleVenta[0];
            int cantidad = Integer.parseInt(detalleVenta[1]);
            this.restarStock(etiqueta, cantidad);
        }
    }

    public void valorTotal(){
        INodo<IProducto> nodoProducto = this.listaProductos.getPrimero();
        int valor = 0;
        while(nodoProducto != null){
            if(nodoProducto.getDato().getStock() != 0 && nodoProducto.getDato().getPrecio() != 0){
                valor = valor + (nodoProducto.getDato().getStock() * nodoProducto.getDato().getPrecio());
            }
            nodoProducto = nodoProducto.getSiguiente();
        }
        System.out.println(valor);
    }
}
