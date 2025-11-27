package models;

public class Informe {//clase para tener un informe maslimpio al momento de hacer el GUI
    private final Regi_Nomina nomina;
    private final Trabajador trabajador;

    public Informe(Trabajador trabajador) {
        // El informe crea y gestiona su propio Regi_Nomina (composición)
        this.trabajador = trabajador;
        this.nomina = new Regi_Nomina(trabajador);

    }

    public String crearInforme(Trabajador trabajador) {
        StringBuilder informeCompleto = new StringBuilder();
       
        informeCompleto.append("==  Nomina del mes ==\n");
        informeCompleto.append("======================\n");
        informeCompleto.append("Datos del trabajador:\n");
        informeCompleto.append("======================\n");

        
        
        // Manejo del caso sin datos
        if (this.nomina == null || this.nomina.list_datos.isEmpty()) {
            return "No hay datos de nómina para mostrar.";
        } else {
            String[] etiquetas = {"Nombre", "Cédula", "Edad", "Cargo", "Bono", "Pensión","Beneficio social","Vacaciones","Salud","Aporte pensión"};
            
            // **********************************************
            // * MODIFICACIÓN CLAVE: Acumular en lugar de retornar *
            // **********************************************
            for (int i = 0; i < this.nomina.list_datos.size() && i < etiquetas.length; i++) {
                // Se añaden los datos línea por línea al constructor de cadenas
                informeCompleto.append(etiquetas[i])
                            .append(": ")
                            .append(this.nomina.list_datos.get(i))
                            .append("\n"); // Agrega un salto de línea para el siguiente dato
            }
        }

        // Añade el pie de página
        informeCompleto.append("======================\n");
        
    
        return informeCompleto.toString();
    }

    public Trabajador getTrabajador() {
        return this.trabajador;
    }
    }
