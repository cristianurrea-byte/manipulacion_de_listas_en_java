
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        // Un solo objeto que coordina las dos pilas (principal y secundaria)
        GestionPedidos pizzas = new GestionPedidos();

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        
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
                sc.nextLine(); 

                switch (opcion) {

                    case 1: { // ---------- REGISTRAR PIZZA ----------
                        Pizza nueva = leerPizza(sc);
                        pizzas.registrarPedido(nueva);
                        System.out.println("Pedido registrado: " + nueva);
                        break;
                    }

                    case 2: { // ---------- DESHACER (Undo) ----------
                        Pizza deshecha = pizzas.deshacer();
                        if (deshecha != null)
                            System.out.println("Se deshizo: " + deshecha);
                        else
                            System.out.println("No hay pedidos para deshacer.");
                        break;
                    }

                    case 3: { // ---------- REHACER (Redo) ----------
                        Pizza recuperada = pizzas.rehacer();
                        if (recuperada != null)
                            System.out.println("Se rehizo: " + recuperada);
                        else
                            System.out.println("No hay pedidos para rehacer.");
                        break;
                    }

                    case 4: { // ---------- MOSTRAR PEDIDO ACTUAL ----------
                        Pizza actual = pizzas.pedidoActual();
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
                sc.nextLine(); 
            }

        } while (opcion != 0);

        sc.close();
    }

    
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
