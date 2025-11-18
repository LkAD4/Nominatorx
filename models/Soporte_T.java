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

    @Override
    public double benSocial(){
        return 0.03;  // Beneficio social: 3% (básico)
    }

    @Override
    public double pagoVac(){
        return 0.03;  // Pago de vacaciones: 3%
    }

    @Override
    public double Salud(){
        return 0.04;  // Aporte a salud: 4%
    }

    @Override
    public double getAP(){
        return 0.02;  // Aporte a pensión: 2%
    }
}
