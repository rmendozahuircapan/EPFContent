
package herramienta;

import java.util.*;

public class Task {
    private String name;
    private String nombre;
    private String descripcion;
    private String id;
    private ArrayList<String> id_realizadores;
    private ArrayList<String> id_colaboradores;
    private ArrayList<String> id_input;
    private ArrayList<String> id_output;

    public Task(String name, String nombre, String descripcion, String id, ArrayList<String> id_realizadores, ArrayList<String> id_colaboradores, ArrayList<String> id_input, ArrayList<String> id_output) {
        this.name = name;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
        this.id_realizadores = id_realizadores;
        this.id_colaboradores = id_colaboradores;
        this.id_input = id_input;
        this.id_output = id_output;
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

    public ArrayList<String> getId_realizadores() {
        return id_realizadores;
    }

    public void setId_realizadores(ArrayList<String> id_realizadores) {
        this.id_realizadores = id_realizadores;
    }

    public ArrayList<String> getId_colaboradores() {
        return id_colaboradores;
    }

    public void setId_colaboradores(ArrayList<String> id_colaboradores) {
        this.id_colaboradores = id_colaboradores;
    }

    public ArrayList<String> getId_input() {
        return id_input;
    }

    public void setId_input(ArrayList<String> id_input) {
        this.id_input = id_input;
    }

    public ArrayList<String> getId_output() {
        return id_output;
    }

    public void setId_output(ArrayList<String> id_output) {
        this.id_output = id_output;
    }
    
    
    
    
    
    
}
