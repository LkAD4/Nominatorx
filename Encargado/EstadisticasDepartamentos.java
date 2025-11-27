package Encargado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EstadisticasDepartamentos {

    private TreeMap<String, Integer> contadorDeptos = new TreeMap<>();

    public EstadisticasDepartamentos() {
            contadorDeptos.put("Sistemas", 0);
            contadorDeptos.put("Ventas", 0);
            contadorDeptos.put("RRHH", 0);
            contadorDeptos.put("Finanzas", 0);
            contadorDeptos.put("Marketing", 0);
    }

    public void aumentar(String departamento) {
        if(contadorDeptos.containsKey(departamento)) {
            contadorDeptos.put(departamento, contadorDeptos.get(departamento) + 1);
        }
    }

    public TreeMap<String, Integer> getContadores() {
        return contadorDeptos;
    }
    public static String generarVistaPrevia(
            String encargadoNombre,
            String notaAdicional,
            Map<String, Integer> conteoDepartamento,
            Map<String, Integer> conteoCargo

    ) {
        StringBuilder sb = new StringBuilder();

        sb.append("========= REPORTE GENERAL =========\n");
        sb.append("Generado: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n\n");

        sb.append("Encargado: ").append(encargadoNombre != null && !encargadoNombre.isBlank() ? encargadoNombre : "No proporcionado").append("\n");
        if (notaAdicional != null && !notaAdicional.isBlank()) {
            sb.append("Nota: ").append(notaAdicional).append("\n");
        }
        sb.append("\n");



        sb.append("------ Conteo por Cargo ------\n");
        if (conteoCargo == null || conteoCargo.isEmpty()) {
            sb.append("Sin registros\n");
        } else {
            for (Map.Entry<String, Integer> e : conteoCargo.entrySet()) {
                sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            }
        }
        sb.append("\n");

        sb.append("------ Distribución por Departamento ------\n");
        if (conteoDepartamento == null || conteoDepartamento.isEmpty()) {
            sb.append("Sin registros\n");
        } else {
            for (Map.Entry<String, Integer> e : conteoDepartamento.entrySet()) {
                sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            }
        }
        sb.append("\n==========================================");

        return sb.toString();
    }
}
