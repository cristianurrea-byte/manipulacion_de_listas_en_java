/*
 * ARCHIVO: GestionPedidos.java
 * ----------------------------
 * Este archivo contiene DOS clases:
 *
 *   1) Pila            -> sin la palabra "public". En Java, un archivo
 *                          solo puede tener UNA clase public (la que
 *                          da nombre al archivo). Las demás van aquí
 *                          sin "public" y son visibles para cualquier
 *                          otra clase del mismo paquete (en este
 *                          proyecto, todas las clases están en el
 *                          paquete por defecto, así que GestionPedidos
 *                          y Principal la pueden usar sin problema).
 *
 *   2) GestionPedidos   -> SÍ es public, porque debe coincidir con el
 *                          nombre del archivo.
 *
 * Se agrupan en un mismo archivo porque "Pila" es una pieza interna:
 * nadie fuera de este proyecto necesita usarla por separado, siempre
 * se usa A TRAVÉS de GestionPedidos.
 */


// =================================================================
// CLASE 1: Pila
// =================================================================
/*
 * Estructura LIFO (Last In, First Out): el ÚLTIMO en entrar es el
 * PRIMERO en salir. Como una pila de platos: solo se pone y se quita
 * por ARRIBA. Ese extremo se llama TOPE.
 *
 * Implementada DESDE CERO con lista ligada (sin java.util.Stack).
 * La clase Pizza ES el nodo (lleva el puntero "siguiente" adentro),
 * por eso no hace falta una clase Nodo aparte.
 */
class Pila {

    // Referencia al elemento de arriba (el tope).
    // Si "tope" es null, la pila está vacía.
    private Pizza tope;

    public Pila() {
        this.tope = null;
    }

    // PUSH: poner una pizza en el TOPE
    public void push(Pizza nueva) {
        /*
         * 1) la nueva apunta hacia abajo, al tope actual
         * 2) la nueva pasa a ser el tope
         * Este orden es crítico: si se hiciera al revés se perdería
         * la referencia al resto de la pila.
         */
        nueva.siguiente = tope;   // 1
        tope            = nueva;  // 2
    }

    // POP: retirar la pizza del TOPE y devolverla
    public Pizza pop() {
        if (isEmpty()) return null; // no hay nada que sacar

        Pizza aux = tope;            // 1. se guarda el tope antes de moverlo
        tope      = tope.siguiente;  // 2. el tope baja un puesto
        aux.siguiente = null;        // 3. se desconecta la que sale

        return aux;
    }

    // PEEK: ver el TOPE sin retirarlo (no mueve ningún puntero)
    public Pizza peek() {
        if (isEmpty()) return null;
        return tope;
    }

    // ISEMPTY: ¿la pila está vacía?
    public boolean isEmpty() {
        return tope == null; // ya es booleano, se devuelve directo
    }

    // MOSTRAR: listar del TOPE hacia el FONDO
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("   (vacía)");
            return;
        }
        Pizza aux = tope; // recorre SIN mover el tope real
        while (aux != null) {
            System.out.println("   " + aux);
            aux = aux.siguiente;
        }
    }
}


// =================================================================
// CLASE 2: GestionPedidos
// =================================================================
/*
 * Coordina DOS objetos Pila para lograr Undo/Redo:
 *
 *   principal  -> pedidos ACTIVOS (aquí se ve el pedido actual, peek)
 *   secundaria -> pedidos DESHECHOS, en espera de recuperarse
 *
 *        registrar()        deshacer()          rehacer()
 *        push(principal)    pop(principal)      pop(secundaria)
 *                            + push(secundaria)   + push(principal)
 */
public class GestionPedidos {

    private Pila principal;   // pila de pedidos activos (Undo)
    private Pila secundaria;  // pila de pedidos deshechos (Redo)

    public GestionPedidos() {
        this.principal  = new Pila();
        this.secundaria = new Pila();
    }

    // REGISTRAR PEDIDO: push() en la pila principal
    public void registrarPedido(Pizza nueva) {
        principal.push(nueva);

        /*
         * Al llegar un pedido NUEVO se descarta cualquier "rehacer"
         * pendiente (igual que en Word: escribir algo nuevo borra el
         * historial de "rehacer" anterior).
         */
        while (!secundaria.isEmpty()) {
            secundaria.pop();
        }
    }

    // DESHACER (Undo): sale de principal, entra a secundaria
    public Pizza deshacer() {
        if (principal.isEmpty()) return null;

        Pizza salio = principal.pop(); // la última registrada
        secundaria.push(salio);        // se guarda para poder rehacer
        return salio;
    }

    // REHACER (Redo): sale de secundaria, vuelve a entrar a principal
    public Pizza rehacer() {
        if (secundaria.isEmpty()) return null;

        Pizza recuperada = secundaria.pop(); // la última deshecha
        principal.push(recuperada);          // vuelve a estar activa
        return recuperada;
    }

    // MOSTRAR PEDIDO ACTUAL: peek() de la pila principal
    public Pizza pedidoActual() {
        return principal.peek();
    }
}
