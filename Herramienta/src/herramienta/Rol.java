
package herramienta;


public class Rol {
    private String name;
    private String nombre;
    private String descripcion;
    private String id;
    
    
    public Rol(String name, String nombre, String descripcion, String id) {
        this.name = name;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
