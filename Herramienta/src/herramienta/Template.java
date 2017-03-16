
package herramienta;

public class Template {
    
    String name;
    String nombre;
    String id;

    public Template(String name, String nombre, String id) {
        this.name = name;
        this.nombre = nombre;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
