package Encargado;
import java.util.Scanner;


public class Encargado implements operations {
    Scanner input = new Scanner(System.in);
    String nombre;
    String apellido;
    int edad;
    String id;
    
    

    



    public Encargado(String nombre, String apellido, String id, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.id = id;
    }

    String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public int getEdad() {
        return edad;
    }

    public String getId() {
        return id;
    }
    


    public static String capitalize(String texto) {
    if (texto == null || texto.isEmpty()) return texto;
    return texto.substring(0,1).toUpperCase() + texto.substring(1).toLowerCase();


  
}

    public String nombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public int Calcular_N(int horas, int salario, int bono, int pension, 
                      double benS, double vaca, double salud, double aporteP) {

    // 1. CALCULAR SALARIO BASE SEGÚN HORAS
    double salarioTotal = (salario * (horas / 8.0)) + bono + pension;

    // 2. SUMAS (%)
    salarioTotal += salario * (benS / 100);   // Beneficio social
    salarioTotal += salario * (vaca / 100);   // Vacaciones

    // 3. DESCUENTOS (%)
    salarioTotal -= salario * (salud / 100);  // Salud
    salarioTotal -= salario * (aporteP / 100); // Aporte pensión

    return (int) salarioTotal; // retorno final
}

    }

  


    

    
