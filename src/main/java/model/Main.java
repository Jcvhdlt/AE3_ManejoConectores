package model;

import dao.CocheDAO;
import dao.CocheImpl;
import dao.PasajeroImpl;
import dao.PasajerosDAO;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CocheDAO cocheDAO = new CocheImpl();
        PasajerosDAO pasajeroDAO = new PasajeroImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Gestión de coches");
            System.out.println("2. Gestión de pasajeros");
            System.out.println("3. Terminar programa");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionCoches(cocheDAO, scanner);
                    break;
                case 2:
                    gestionDePasajeros(pasajeroDAO, scanner);
                    break;
                case 3:
                    System.out.println("Programa terminado.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void gestionCoches(CocheDAO cocheDAO, Scanner scanner) {
        while (true) {
            System.out.println("\nGestión de coches:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consultar coche por ID");
            System.out.println("4. Modificar coche por ID");
            System.out.println("5. Listar todos los coches");
            System.out.println("6. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Introduce el número de plazas: ");
                    int plazas = scanner.nextInt();

                    Coche nuevoCoche = new Coche(0, modelo, plazas);
                    cocheDAO.creaCoche(nuevoCoche);
                    System.out.println("Coche añadido correctamente.");
                    break;
                case 2:
                    System.out.println("Introduzca el id del coche que quiere borrar:");
                    int idDelete = scanner.nextInt();
                    cocheDAO.deleteCoche(idDelete);
                    System.out.println("Coche eliminado correctamente");
                    break;

                case 3:
                    System.out.println("Introduzca el ID:");
                    int id = scanner.nextInt();
                    System.out.println(cocheDAO.buscarCocheId(id));
                    break;

                case 4:
                    System.out.println("Introduce el ID del coche a modificar");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Introduzca nuevo modelo:");
                    String modeloC = scanner.nextLine();
                    System.out.println("Introduzca el número de plazas:");
                    int plazasC= scanner.nextInt();
                    Coche cocheUpdate = new Coche(idUpdate,modeloC, plazasC);
                    cocheDAO.updateCoche(cocheUpdate);
                    System.out.println("Modificacion realizada correctamente.");

                    break;
                case 5:
                    System.out.println(cocheDAO.listadoCoches());
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción no valida.");
            }
        }
    }

    public static void gestionDePasajeros(PasajerosDAO pasajeroDAO, Scanner scanner) {
        while (true) {
            System.out.println("\nGestión de pasajeros:");
            System.out.println("1. Añadir nuevo pasajero");
            System.out.println("2. Borrar pasajero por ID");
            System.out.println("3. Consultar pasajero por ID");
            System.out.println("4. Listar todos los pasajeros");
            System.out.println("5. Añadir pasajero a coche");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar pasajeros de un coche");
            System.out.println("8. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nombre del pasajero:");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduce la edad del pasajero:");
                    int edad = scanner.nextInt();
                    System.out.println("Introduce el peso del pasajero:");
                    double peso = scanner.nextDouble();

                    Pasajeros nuevoPasajero = new Pasajeros(0, nombre, edad, peso);
                    pasajeroDAO.crearPasajero(nuevoPasajero);
                    System.out.println("Pasajero añadido correctamente.");
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Opción no valida.");
            }
        }
    }
}
