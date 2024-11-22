import java.util.*;

public class GestorEncuestas {
    private Map<Integer, Estudiante> estudiantes;

    public GestorEncuestas() {
        estudiantes = new HashMap<>();
    }

    public void agregarEstudiante(int id, String nombre) {
        estudiantes.put(id, new Estudiante(nombre, id));
    }

    public void registrarRespuesta(int id, int estado) {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante != null) {
            estudiante.registrarEstadoAnimo(estado);
            estudiante.registrarAsistencia();

            double promedio = estudiante.calcularPromedioAnimo();
            if (promedio < 3) {
                System.out.println("Alerta: El estudiante " + estudiante.getNombre() +
                        " requiere asistencia psicológica.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    public void mostrarResumen() {
        for (Estudiante e : estudiantes.values()) {
            System.out.println("Estudiante: " + e.getNombre());
            System.out.println("Promedio de ánimo: " + e.calcularPromedioAnimo());
            System.out.println("Asistencia: " + e.getAsistencia());
            System.out.println("Últimos estados de ánimo: " + e.getEstadosDeAnimo());
            System.out.println("-----------------------------");
        }
    }
}

