package Encargado;
import java.util.Scanner;


public class Encargado extends operations {

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

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getId() {
        return id;
    }


    @Override
    public String capitalize(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();


    }

    public String nombreCompleto() {
        return nombre + " " + apellido;
    }
}



  


    

    
