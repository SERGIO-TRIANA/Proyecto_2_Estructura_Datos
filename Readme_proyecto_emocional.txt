Integrantes:
Sergio Andres Rodriguez Triana
Vanessa Miranda.

Video explicativo del programa proyecto emocional:
https://drive.google.com/file/d/14ZVzma3mfiUG9SoSpRIJCRuNDwuRZSJd/view?usp=drive_link

Descripción del Proyecto
El proyecto es un programa desarrollado en Java que permite gestionar encuestas diarias para estudiantes de la Universidad de Caldas, con el objetivo de registrar su estado de ánimo en una escala de 1 a 10 y controlar su asistencia a clases de lunes a viernes. Si el promedio de estado de ánimo de un estudiante en los últimos días es menor a 3, el programa genera una alerta indicando que el estudiante podría necesitar atención psicológica. Además, ofrece estadísticas generales y permite gestionar datos históricos.

El programa utiliza:

Java como lenguaje de programación.
Java Swing como framework para la interfaz gráfica de usuario (GUI).
Estructuras de datos no lineales, como un árbol binario de búsqueda para organizar los estudiantes.
Estructuras de datos lineales, como un HashMap, para almacenar estados de ánimo y asistencia asociados a fechas específicas.
Requisitos para la Instalación y Ejecución
Software necesario:

Java Development Kit (JDK) versión 8 o superior.
Un entorno de desarrollo integrado (IDE) como IntelliJ IDEA, Eclipse o NetBeans (opcional para desarrollo).
Sistema operativo compatible con Java (Windows, macOS, Linux).
Dependencias:

Ninguna dependencia externa adicional; el programa utiliza únicamente bibliotecas estándar de Java.
Instrucciones de Instalación y Ejecución
Clonar o descargar el proyecto:

desde el repositorio de Git, clónalo usando:
bash
Copy code
git clone <URL_DEL_REPOSITORIO>
Alternativamente, descarga el archivo ZIP del proyecto y extrae su contenido en tu máquina.
Abrir el proyecto:

Abre el proyecto en un IDE de tu preferencia.
Asegúrate de que el SDK de Java esté configurado correctamente en tu IDE.
Compilar el programa:

Si estás utilizando un IDE:
Haz clic en Run o Build para compilar el programa.
Si prefieres la línea de comandos:
Navega a la carpeta donde se encuentra el archivo EncuestasEstudiantes.java y ejecuta:
bash
Copy code
javac EncuestasEstudiantes.java
Ejecutar el programa:

Desde el IDE, presiona el botón Run.
Desde la línea de comandos, ejecuta:
bash
Copy code
java EncuestasEstudiantes
Descripción de las Funcionalidades
Registro de Encuestas:

Permite ingresar el ID de un estudiante, su estado de ánimo en una escala de 1 a 10 y marcar si asistió a clase.
Si el promedio de estado de ánimo de un estudiante en los últimos 5 días es menor a 3, genera una alerta que sugiere remitirlo a Bienestar Universitario.
Consulta de Promedio de Estado de Ánimo por Estudiante:

Permite ingresar el ID de un estudiante y consultar su promedio de estado de ánimo actual.
Visualización de Asistencia por Estudiante:

Permite consultar el registro histórico de asistencia de un estudiante, indicando los días específicos en los que asistió o no.
Cálculo de Estadísticas Generales:

Calcula el promedio general del estado de ánimo de todos los estudiantes registrados en el sistema.
Gestión de Datos por Fecha:

Permite eliminar un registro de estado de ánimo y asistencia asociado a una fecha específica.
Interfaz Gráfica:

La aplicación utiliza Java Swing para ofrecer una GUI interactiva con botones, campos de texto, y un área de resultados para mostrar mensajes, estadísticas o alertas.
Registrar Encuesta:

Introducir el ID del estudiante (número único).
Seleccionar una fecha específica (formato YYYY-MM-DD).
Ingresar el estado de ánimo (1-10) y marcar si asistió o no.
Hacer clic en el botón Registrar Encuesta.
Consultar Promedio de un Estudiante:

Introducir el ID del estudiante y hacer clic en Mostrar Promedio.
Visualizar Asistencia:

Introducir el ID del estudiante y hacer clic en Ver Asistencia.
Eliminar Registro:

Introducir el ID del estudiante, seleccionar una fecha y hacer clic en Eliminar Registro.
Ver Estadísticas Generales:

Hacer clic en Mostrar Estadísticas Generales para ver el promedio de estado de ánimo de todos los estudiantes.
Con esta aplicación, los docentes pueden llevar un control más efectivo del bienestar emocional y la asistencia de los estudiantes, permitiendo tomar acciones preventivas en casos críticos.