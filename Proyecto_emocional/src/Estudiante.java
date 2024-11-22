import java.util.LinkedList;
import java.util.Queue;

public class Estudiante {
    private String nombre;
    private int id;
    private Queue<Integer> estadosDeAnimo;
    private int asistencia;

    public Estudiante(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.estadosDeAnimo = new LinkedList<>();
        this.asistencia = 0;
    }

    public void registrarEstadoAnimo(int estado) {
        if (estadosDeAnimo.size() == 5) {
            estadosDeAnimo.poll(); // Remover el estado más antiguo
        }
        estadosDeAnimo.offer(estado); // Añadir el nuevo estado
    }

    public double calcularPromedioAnimo() {
        return estadosDeAnimo.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public void registrarAsistencia() {
        this.asistencia++;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public Queue<Integer> getEstadosDeAnimo() {
        return new LinkedList<>(estadosDeAnimo); // Retorna una copia
    }
}
