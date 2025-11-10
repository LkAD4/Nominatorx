package models;

public class Ejecutivo extends Trabajador {
      public Ejecutivo(String t_Nombre, String t_Apellido, String cedula, int edad, String departamento, String cargo) {
        super(t_Nombre, t_Apellido, cedula, edad,departamento,cargo);
        
    }

    @Override
       public int getBono(){
        return 45;
    }
    @Override
    public int getPension(){
        if (edad > 60){
            return 150;
        
        }
        else {
            
            return 0;
        }
}
}
