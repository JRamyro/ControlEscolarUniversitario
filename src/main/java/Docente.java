/* Clase hija que datos de un docente */
public class Docente extends PersonaUniversitaria {

    private String especialidad;
    private String departamento;

    public Docente(int id, String nombre, int edad, String universidad,
                   String especialidad, String departamento) {
        super(id, nombre, edad, universidad);
        setEspecialidad(especialidad);
        setDepartamento(departamento);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad.trim();
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("El departamento no puede estar vacío.");
        }
        this.departamento = departamento.trim();
    }

    /* Actualiza el metodo de la clase padre */
    @Override
    public void mostrarInformacion() {
        System.out.println("--- DOCENTE ---");
        super.mostrarInformacion();
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Departamento: " + departamento);
    }
}
