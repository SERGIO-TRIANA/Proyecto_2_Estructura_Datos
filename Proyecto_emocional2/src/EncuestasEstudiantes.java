import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;

public class EncuestasEstudiantes {
    private NodoEstudiante raiz;

    public EncuestasEstudiantes() {
        raiz = null;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        JFrame frame = new JFrame("Encuestas Diarias - Universidad de Caldas");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel etiquetaID = new JLabel("ID Estudiante:");
        JTextField campoID = new JTextField(10);
        JLabel etiquetaEstado = new JLabel("Estado de Ánimo (1-10):");
        JTextField campoEstado = new JTextField(5);
        JCheckBox checkAsistencia = new JCheckBox("Asistió");

        JLabel etiquetaFecha = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField campoFecha = new JTextField(10);

        JButton botonRegistrar = new JButton("Registrar Encuesta");
        JButton botonPromedio = new JButton("Mostrar Promedio");
        JButton botonAsistencia = new JButton("Ver Asistencia");
        JButton botonPromedioGeneral = new JButton("Promedio General");
        JButton botonEliminarEstado = new JButton("Eliminar Estado/Asistencia");

        JTextArea areaResultados = new JTextArea(12, 60);
        areaResultados.setEditable(false);

        botonRegistrar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoID.getText());
                int estado = Integer.parseInt(campoEstado.getText());
                boolean asistio = checkAsistencia.isSelected();
                LocalDate fecha = LocalDate.parse(campoFecha.getText(), DateTimeFormatter.ISO_DATE);

                if (estado < 1 || estado > 10) {
                    areaResultados.setText("Por favor ingresa un estado de ánimo entre 1 y 10.");
                    return;
                }

                NodoEstudiante estudiante = buscarOAgregarEstudiante(id);
                estudiante.registrarEncuesta(fecha, estado, asistio);

