package models;

public class Director extends Trabajador {
    
    public Director(String t_Nombre, String t_Apellido, String cedula, int edad, String departamento, String cargo) {
        super(t_Nombre, t_Apellido, cedula, edad,departamento,cargo);
        
    }

    @Override
       public int getBono(){
        return 50;
    }
     @Override
    public int getPension(){
        if (edad > 60){
            return 180;
        
        }
        else {
            
            return 0;
        }
}

}