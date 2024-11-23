import java.util.LinkedList;

class Estudiante {
    private String nombre;
    private int id;
    private LinkedList<Integer> estadosDeAnimo; // Historial de estados de ánimo
    private LinkedList<Boolean> asistencia;    // Historial de asistencia

    public Estudiante(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.estadosDeAnimo = new LinkedList<>();
        this.asistencia = new LinkedList<>();
    }

    public void registrarEstadoDeAnimo(int estado, boolean asistio) {
        if (estadosDeAnimo.size() == 5) {
            estadosDeAnimo.poll(); // Eliminar el más antiguo
            asistencia.poll();    // Eliminar asistencia más antigua
        }
        estadosDeAnimo.add(estado);
        asistencia.add(asistio);
    }

    public double calcularPromedioAnimo() {
        return estadosDeAnimo.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public String getRegistroAsistencia() {
        StringBuilder registro = new StringBuilder();
        for (int i = 0; i < asistencia.size(); i++) {
            registro.append("Día ").append(i + 1).append(": ");
            registro.append(asistencia.get(i) ? "Asistió" : "No asistió").append("\n");
        }
        return registro.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
