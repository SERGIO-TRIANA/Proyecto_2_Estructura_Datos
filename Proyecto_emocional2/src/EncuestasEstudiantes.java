import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

// Clase principal
public class EncuestasEstudiantes {
    private NodoEstudiante raiz; // Árbol binario para almacenar estudiantes

    public EncuestasEstudiantes() {
        raiz = null;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        JFrame frame = new JFrame("Encuestas Diarias - Universidad de Caldas");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes
        JLabel etiquetaID = new JLabel("ID Estudiante:");
        JTextField campoID = new JTextField(10);
        JLabel etiquetaEstado = new JLabel("Estado de Ánimo (1-10):");
        JTextField campoEstado = new JTextField(5);
        JCheckBox checkAsistencia = new JCheckBox("Asistió");

        JButton botonRegistrar = new JButton("Registrar Encuesta");
        JButton botonPromedio = new JButton("Mostrar Promedio");
        JButton botonAsistencia = new JButton("Ver Asistencia");

        JTextArea areaResultados = new JTextArea(12, 50);
        areaResultados.setEditable(false);

        // Acción para registrar encuesta
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(campoID.getText());
                    int estado = Integer.parseInt(campoEstado.getText());
                    boolean asistio = checkAsistencia.isSelected();

                    if (estado < 1 || estado > 10) {
                        areaResultados.setText("Por favor ingresa un estado de ánimo entre 1 y 10.");
                        return;
                    }

                    NodoEstudiante estudiante = buscarOAgregarEstudiante(id);
                    estudiante.registrarEncuesta(estado, asistio);

                    double promedio = estudiante.calcularPromedioEstado();
                    if (promedio < 3) {
                        areaResultados.setText("¡Alerta! El estudiante " + id +
                                " debe ser remitido a Bienestar Universitario.\nPromedio: " + promedio);
                    } else {
                        areaResultados.setText("Registro exitoso. Promedio actual: " + promedio);
                    }
                } catch (NumberFormatException ex) {
                    areaResultados.setText("Por favor ingresa valores válidos.");
                }
            }
        });

        // Acción para mostrar promedio
        botonPromedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Acción para mostrar asistencia
        botonAsistencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Paneles de interfaz
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(etiquetaID);
        panelSuperior.add(campoID);
        panelSuperior.add(etiquetaEstado);
        panelSuperior.add(campoEstado);
        panelSuperior.add(checkAsistencia);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonRegistrar);
        panelBotones.add(botonPromedio);
        panelBotones.add(botonAsistencia);

        JPanel panelResultados = new JPanel();
        panelResultados.add(new JScrollPane(areaResultados));

        // Añadir paneles al frame
        frame.add(panelSuperior, "North");
        frame.add(panelBotones, "Center");
        frame.add(panelResultados, "South");

        frame.setVisible(true);
    }

    // Métodos del árbol binario
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

    public static void main(String[] args) {
        new EncuestasEstudiantes();
    }
}

// Clase para representar un estudiante
class NodoEstudiante {
    int id;
    LinkedList<Integer> estadosDeAnimo;
    LinkedList<Boolean> asistencia;
    NodoEstudiante izquierda, derecha;

    public NodoEstudiante(int id) {
        this.id = id;
        this.estadosDeAnimo = new LinkedList<>();
        this.asistencia = new LinkedList<>();
        this.izquierda = this.derecha = null;
    }

    public void registrarEncuesta(int estado, boolean asistio) {
        if (estadosDeAnimo.size() == 5) {
            estadosDeAnimo.poll();
            asistencia.poll();
        }
        estadosDeAnimo.add(estado);
        asistencia.add(asistio);
    }

    public double calcularPromedioEstado() {
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
}
