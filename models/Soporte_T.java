package models;

public class Soporte_T extends Trabajador {
      public Soporte_T(String t_nombre, String t_apellido, String cedula, String departamento, int edad, String cargo) {
        super(t_nombre, t_apellido, cedula, departamento, edad,cargo);
        
    }

    @Override
       public int getBono(){
        return 30;
    }
    
}
