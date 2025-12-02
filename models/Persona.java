package models;

public class Persona {
    public String t_Nombre;
    public String t_Apellido;
    public String cedula;
    public int edad;


public Persona(String t_Nombre, String t_Apellido, String cedula, int edad) {
        this.t_Nombre = t_Nombre;
        this.t_Apellido = t_Apellido;
        this.cedula = cedula;
        this.edad = edad;
    }
}
