package models;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Regi_Nomina {
    private final Trabajador datos_Trabajador;

    @SuppressWarnings("unused")

    public List<String> list_datos = new ArrayList<>(); // ya usado por Informe


    // Registro global de trabajadores añadidos para reportes
    private static final List<Trabajador> registroGlobal = new ArrayList<>();

    public Regi_Nomina(Trabajador trabajador ) {
        this.datos_Trabajador = trabajador;
         // Guardamos referencia al informe
        if (this.datos_Trabajador != null) {
            list_datos.add(datos_Trabajador.nombreCompleto());
            list_datos.add(datos_Trabajador.getCedula());
            list_datos.add(String.valueOf(datos_Trabajador.getEdad()));
            list_datos.add(datos_Trabajador.getCargo());
            try { list_datos.add(String.valueOf(datos_Trabajador.getBono())); } catch (Exception ignored) {}
            try { list_datos.add(String.valueOf(datos_Trabajador.getPension())); } catch (Exception ignored) {}
            try { list_datos.add(String.valueOf(datos_Trabajador.benSocial())); } catch (Exception ignored) {}
            try { list_datos.add(String.valueOf(datos_Trabajador.pagoVac())); } catch (Exception ignored) {}
            try { list_datos.add(String.valueOf(datos_Trabajador.Salud())); } catch (Exception ignored) {}
            try { list_datos.add(String.valueOf(datos_Trabajador.getAP())); } catch (Exception ignored) {}

            // Registrar globalmente para reportes
            synchronized (registroGlobal) {
                registroGlobal.add(datos_Trabajador);
            }
        } else {
            System.out.println("No hay datos del trabajador.");
        }
    }

    // Total de trabajadores registrados
    public static int getTotalTrabajadores() {
        synchronized (registroGlobal) {
            return registroGlobal.size();
        }
    }

    // Conteo por cargo
    public static Map<String, Integer> getConteoPorCargo() {
        Map<String, Integer> conteo = new HashMap<>();
        synchronized (registroGlobal) {
            for (Trabajador t : registroGlobal) {
                if (t == null) continue;
                String cargo = t.getCargo() != null ? t.getCargo() : "Sin cargo";
                conteo.put(cargo, conteo.getOrDefault(cargo, 0) + 1);
            }
        }
        return conteo;
    }
    public static Map<String, Integer> getConteoPorDepartamento(String depart) {
        Map<String, Integer> conteo = new HashMap<>();
        synchronized (registroGlobal) {
            for (Trabajador t : registroGlobal) {
                if (t == null) continue;
                String departamento = (depart != null) ? depart : "Sin departamento";
                conteo.put(departamento, conteo.getOrDefault(departamento, 0) + 1);
            }
        }
        return conteo;
    }

    // Limpiar registro (útil en pruebas)
    public static void limpiarRegistro() {
        synchronized (registroGlobal) {
            registroGlobal.clear();
        }
    }
}
