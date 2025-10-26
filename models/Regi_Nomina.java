package models;
import java.util.ArrayList;

public class Regi_Nomina {
    private final Trabajador datos_Trabajador;
    private final Informe informe; // Referencia al informe (composición)
    ArrayList<String> list_datos = new ArrayList<>();

    public Regi_Nomina(Trabajador datos_Trabajador, Informe informe) {
        this.datos_Trabajador = datos_Trabajador;
        this.informe = informe; // Guardamos referencia al informe
        if (this.datos_Trabajador != null) {
            list_datos.add(datos_Trabajador.nombreCompleto());
            list_datos.add(datos_Trabajador.getCedula());
            list_datos.add(String.valueOf(datos_Trabajador.getEdad()));
            //departamento
           
            
        } else {
            System.out.println("No hay datos del trabajador.");
        
        }
    }
    
    
    }
