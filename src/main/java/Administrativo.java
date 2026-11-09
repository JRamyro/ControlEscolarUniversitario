/* Clase hija que para al personal administrativo*/
public class Administrativo extends PersonaUniversitaria {

    private String puesto;
    private String areaTrabajo;

    public Administrativo(int id, String nombre, int edad, String universidad,
                          String puesto, String areaTrabajo) {
        super(id, nombre, edad, universidad);
        setPuesto(puesto);
        setAreaTrabajo(areaTrabajo);
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        if (puesto == null || puesto.trim().isEmpty()) {
            throw new IllegalArgumentException("El puesto no puede estar vacío.");
        }
        this.puesto = puesto.trim();
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        if (areaTrabajo == null || areaTrabajo.trim().isEmpty()) {
            throw new IllegalArgumentException("El área de trabajo no puede estar vacía.");
        }
        this.areaTrabajo = areaTrabajo.trim();
    }

    /*Sobrescribe el metodo de la clase padre. */
    @Override
    public void mostrarInformacion() {
        System.out.println("--- PERSONAL ADMINISTRATIVO ---");
        super.mostrarInformacion();
        System.out.println("Puesto: " + puesto);
        System.out.println("Área de trabajo: " + areaTrabajo);
    }
}
