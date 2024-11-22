//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

// Clase que representa una subtarea en forma de nodo de árbol
class SubTarea {
    String descripcion;
    boolean completada;
    List<SubTarea> dependencias;

    public SubTarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
        this.dependencias = new ArrayList<>();
    }

    // Método para agregar una dependencia (subtarea)
    public void agregarDependencia(SubTarea subTarea) {
        dependencias.add(subTarea);
    }

    // Método para marcar como completada la subtarea y todas sus dependencias
    public boolean completar() {
        for (SubTarea dep : dependencias) {
            if (!dep.completada) {
                System.out.println("No se puede completar la subtarea '" + descripcion + "' hasta que todas las dependencias estén completas.");
                return false;
            }
        }
        completada = true;
        System.out.println("Subtarea '" + descripcion + "' completada.");
        return true;
    }
}

// Clase que representa una tarea principal
class Tarea {
    String nombre;
    LinkedList<SubTarea> subtareas;

    public Tarea(String nombre) {
        this.nombre = nombre;
        this.subtareas = new LinkedList<>();
    }

    // Método para agregar una subtarea
    public void agregarSubTarea(SubTarea subTarea) {
        subtareas.add(subTarea);
    }

    // Método para marcar una tarea como completada si todas sus subtareas están completadas
    public boolean completarTarea() {
        for (SubTarea sub : subtareas) {
            if (!sub.completada) {
                System.out.println("La tarea '" + nombre + "' no puede completarse hasta que todas las subtareas estén completas.");
                return false;
            }
        }
        System.out.println("Tarea '" + nombre + "' completada.");
        return true;
    }
}

public class GestorDeTareas {
    public static void main(String[] args) {
        // Creamos una lista de tareas principales
        LinkedList<Tarea> listaDeTareas = new LinkedList<>();

        // Creamos una tarea principal
        Tarea tarea1 = new Tarea("Preparar presentación");
        listaDeTareas.add(tarea1);

        // Creamos subtareas para la tarea
        SubTarea subTarea1 = new SubTarea("Hacer investigación");
        SubTarea subTarea2 = new SubTarea("Escribir borrador");
        SubTarea subTarea3 = new SubTarea("Crear diapositivas");

        // Agregamos dependencias entre subtareas
        subTarea2.agregarDependencia(subTarea1); // El borrador depende de la investigación
        subTarea3.agregarDependencia(subTarea2); // Las diapositivas dependen del borrador

        // Agregamos subtareas a la tarea principal
        tarea1.agregarSubTarea(subTarea1);
        tarea1.agregarSubTarea(subTarea2);
        tarea1.agregarSubTarea(subTarea3);

        // Intentamos completar las subtareas y la tarea principal
        subTarea1.completar(); // Completar "Hacer investigación"
        subTarea2.completar(); // Completar "Escribir borrador"
        subTarea3.completar(); // Completar "Crear diapositivas"

        // Finalmente, intentamos completar la tarea principal
        tarea1.completarTarea();
    }
}
