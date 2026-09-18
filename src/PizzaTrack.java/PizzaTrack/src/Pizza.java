/*
 * CLASE Pizza
 * -----------
 * Representa UN PEDIDO y, al mismo tiempo, funciona como NODO de la
 * pila: el mismo objeto guarda sus datos Y el puntero "siguiente" que
 * lo conecta con el pedido de abajo.
 *
 * Es la misma idea que tenías en Cliente.java: no se necesita una
 * clase Nodo aparte porque el puntero vive DENTRO de la clase de datos.
 */
public class Pizza {

    // --- Datos del pedido ---
    String nombre; // nombre de la pizza, ej. "Hawaiana"

    /*
     * ARREGLO FIJO DE TAMAÑO 3
     * ------------------------
     * La tarea exige EXACTAMENTE 3 ingredientes por pizza, ni más ni
     * menos. Por eso se usa un arreglo (tamaño fijo) y no una lista.
     *
     *   ingredientes → [ 0 ]        [ 1 ]      [ 2 ]
     *                  "Queso"     "Jamón"    "Piña"
     */
    String[] ingredientes = new String[3];

    // --- Puntero de la pila ---
    // Apunta al pedido que queda DEBAJO de este en la pila.
    // Es null cuando este pedido está en el fondo.
    Pizza siguiente;

    /*
     * Constructor
     * -----------
     * Se llama al escribir:
     *     new Pizza("Hawaiana", misIngredientes)
     *
     * Guarda el nombre, copia los ingredientes y deja "siguiente" en
     * null porque el objeto recién creado todavía no está enlazado a
     * nadie: eso lo hace la Pila cuando se llama a push().
     */
    public Pizza(String nombre, String[] ingredientes) {
        this.nombre    = nombre;
        this.siguiente = null; // sin vecino debajo todavía

        /*
         * Se COPIAN los 3 ingredientes uno por uno, en vez de escribir
         * "this.ingredientes = ingredientes".
         *
         * Motivo: un arreglo es un objeto. Si solo se igualara la
         * referencia, esta Pizza y el arreglo original de afuera
         * apuntarían al MISMO arreglo en memoria; si alguien lo cambia
         * afuera, el pedido guardado cambiaría solo sin que nadie lo
         * pida. Copiando, cada Pizza tiene su propio arreglo.
         */
        for (int i = 0; i < 3; i++) {
            this.ingredientes[i] = ingredientes[i];
        }
    }

    /*
     * toString()
     * ----------
     * Java llama automáticamente a este método cuando hacemos
     * System.out.println(unaPizza). Con @Override reemplazamos la
     * versión por defecto (que mostraría una dirección de memoria
     * inútil) por un texto legible.
     */
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
