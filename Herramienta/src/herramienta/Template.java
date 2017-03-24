
package herramienta;

public class Template {
    
    private String name;
    private String nombre;
    private String id;
    private String descripcion;

    public Template(String name, String nombre, String id, String descripcion) {
        this.name = name;
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}