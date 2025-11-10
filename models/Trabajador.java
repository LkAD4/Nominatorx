package models;

public class Trabajador extends Persona{
    
    String cargo;
  
    String departamento;
    

    public Trabajador(String t_Nombre, String t_Apellido, String cedula, int edad,
                      String departamento, String cargo) {
        super(t_Nombre, t_Apellido, cedula, edad);
        this.t_Nombre = t_Nombre;
        this.t_Apellido = t_Apellido;
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
    public int getPension(){
        if (edad > 60){
            return 100;
        
        }
        else {
            
            return 0;
        }

        
    }
    

    
    }

    
