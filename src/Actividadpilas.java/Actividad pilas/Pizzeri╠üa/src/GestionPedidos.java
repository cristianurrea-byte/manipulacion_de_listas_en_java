



class Pila {

    // Referencia al elemento de arriba (el tope).
    // Si "tope" es null, la pila está vacía.
    private Pizza tope;

    public Pila() {
        this.tope = null;
    }

    // PUSH: agregar una pizza al TOPE
    public void push(Pizza nueva) {
        
        nueva.siguiente = tope;   
        tope            = nueva;  
    }

    // POP: retirar la pizza del TOPE y devolverla
    public Pizza pop() {
        if (isEmpty()) return null; 

        Pizza aux = tope;            // 1. se guarda el tope antes de moverlo
        tope      = tope.siguiente;  // 2. el tope baja un puesto
        aux.siguiente = null;        // 3. se desconecta la que sale

        return aux;
    }

    // PEEK: ver el TOPE sin retirarlo 
    public Pizza peek() {
        if (isEmpty()) return null;
        return tope;
    }

    // ISEMPTY: ¿la pila está vacía?
    public boolean isEmpty() {
        return tope == null; // 
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



public class GestionPedidos {

    private Pila principal;   // pila de pedidos activos 
    private Pila secundaria;  // pila de pedidos deshechos 

    public GestionPedidos() {
        this.principal  = new Pila();
        this.secundaria = new Pila();
    }

    // REGISTRAR PEDIDO: push() en la pila principal
    public void registrarPedido(Pizza nueva) {
        principal.push(nueva);

        while (!secundaria.isEmpty()) {
            secundaria.pop();
        }
    }

    // DESHACER (Undo): sale de principal, entra a secundaria
    public Pizza deshacer() {
        if (principal.isEmpty()) return null;

        Pizza salio = principal.pop(); 
        secundaria.push(salio);
        return salio;
    }

    // REHACER (Redo): sale de secundaria, vuelve a entrar a principal
    public Pizza rehacer() {
        if (secundaria.isEmpty()) return null;

        Pizza recuperada = secundaria.pop(); 
        principal.push(recuperada);          
        return recuperada;
    }

    // MOSTRAR PEDIDO ACTUAL: 
    public Pizza pedidoActual() {
        return principal.peek();
    }
}
