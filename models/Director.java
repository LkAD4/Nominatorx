package models;

public class Director extends Trabajador {
    
    public Director(String t_nombre, String t_apellido, String cedula, String departamento, int edad, String cargo) {
        super(t_nombre, t_apellido, cedula, departamento, edad,"Director");
        
    }

    @Override
       public int getBono(){
        return 50;
    }

}