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

    @Override
    public double benSocial(){
        return 0.02;  // Beneficio social: 2%
    }

    @Override
    public double pagoVac(){
        return 0.04;  // Pago de vacaciones: 4%
    }

    @Override
    public double Salud(){
        return 0.05;  // Aporte a salud: 5%
    }

    @Override
    public double getAP(){
        return 0.03;  // Aporte a pensión: 3%
    }
}
