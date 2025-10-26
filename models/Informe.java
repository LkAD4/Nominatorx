package models;

public class Informe {
    private final Regi_Nomina nomina;

    public Informe(Trabajador trabajador) {
        // El informe crea y gestiona su propio Regi_Nomina (composición)
        this.nomina = new Regi_Nomina(trabajador, this);
    }

    public void crearInforme() {
        System.out.println("INFORME DE NOMINA:");
        System.out.println("Datos del trabajador:");
        System.out.println("=====================");
        
        for (int i = 0; i < nomina.list_datos.size();  ) {
            

             
            System.out.println("------------------");
            System.out.println("Nombre: " + nomina.list_datos.get(i));
            System.out.println("------------------");
            System.out.println("cedula: " + nomina.list_datos.get(i = 1));
            System.out.println("edad: " + nomina.list_datos.get(i = 2));
            System.out.println("------------------");
            System.out.println("=====================");
            i = 3;
        }
    }
}