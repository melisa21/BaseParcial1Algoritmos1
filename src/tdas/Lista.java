
package tdas;

public class Lista<T> implements ILista<T> {

    private INodo<T> primero;

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

    public INodo<T> buscar(Comparable clave) {
        INodo<T> aux = primero;
        while (aux != null && aux.compareTo(clave) != 0)
            aux = aux.getSiguiente();
        return aux;
    }

    @Override
    public INodo<T> getPrimero() {
        return this.primero;
    }

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
                aux.imprimir();
                aux = aux.getSiguiente();
            }
        }
        return contenido;
    }

    @Override
    public String imprimir(String separador) {
        INodo<T> aux = primero;
        String contenido = "";
        if (!esVacia()) {
            while (aux != null) {
                aux.imprimir();
                aux = aux.getSiguiente();
            }
        }
        return contenido;
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
