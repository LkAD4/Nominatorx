package models;

public class Informe {//clase para tener un informe maslimpio al momento de hacer el GUI
    private final Regi_Nomina nomina;

    public Informe(Trabajador trabajador) {
        // El informe crea y gestiona su propio Regi_Nomina (composición)
        this.nomina = new Regi_Nomina(trabajador, this);
        
    }

    public String crearInforme(Trabajador trabajador) {
        StringBuilder informeCompleto = new StringBuilder();
       informeCompleto.append("== INFORME DE NOMINA ==\n");
    informeCompleto.append("Datos del trabajador:\n");
    informeCompleto.append("======================\n");

    // Asumiendo que 'nomina' es una variable de clase inicializada
    // y que 'List_datos' es List<String>
    
    // Manejo del caso sin datos
    if (this.nomina == null || this.nomina.list_datos.isEmpty()) {
        return "No hay datos de nómina para mostrar.";
    } else {
        String[] etiquetas = {"Nombre", "Cédula", "Edad", "Cargo", "Bono", "Pensión"};
        
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
    
    // ****************************************
    // * SOLUCIÓN AL ERROR DE COMPILACIÓN: *
    // * Devuelve la cadena FINAL que construiste *
    // ****************************************
    return informeCompleto.toString();
}
}
