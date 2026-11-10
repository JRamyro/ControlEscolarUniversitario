import java.util.ArrayList;
import java.util.Scanner;

/* Muestra el menú, para la elección del usuario*/
public class Main {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final ControlEscolar SISTEMA = new ControlEscolar();

    // Códigos ANSI para dar color en la consola
    private static final String AMARILLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ", 0, 6);

            switch (opcion) {
                case 1 -> alta();
                case 2 -> consultar();
                case 3 -> modificar();
                case 4 -> baja();
                case 5 -> listarTodos();
                case 6 -> mostrarHistorial();
                case 0 -> System.out.println("Sesión finalizada. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        ENTRADA.close();
    }

    /* Menu de opciones disponibles */
    private static void mostrarMenu() {
        System.out.println(AMARILLO + "\n========================================");
        System.out.println("      SISTEMA DE CONTROL ESCOLAR");
        System.out.println("      CREADO PARA LA ASIGNATURA DE ESTRUCTURA DE DATOS");
        System.out.println("      ESTUDIANTE JOSE RAMIRO ALVAREZ FLORES");
        System.out.println("      MATRICULA BM26520446");
        System.out.println("      DOCENTE ARTURO CABALLERO ALTAMIRANO");
        System.out.println("========================================");
        System.out.println("1. Dar de alta una persona");
        System.out.println("2. Consultar por identificador");
        System.out.println("3. Modificar una persona");
        System.out.println("4. Dar de baja una persona");
        System.out.println("5. Listar toda la comunidad");
        System.out.println("6. Consultar historial de operaciones");
        System.out.println("0. Salir" + RESET);
    }

    /* Solicita la opción a realizar y datos necesarios para crear una clase hija */
    private static void alta() {
        System.out.println(AMARILLO + "\n--- ALTA DE PERSONA ---" + RESET);
        System.out.println("1. Estudiante");
        System.out.println("2. Docente");
        System.out.println("3. Administrativo");
        int tipo = leerEntero("Tipo de persona: ", 1, 3);

        int id = leerEntero("Identificador (entero positivo): ", 1, Integer.MAX_VALUE);
        if (SISTEMA.buscarPorId(id) != null) {
            System.out.println("No se realizó el alta: el identificador ya está registrado.");
            return;
        }

        String nombre = leerTexto("Nombre completo: ");
        int edad = leerEntero("Edad (1 a 120): ", 1, 120);
        String universidad = leerTexto("Universidad: ");
        PersonaUniversitaria nuevaPersona;

        try {
            switch (tipo) {
                case 1 -> {
                    String carrera = leerTexto("Carrera: ");
                    int creditos = leerEntero("Créditos acumulados: ", 0, Integer.MAX_VALUE);
                    nuevaPersona = new Estudiante(id, nombre, edad, universidad, carrera, creditos);
                }
                case 2 -> {
                    String especialidad = leerTexto("Especialidad: ");
                    String departamento = leerTexto("Departamento: ");
                    nuevaPersona = new Docente(id, nombre, edad, universidad,
                            especialidad, departamento);
                }
                case 3 -> {
                    String puesto = leerTexto("Puesto: ");
                    String area = leerTexto("Área de trabajo: ");
                    nuevaPersona = new Administrativo(id, nombre, edad, universidad,
                            puesto, area);
                }
                default -> throw new IllegalStateException("Tipo inesperado.");
            }

            if (SISTEMA.registrar(nuevaPersona)) {
                System.out.println("Alta realizada correctamente.");
            } else {
                System.out.println("No se realizó el alta. Verifica el identificador.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("No se pudo registrar: " + ex.getMessage());
        }
    }

    /* Se realiza la Consulta de un registro y ejecuta su versión polimórfica del metodo */
    private static void consultar() {
        System.out.println(AMARILLO + "\n--- CONSULTA ---" + RESET);
        int id = leerEntero("Identificador que deseas consultar: ", 1, Integer.MAX_VALUE);
        PersonaUniversitaria persona = SISTEMA.buscarPorId(id);

        if (persona == null) {
            System.out.println("No se encontró una persona con ese identificador.");
        } else {
            persona.mostrarInformacion();
            persona.mostrarDocumentos();
        }
    }

    /* Se modifican los datos generales y los atributos particulares de cada clase hija */
    private static void modificar() {
        System.out.println(AMARILLO + "\n--- MODIFICACIÓN ---" + RESET);
        int id = leerEntero("Identificador que deseas modificar: ", 1, Integer.MAX_VALUE);
        PersonaUniversitaria persona = SISTEMA.buscarPorId(id);

        if (persona == null) {
            System.out.println("No se encontró el registro.");
            return;
        }

        System.out.println("Registro actual:");
        persona.mostrarInformacion();
        System.out.println("Captura los nuevos datos generales.");

        String nombre = leerTexto("Nuevo nombre: ");
        int edad = leerEntero("Nueva edad (1 a 120): ", 1, 120);
        String universidad = leerTexto("Nueva universidad: ");

        try {
            SISTEMA.actualizarDatosGenerales(id, nombre, edad, universidad);

            if (persona instanceof Estudiante estudiante) {
                estudiante.setCarrera(leerTexto("Nueva carrera: "));
                estudiante.setCreditos(leerEntero("Nuevos créditos: ", 0, Integer.MAX_VALUE));
                SISTEMA.registrarModificacionEspecifica(id, "Datos académicos actualizados");
            } else if (persona instanceof Docente docente) {
                docente.setEspecialidad(leerTexto("Nueva especialidad: "));
                docente.setDepartamento(leerTexto("Nuevo departamento: "));
                SISTEMA.registrarModificacionEspecifica(id, "Datos docentes actualizados");
            } else if (persona instanceof Administrativo administrativo) {
                administrativo.setPuesto(leerTexto("Nuevo puesto: "));
                administrativo.setAreaTrabajo(leerTexto("Nueva área de trabajo: "));
                SISTEMA.registrarModificacionEspecifica(id, "Datos administrativos actualizados");
            }

            System.out.println("Modificación realizada correctamente.");
        } catch (IllegalArgumentException ex) {
            System.out.println("No se pudo completar la modificación: " + ex.getMessage());
            System.out.println("Si ya se actualizaron datos generales, consulta el registro para verificarlo.");
        }
    }

    /* Antes de eliminar un registro, se solicita confirmación*/
    private static void baja() {
        System.out.println(AMARILLO + "\n--- BAJA ---" + RESET);
        int id = leerEntero("Identificador que deseas dar de baja: ", 1, Integer.MAX_VALUE);
        PersonaUniversitaria persona = SISTEMA.buscarPorId(id);

        if (persona == null) {
            System.out.println("No se encontró el registro.");
            return;
        }

        persona.mostrarInformacion();
        String confirmacion = leerTexto("¿Confirmas la baja? (S/N): ");
        if (confirmacion.equalsIgnoreCase("S")) {
            if (SISTEMA.darDeBaja(id)) {
                System.out.println("Baja realizada correctamente.");
            }
        } else {
            System.out.println("Operación cancelada. No se eliminó el registro.");
        }
    }

    /* Recorre la colección padre; cada objeto ejecuta su método sobrescrito */
    private static void listarTodos() {
        System.out.println(AMARILLO + "\n--- COMUNIDAD UNIVERSITARIA ---" + RESET);
        ArrayList<PersonaUniversitaria> personas = SISTEMA.obtenerTodos();

        if (personas.isEmpty()) {
            System.out.println("Todavía no hay personas registradas.");
            return;
        }

        for (PersonaUniversitaria persona : personas) {
            persona.mostrarInformacion();
            System.out.println("----------------------------------------");
        }
    }

    /* Muestra las altas, modificaciones y bajas realizadas */
    private static void mostrarHistorial() {
        System.out.println(AMARILLO + "\n--- HISTORIAL DE OPERACIONES ---" + RESET);
        if (SISTEMA.obtenerHistorial().isEmpty()) {
            System.out.println("No hay operaciones registradas.");
            return;
        }

        for (String movimiento : SISTEMA.obtenerHistorial()) {
            System.out.println("- " + movimiento);
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = ENTRADA.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("La entrada no puede estar vacía. Intenta nuevamente.");
        }
    }

    private static int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String texto = ENTRADA.nextLine().trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Ingresa un número entre " + minimo + " y " + maximo + ".");
            } catch (NumberFormatException ex) {
                System.out.println("Entrada no válida. Debes escribir un número entero.");
            }
        }
    }
}