                double promedio = estudiante.calcularPromedioEstado();
                if (promedio < 3) {
                    areaResultados.setText("¡Alerta! El estudiante " + id +
                            " debe ser remitido a Bienestar Universitario.\nPromedio: " + promedio);
                } else {
                    areaResultados.setText("Registro exitoso. Promedio actual: " + promedio);
                }
            } catch (Exception ex) {
                areaResultados.setText("Por favor ingresa valores válidos. Asegúrate de que la fecha esté en formato YYYY-MM-DD.");
            }
        });

        botonPromedio.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoID.getText());
                NodoEstudiante estudiante = buscarEstudiante(id);
                if (estudiante == null) {
                    areaResultados.setText("No se encontró un estudiante con el ID " + id);
                    return;
                }
                double promedio = estudiante.calcularPromedioEstado();
                areaResultados.setText("Promedio del estudiante " + id + ": " + promedio);
            } catch (NumberFormatException ex) {
                areaResultados.setText("Por favor ingresa un ID válido.");
            }
        });

        botonAsistencia.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoID.getText());
                NodoEstudiante estudiante = buscarEstudiante(id);
                if (estudiante == null) {
                    areaResultados.setText("No se encontró un estudiante con el ID " + id);
                    return;
                }
                areaResultados.setText("Asistencia del estudiante " + id + ":\n" + estudiante.getRegistroAsistencia());
            } catch (NumberFormatException ex) {
                areaResultados.setText("Por favor ingresa un ID válido.");
            }
        });

        botonPromedioGeneral.addActionListener(e -> {
            double sumaPromedios = calcularPromedioGeneral();
            int totalEstudiantes = contarEstudiantes();
            if (totalEstudiantes == 0) {
                areaResultados.setText("No hay estudiantes registrados.");
            } else {
                areaResultados.setText("Promedio general de estado de ánimo: " + (sumaPromedios / totalEstudiantes));
            }
        });

        botonEliminarEstado.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoID.getText());
                LocalDate fecha = LocalDate.parse(campoFecha.getText(), DateTimeFormatter.ISO_DATE);

                NodoEstudiante estudiante = buscarEstudiante(id);
                if (estudiante == null) {
                    areaResultados.setText("No se encontró un estudiante con el ID " + id);
                    return;
                }

                boolean eliminado = estudiante.eliminarEstadoAsistencia(fecha);
                if (eliminado) {
                    areaResultados.setText("Estado de ánimo y asistencia eliminados para la fecha " + fecha +
                            " del estudiante " + id);
                } else {
                    areaResultados.setText("No se pudo eliminar. Fecha inválida o datos inexistentes.");
                }
            } catch (Exception ex) {
                areaResultados.setText("Por favor ingresa valores válidos. Asegúrate de que la fecha esté en formato YYYY-MM-DD.");
            }
        });

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(etiquetaID);
        panelSuperior.add(campoID);
        panelSuperior.add(etiquetaEstado);
        panelSuperior.add(campoEstado);
        panelSuperior.add(checkAsistencia);
        panelSuperior.add(etiquetaFecha);
        panelSuperior.add(campoFecha);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonPromedio);
        panelBotones.add(botonAsistencia);
        panelBotones.add(botonPromedioGeneral);
        panelBotones.add(botonEliminarEstado);

        JPanel panelResultados = new JPanel();
        panelResultados.add(new JScrollPane(areaResultados));

        frame.add(panelSuperior, "North");
        frame.add(panelBotones, "Center");
        frame.add(panelResultados, "South");

        frame.setVisible(true);
    }

    private NodoEstudiante buscarOAgregarEstudiante(int id) {
        NodoEstudiante actual = raiz, padre = null;
        while (actual != null) {
            padre = actual;
            if (id < actual.id) {
                actual = actual.izquierda;
            } else if (id > actual.id) {
                actual = actual.derecha;
            } else {
                return actual;
            }
        }

        NodoEstudiante nuevo = new NodoEstudiante(id);
        if (padre == null) {
            raiz = nuevo;
        } else if (id < padre.id) {
            padre.izquierda = nuevo;
        } else {
            padre.derecha = nuevo;
        }

        return nuevo;
    }

    private NodoEstudiante buscarEstudiante(int id) {
        NodoEstudiante actual = raiz;
        while (actual != null) {
            if (id < actual.id) {
                actual = actual.izquierda;
            } else if (id > actual.id) {
                actual = actual.derecha;
            } else {
                return actual;
            }
        }
        return null;
    }

    private double calcularPromedioGeneral() {
        return calcularSumaPromedios(raiz);
    }

    private double calcularSumaPromedios(NodoEstudiante nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.calcularPromedioEstado() +
                calcularSumaPromedios(nodo.izquierda) +
                calcularSumaPromedios(nodo.derecha);
    }

    private int contarEstudiantes() {
        return contarNodos(raiz);
    }

    private int contarNodos(NodoEstudiante nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.izquierda) + contarNodos(nodo.derecha);
    }

    public static void main(String[] args) {
        new EncuestasEstudiantes();
    }
}

class NodoEstudiante {
    int id;
    HashMap<LocalDate, EstadoAsistencia> datos;
    NodoEstudiante izquierda, derecha;

    public NodoEstudiante(int id) {
        this.id = id;
        this.datos = new HashMap<>();
    }

    public void registrarEncuesta(LocalDate fecha, int estado, boolean asistio) {
        datos.put(fecha, new EstadoAsistencia(estado, asistio));
    }

    public double calcularPromedioEstado() {
        return datos.values().stream().mapToInt(e -> e.estado).average().orElse(0.0);
    }

    public String getRegistroAsistencia() {
        StringBuilder registro = new StringBuilder();
        datos.forEach((fecha, estadoAsistencia) ->
                registro.append(fecha).append(": Estado de ánimo ").append(estadoAsistencia.estado)
                        .append(", ").append(estadoAsistencia.asistio ? "Asistió" : "No asistió").append("\n"));
        return registro.toString();
    }

    public boolean eliminarEstadoAsistencia(LocalDate fecha) {
        return datos.remove(fecha) != null;
    }
}

class EstadoAsistencia {
    int estado;
    boolean asistio;

    public EstadoAsistencia(int estado, boolean asistio) {
        this.estado = estado;
        this.asistio = asistio;
    }
}
