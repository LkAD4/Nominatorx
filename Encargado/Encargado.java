package Encargado;
import java.util.Scanner;


public class Encargado implements operations {
    Scanner input = new Scanner(System.in);
    String nombre;
    String apellido;
    int edad;
    String id;
    List_fijas listas;
    

    



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
    public int Calcular_N(int horas,int salario,int bono,int pension){
         int salarioTotal = ((salario  * (horas / 8))+ bono + pension);
         return salarioTotal;


    }

  }


    

    
