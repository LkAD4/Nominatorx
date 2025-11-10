package models;

public class Informe {//clase para tener un informe maslimpio al momento de hacer el GUI
    private final Regi_Nomina nomina;

    public Informe(Trabajador trabajador) {
        // El informe crea y gestiona su propio Regi_Nomina (composición)
        this.nomina = new Regi_Nomina(trabajador, this);
    }

    public void crearInforme() {
        System.out.println("INFORME DE NOMINA:");
        System.out.println("Datos del trabajador:");
        System.out.println("=====================");
        
        if (nomina.list_datos == null || nomina.list_datos.isEmpty()) {
        System.out.println("No hay datos para mostrar.");
        return;
    }

    String[] etiquetas = {"Nombre", "Cédula", "Edad", "Cargo", "Bono", "Pensión"};

    for (int i = 0; i < nomina.list_datos.size() && i < etiquetas.length; i++) {
        System.out.println(etiquetas[i] + ": " + nomina.list_datos.get(i));
    }

    System.out.println("=====================");
    }
    }
