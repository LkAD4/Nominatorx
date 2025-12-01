package models;

public class Director extends  Trabajador implements HorasExtra {
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

    @Override
    public double benSocial(){
        return 0.06;  // Beneficio social: 6% (máximo para directores)
    }

    @Override
    public double pagoVac(){
        return 0.06;  // Pago de vacaciones: 6% (premium para directores)
    }

    @Override
    public double Salud(){
        return 0.05;  // Aporte a salud: 5% (empresa cubre más)
    }

    @Override
    public double getAP(){
        return 0.04;  // Aporte a pensión: 4% (máximo para directores)
    }

    @Override
    public int getHoras_Ext() {
        return 32;
    }
}

