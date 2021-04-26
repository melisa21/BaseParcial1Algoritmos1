import archivos.ManejadorArchivosGenerico;
import geant.Almacen;
import geant.IAlmacen;
import geant.IProducto;
import tdas.INodo;

public class Principal {
    public static void main(String[] args) throws Exception {
        System.out.println("Comenzando ejecución UT3_TA2");

        IAlmacen almacen = new Almacen();
        ManejadorArchivosGenerico manejadorArchivos = new ManejadorArchivosGenerico();
        String[] contenidoArchivoAltas = manejadorArchivos.leerArchivo("src/altas.txt");

        almacen.agregarListaProducto(contenidoArchivoAltas);
        System.out.println(almacen.cantidadProductos());
        almacen.valorTotal();

        /*INodo<IProducto> nodoABuscar = almacen.getListaProductos().buscar("1000177");

        System.out.println(nodoABuscar.getDato().getNombre() + " " + nodoABuscar.getDato().getStock());
*/
        
        almacen.ventaProductos("src/ventas.txt");
        System.out.println(almacen.cantidadProductos());
        almacen.valorTotal();
/*
        nodoABuscar = almacen.getListaProductos().buscar("1000177");
        System.out.println(nodoABuscar.getDato().getNombre() + " " + nodoABuscar.getDato().getStock());

        System.out.println("Con elementos 1000001 y 1000002");
        almacen.getListaProductos().imprimir();
        almacen.getListaProductos().eliminar("1000001");
        almacen.getListaProductos().eliminar("1000002");

        System.out.println("Sin elementos 1000001 y 1000002");
        almacen.getListaProductos().imprimir();

        System.out.println("Fin de la ejecución UT3_TA2_Ej1");

        System.out.println("====================================");
        System.out.println("Comienzo de la ejecución UT3_TA2_Ej2");

        IProducto productoABuscar = almacen.buscarPorCodigo("1000177");
        System.out.println(productoABuscar.getNombre() + " " + productoABuscar.getStock());

        productoABuscar = almacen.buscarPorDescripcion("VINO BLANCO DULCE DE MESA SANTA TERESA");
        System.out.println(productoABuscar.getNombre() + " " + productoABuscar.getStock());
*/
        almacen.eliminarProductos("src/elim.txt");
        System.out.println(almacen.cantidadProductos());
        almacen.valorTotal();

        //almacen.listarOrdenadoPorNombre();

    }
}
