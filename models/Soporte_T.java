package models;

public class Soporte_T extends Trabajador {
      public Soporte_T(String t_Nombre, String t_Apellido, String cedula, int edad, String departamento, String cargo) {
        super(t_Nombre, t_Apellido, cedula, edad,departamento,cargo);
        
    }

    @Override
       public int getBono(){
        return 30;
    }
     @Override
    public int getPension(){
        if (edad > 60){
            return 125;
        
        }
        else {
            
            return 0;
        }
}
}
