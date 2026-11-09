/* Clase hija de los datos a un estudiante */
public class Estudiante extends PersonaUniversitaria {

    private String carrera;
    private int creditos;

    public Estudiante(int id, String nombre, int edad, String universidad,
                      String carrera, int creditos) {
        super(id, nombre, edad, universidad); // Inicializa la parte heredada.
        setCarrera(carrera);
        setCreditos(creditos);
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        if (carrera == null || carrera.trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera no puede estar vacía.");
        }
        this.carrera = carrera.trim();
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        if (creditos < 0) {
            throw new IllegalArgumentException("Los créditos no pueden ser negativos.");
        }
        this.creditos = creditos;
    }

    /* Actualiza el metodo de la clase padre */
    @Override
    public void mostrarInformacion() {
        System.out.println("--- ESTUDIANTE ---");
        super.mostrarInformacion();
        System.out.println("Carrera: " + carrera);
        System.out.println("Créditos: " + creditos);
    }
}
