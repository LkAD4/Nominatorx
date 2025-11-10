package models;

public class Analista extends Trabajador {
      public Analista(String t_Nombre, String t_Apellido, String cedula, int edad, String departamento, String cargo) {
        super(t_Nombre, t_Apellido, cedula, edad,departamento,cargo);
        
    }

    @Override
       public int getBono(){
        return 40;
    }
     @Override
    public int getPension(){
        if (edad > 60){
            return 170;
        
        }
        else {
            
            return 0;
        }
}
}
