/*
 * CLASE Principal
 * ---------------
 * Punto de entrada del programa (Pizza-Track). Muestra un menú en
 * bucle hasta que el usuario elija "Salir".
 *
 * Toda la lógica de las dos pilas está DELEGADA en GestionPedidos:
 * este menú solo lee del teclado y llama a sus métodos. Así, si
 * mañana cambia la forma de guardar los pedidos, este archivo casi
 * no se toca.
 */
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        // Un solo objeto que coordina las dos pilas (principal y secundaria)
        GestionPedidos pizzaTrack = new GestionPedidos();

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        /*
         * do-while: muestra el menú AL MENOS una vez y repite mientras
         * la opción no sea 0.
         */
        do {
            System.out.println("\n===== PIZZA-TRACK =====");
            System.out.println("1. Registrar Pizza");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Mostrar Pedido Actual");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); // limpia el salto de línea pendiente

                switch (opcion) {

                    case 1: { // ---------- REGISTRAR PIZZA ----------
                        Pizza nueva = leerPizza(sc);
                        pizzaTrack.registrarPedido(nueva);
                        System.out.println("Pedido registrado: " + nueva);
                        break;
                    }

                    case 2: { // ---------- DESHACER (Undo) ----------
                        Pizza deshecha = pizzaTrack.deshacer();
                        if (deshecha != null)
                            System.out.println("Se deshizo: " + deshecha);
                        else
                            System.out.println("No hay pedidos para deshacer.");
                        break;
                    }

                    case 3: { // ---------- REHACER (Redo) ----------
                        Pizza recuperada = pizzaTrack.rehacer();
                        if (recuperada != null)
                            System.out.println("Se rehizo: " + recuperada);
                        else
                            System.out.println("No hay pedidos para rehacer.");
                        break;
                    }

                    case 4: { // ---------- MOSTRAR PEDIDO ACTUAL ----------
                        Pizza actual = pizzaTrack.pedidoActual();
                        if (actual != null)
                            System.out.println("Pedido actual (tope): " + actual);
                        else
                            System.out.println("No hay pedidos activos.");
                        break;
                    }

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (Exception e) {
                System.out.println("Entrada no válida.");
                sc.nextLine(); // limpia el buffer si se escribió texto en vez de número
            }

        } while (opcion != 0);

        sc.close();
    }

    /*
     * MÉTODO AUXILIAR: leerPizza()
     * -----------------------------
     * Pide por teclado el nombre y los 3 ingredientes, y devuelve el
     * objeto Pizza ya construido. Es "static" porque main() también
     * lo es, y recibe el mismo Scanner que ya está leyendo el teclado.
     */
    public static Pizza leerPizza(Scanner sc) {
        System.out.print("Nombre de la pizza (ej. Hawaiana): ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese los 3 ingredientes:");
        String[] ingredientes = new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("  Ingrediente " + (i + 1) + ": ");
            ingredientes[i] = sc.nextLine();
        }

        return new Pizza(nombre, ingredientes);
    }
}
