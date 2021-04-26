package tdas;

public class ListaOrdenada<T> implements ILista<T> {

    private INodo<T> primero;

    @Override
    public INodo<T> getPrimero() {
        return this.primero;
    }

    /* cambiamos el tipo de dato en la firma de boolean a void
       no entendemos mucho el motivo para devolver un booleano
       originalmente devolviamos siempre true
    */
    public boolean insertarOrdenado(INodo<T> nodo) {
        if (nodo == null)
            return false;

        if (esVacia()) {
            nodo.setSiguiente(null);
            primero = nodo;
            return true;
        }

        if (nodo.compareTo(primero.getEtiqueta()) < 0) {
            nodo.setSiguiente(primero);
            primero = nodo;
            return true;
        }

        INodo<T> aux = primero;
        while (aux.getSiguiente() != null && aux.getSiguiente().compareTo(nodo.getEtiqueta()) < 0) {
            aux = aux.getSiguiente();
        }
        nodo.setSiguiente(aux.getSiguiente());
        aux.setSiguiente(nodo);
        return true;
    }

    @Override
    public void insertarUltimo(INodo<T> nodo) {
        if (this.esVacia()) {
            primero = nodo;
        } else {
            INodo<T> aux = primero;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);
        }
    }

    @Override
    public INodo<T> buscar(Comparable clave) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        INodo<T> aux;
        boolean encontro = false;

        // primero chequeamos si la lista está vacía
        if (this.esVacia()) {
            return false;
        } else {
            aux = primero;
        }

        // luego chequeamos si la lista tiene un solo nodo y si es el nodo a eliminar
        if (primero.getSiguiente() == null && aux.compareTo(clave) == 0) {
            primero = aux.getSiguiente();
            return true;
        }
        while (aux.getSiguiente() != null) {
            encontro = aux.getSiguiente().compareTo(clave) == 0;
            if (encontro) {
                INodo<T> nodoAEliminar = aux.getSiguiente();
                aux.setSiguiente(nodoAEliminar.getSiguiente());
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public String imprimir() {
        INodo<T> aux = primero;
        String contenido = "";
        if (!esVacia()) {
            while (aux != null) {
                aux.imprimirEtiqueta();
                aux = aux.getSiguiente();
            }
        }
        return contenido;
    }

    @Override
    public String imprimir(String separador) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int cantElementos() {
        int cantidad = 0;
        INodo<T> aux = primero;
        while (aux != null) {
            aux = aux.getSiguiente();
            cantidad++;
        }
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

}
