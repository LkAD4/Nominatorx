package models;

public class Administrador extends Trabajador {
      public Administrador(String t_Nombre, String t_Apellido, String cedula, int edad, String departamento, String cargo) {
        super(t_Nombre, t_Apellido, cedula, edad,departamento,cargo);
        
    }

    @Override
       public int getBono(){
        return 48;
    }
     @Override
    public int getPension(){
        if (edad > 60){
            return 175;
        
        }
        else {
            
            return 0;
        }
}
}
