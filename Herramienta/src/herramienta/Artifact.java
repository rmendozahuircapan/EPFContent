
package herramienta;
        
public class Artifact {
    private String name;
    private String nombre;
    private String descripcion;
    private String id;
    private String id_template;

    public Artifact(String name, String nombre, String descripcion, String id, String id_template) {
        this.name = name;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
        this.id_template = id_template;
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

    public String getId_template() {
        return id_template;
    }

    public void setId_template(String id_template) {
        this.id_template = id_template;
    }
    
    
}
