import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/* Clase padre aqui van a estar los datos
 * de estudiantes, docentes y personal administrativo.
 */
public class PersonaUniversitaria {

    // Encapsulamiento: el identificador solo se consulta o modifica con métodos.
    private int id;

    // protected: las clases hijas pueden acceder directamente a estos atributos.
    protected String nombre;

    // public: se deja público para ejemplificar el modificador de acceso.
    // En un sistema real sería preferible mantenerlo private y usar getter/setter.
    public String universidad;

    // Pares de tipos primitivos y sus clases envolventes (Wrapper).
    protected int edadPrimitiva;
    protected Integer edadObjeto;

    protected double estaturaPrimitiva;
    protected Double estaturaObjeto;

    protected boolean activoPrimitivo;
    protected Boolean activoObjeto;

    protected char inicialPrimitiva;
    protected Character inicialObjeto;

    protected long numeroPrimitivo;
    protected Long numeroObjeto;

    protected float promedioPrimitivo;
    protected Float promedioObjeto;

    protected byte nivelPrimitivo;
    protected Byte nivelObjeto;

    protected short semestrePrimitivo;
    protected Short semestreObjeto;

    //  objeto: fecha de registro.
    protected LocalDate fechaRegistro;

    // Se inicializan arreglos y variables para que estén para usarse.
    protected ArrayList<String> materias = new ArrayList<>();
    protected LinkedList<String> historialPersonal = new LinkedList<>();
    protected HashSet<String> documentos = new HashSet<>();
    protected HashMap<String, String> datosAdicionales = new HashMap<>();

    /*Constructor de la clase padre. */
    public PersonaUniversitaria(int id, String nombre, int edad, String universidad) {
        setId(id);
        setNombre(nombre);
        setEdad(edad);
        setUniversidad(universidad);

        // Valores iniciales ilustrativos para los pares primitivo/Wrapper.
        this.estaturaPrimitiva = 1.70;
        this.estaturaObjeto = Double.valueOf(estaturaPrimitiva);
        this.activoPrimitivo = true;
        this.activoObjeto = Boolean.valueOf(activoPrimitivo);
        this.inicialPrimitiva = this.nombre.charAt(0);
        this.inicialObjeto = Character.valueOf(inicialPrimitiva);
        this.numeroPrimitivo = id;
        this.numeroObjeto = Long.valueOf(numeroPrimitivo);
        this.promedioPrimitivo = 0.0f;
        this.promedioObjeto = Float.valueOf(promedioPrimitivo);
        this.nivelPrimitivo = 1;
        this.nivelObjeto = Byte.valueOf(nivelPrimitivo);
        this.semestrePrimitivo = 1;
        this.semestreObjeto = Short.valueOf(semestrePrimitivo);
        this.fechaRegistro = LocalDate.now();

        // Ejemplos de uso de las colecciones que pertenecen a cada persona.
        materias.add("Materia por asignar");
        historialPersonal.add("Registro creado: " + fechaRegistro);
        documentos.add("Identificación oficial");
        datosAdicionales.put("Institución", universidad);
    }

    // Getters y setters: controlan el acceso a los atributos privados/protegidos.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser mayor que cero.");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
        this.inicialPrimitiva = this.nombre.charAt(0);
        this.inicialObjeto = Character.valueOf(inicialPrimitiva);
    }

    public int getEdad() {
        return edadPrimitiva;
    }

    public Integer getEdadObjeto() {
        return edadObjeto;
    }

    public void setEdad(int edad) {
        if (edad <= 0 || edad > 120) {
            throw new IllegalArgumentException("La edad debe estar entre 1 y 120 años.");
        }
        this.edadPrimitiva = edad;
        this.edadObjeto = Integer.valueOf(edad);
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        if (universidad == null || universidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La universidad no puede estar vacía.");
        }
        this.universidad = universidad.trim();
        this.datosAdicionales.put("Institución", this.universidad);
    }

    public double getEstaturaPrimitiva() {
        return estaturaPrimitiva;
    }

    public Double getEstaturaObjeto() {
        return estaturaObjeto;
    }

    public void setEstatura(double estatura) {
        if (estatura <= 0) {
            throw new IllegalArgumentException("La estatura debe ser mayor que cero.");
        }
        this.estaturaPrimitiva = estatura;
        this.estaturaObjeto = Double.valueOf(estatura);
    }

    public boolean isActivo() {
        return activoPrimitivo;
    }

    public Boolean getActivoObjeto() {
        return activoObjeto;
    }

    public void setActivo(boolean activo) {
        this.activoPrimitivo = activo;
        this.activoObjeto = Boolean.valueOf(activo);
    }

    public char getInicialPrimitiva() {
        return inicialPrimitiva;
    }

    public Character getInicialObjeto() {
        return inicialObjeto;
    }

    public long getNumeroPrimitivo() {
        return numeroPrimitivo;
    }

    public Long getNumeroObjeto() {
        return numeroObjeto;
    }

    public float getPromedioPrimitivo() {
        return promedioPrimitivo;
    }

    public Float getPromedioObjeto() {
        return promedioObjeto;
    }

    public void setPromedio(float promedio) {
        if (promedio < 0 || promedio > 10) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 10.");
        }
        this.promedioPrimitivo = promedio;
        this.promedioObjeto = Float.valueOf(promedio);
    }

    public byte getNivelPrimitivo() {
        return nivelPrimitivo;
    }

    public Byte getNivelObjeto() {
        return nivelObjeto;
    }

    public short getSemestrePrimitivo() {
        return semestrePrimitivo;
    }

    public Short getSemestreObjeto() {
        return semestreObjeto;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /* Metodo común. Las clases hijas lo sobrescriben con @Override,
     * lo que permite demostrar polimorfismo en tiempo de ejecución.
     */
    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edadObjeto);
        System.out.println("Universidad: " + universidad);
    }

    /** Agrega un documento; HashSet evita documentos repetidos. */
    public void agregarDocumento(String documento) {
        if (documento != null && !documento.trim().isEmpty()) {
            documentos.add(documento.trim());
        }
    }

    public void mostrarDocumentos() {
        System.out.println("Documentos: " + documentos);
    }

    /** Agrega una anotación al historial personal. */
    public void agregarMovimientoPersonal(String movimiento) {
        historialPersonal.add(movimiento);
    }
}
