import java.util.Scanner;

public class EncuestasApp {
    public static void main(String[] args) {
        GestorEncuestas gestor = new GestorEncuestas();
        Scanner scanner = new Scanner(System.in);

        // Agregar algunos estudiantes hola
        gestor.agregarEstudiante(1, "Juan Pérez");
        gestor.agregarEstudiante(2, "María López");

        boolean continuar = true;
        while (continuar) {
            System.out.println("1. Registrar respuesta");
            System.out.println("2. Mostrar resumen");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del estudiante: ");
                    int id = scanner.nextInt();
                    System.out.print("Ingrese estado de ánimo (1-10): ");
                    int estado = scanner.nextInt();
                    gestor.registrarRespuesta(id, estado);
                    break;
                case 2:
                    gestor.mostrarResumen();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
