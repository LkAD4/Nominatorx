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
    @Override
    public double benSocial(){
        return 0.05;  // Beneficio social: 5%
    }
    @Override
    public double pagoVac(){
        return 0.05;  // Pago de vacaciones: 5%
    }
    @Override
    public double Salud(){
        return 0.04;  // Aporte a salud: 4%
    }
    @Override
    public double getAP(){
        return 0.03;  // Aporte a pensión: 3%
    }
}