import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/*Clase de servicio: concentra las operaciones de altas, consultas,
 * modificaciones y bajas
 */
public class ControlEscolar {

    // ArrayList: la lista general y permite recorrer los registros.
    private final ArrayList<PersonaUniversitaria> comunidad = new ArrayList<>();

    // LinkedList: los movimientos realizados en el sistema.
    private final LinkedList<String> historial = new LinkedList<>();

    // HashSet: comprueba si un ID ya fue registrado.
    private final HashSet<Integer> identificadores = new HashSet<>();

    // HashMap: localiza una persona por su ID.
    private final HashMap<Integer, PersonaUniversitaria> indicePorId = new HashMap<>();

    /*Registra una persona si el identificador no existe. */
    public boolean registrar(PersonaUniversitaria persona) {
        if (persona == null || identificadores.contains(persona.getId())) {
            return false;
        }

        comunidad.add(persona);
        identificadores.add(persona.getId());
        indicePorId.put(persona.getId(), persona);
        historial.add("Alta: " + tipoDe(persona) + " | ID " + persona.getId()
                + " | " + persona.getNombre());
        persona.agregarMovimientoPersonal("Alta en Control Escolar");
        return true;
    }

    /* Busca una persona por su identificador */
    public PersonaUniversitaria buscarPorId(int id) {
        return indicePorId.get(id);
    }

    /* Devuelve una copia de la lista para no exponer la colección interna */
    public ArrayList<PersonaUniversitaria> obtenerTodos() {
        return new ArrayList<>(comunidad);
    }

    /* Actualiza los datos generales de una persona ya registrada */
    public boolean actualizarDatosGenerales(int id, String nombre, int edad,
                                             String universidad) {
        PersonaUniversitaria persona = buscarPorId(id);
        if (persona == null) {
            return false;
        }

        persona.setNombre(nombre);
        persona.setEdad(edad);
        persona.setUniversidad(universidad);
        persona.agregarMovimientoPersonal("Modificación de datos generales");
        historial.add("Modificación de datos generales | ID " + id);
        return true;
    }

    /* Elimina el registro de las cuatro estructuras de datos */
    public boolean darDeBaja(int id) {
        PersonaUniversitaria persona = indicePorId.get(id);
        if (persona == null) {
            return false;
        }

        comunidad.remove(persona);
        identificadores.remove(id);
        indicePorId.remove(id);
        historial.add("Baja: " + tipoDe(persona) + " | ID " + id
                + " | " + persona.getNombre());
        return true;
    }

    /*Registra en el historial una modificación específica de la clase hija */
    public void registrarModificacionEspecifica(int id, String detalle) {
        historial.add("Modificación | ID " + id + " | " + detalle);
        PersonaUniversitaria persona = buscarPorId(id);
        if (persona != null) {
            persona.agregarMovimientoPersonal(detalle);
        }
    }

    public LinkedList<String> obtenerHistorial() {
        return new LinkedList<>(historial);
    }

    private String tipoDe(PersonaUniversitaria persona) {
        if (persona instanceof Estudiante) return "Estudiante";
        if (persona instanceof Docente) return "Docente";
        if (persona instanceof Administrativo) return "Administrativo";
        return "Persona universitaria";
    }
}
