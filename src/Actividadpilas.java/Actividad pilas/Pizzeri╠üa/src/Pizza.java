
public class Pizza {

    
    String nombre; // nombre de la pizza

    
    String[] ingredientes = new String[3];

    // --- Puntero de la pila ---
    Pizza siguiente;

    /*
     * Constructor
     * -----------
     * Se llama al escribir: new Pizza
     */
    public Pizza(String nombre, String[] ingredientes) {
        this.nombre    = nombre;
        this.siguiente = null; // sin vecino debajo todavía

        for (int i = 0; i < 3; i++) {
            this.ingredientes[i] = ingredientes[i];
        }
    }

    
    @Override
    public String toString() {
        // Se arma el texto "Queso, Jamón, Piña" recorriendo el arreglo
        String lista = "";
        for (int i = 0; i < 3; i++) {
            lista += ingredientes[i];
            if (i < 2) lista += ", ";
        }

        return "[Pizza: " + nombre + " | Ingredientes: " + lista + "]";
    }
}
