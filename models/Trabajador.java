package models;

public class Trabajador {
    String t_Nombre;
    String t_Apellido;
    private String cedula;
    String cargo;
    int bono;
    String departamento;
    int edad;

    public Trabajador(String t_nombre, String t_apellido, String cedula,String Departamento, int edad, String cargo) {
        this.t_Nombre = t_nombre;
        this.t_Apellido = t_apellido;
        this.cedula = cedula;
        this.cargo = cargo;
        this.edad = edad;
        
       
    }
    
    public String getT_nombre() {
        return t_Nombre;
    }
    public String getT_apellido() {
        return t_Apellido;
    }

    public int getEdad() {
        return edad;
    }


    public String getCargo() {
        return cargo;
    }

    public int getBono(){
        return 0 ;
    }

        
    public String getCedula() {
        return cedula;}
   public String nombreCompleto() {
        return t_Nombre + " " + t_Apellido;

    
    }
    

    
    }

    
