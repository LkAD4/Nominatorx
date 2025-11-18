package models;


public class Departament {
    private int salarioDia;//Salario total por dia
    private String d_Nombre;
    private int h_Totales;//h totales trabajadas en el mes
    
    
    public Departament(int salarioDiA, String d_nombre, int h_totales ) {
        this.salarioDia = salarioDiA;
        this.d_Nombre = d_nombre;
        this.h_Totales = h_totales;
        
    }

    public String getNombre() {
        return d_Nombre;
    }

    public int getSalario() {
        return salarioDia;
    }

    public int geth_Totales() {
        return h_Totales;
    }

    
    
}