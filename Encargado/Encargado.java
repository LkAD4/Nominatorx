package Encargado;

public class Encargado {
    String nombre;
    String apellido;
    int edad;
    String id;



    public Encargado(String nombre, String apellido, String id, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.id = id;
    }

    String getNombre() {
        return nombre;
    }
    String getApellido() {
        return apellido;
    }
    int getEdad() {
        return edad;
    }

    public String getId() {
        return id;
    }
    
    public String nombreCompleto() {
        return nombre + " " + apellido;
    }

    
}
