/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encargado;

// Importaciones corregidas y añadidas
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter; // <-- ¡Esta es la importación CLAVE que faltaba!
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import models.Informe;

import java.io.File;
import java.io.FileNotFoundException; // Usaremos esta excepción más específica
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenerarPDF {

Informe  info;

public GenerarPDF(Informe info){
    this.info = info;
}


    public static void generarNomina(String usuario,String text,String info,String text2) {
        try {
            //  nombre  del archivo
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "NOMINA  " + usuario + "_       " + fechaHora + ".pdf";

            // Crear el PDF
            // * CORRECCIÓN: Se usa la clase PdfWriter de iText *
            PdfWriter writer = new PdfWriter(new File(nombreArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            // Documento de iText 7 usa el tamaño de página por defecto
            Document document = new Document(pdf);

            // Encabezado
            document.add(new Paragraph("NOMINA\n\n"));
            // * CORRECCIÓN: Se usa el parámetro 'usuario' en lugar de la variable interna 'usuario' no definida. *
            document.add(new Paragraph("MAKED IN NOMINATORX "));
            document.add(new Paragraph("Fecha de generación: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n***INFORME****\n"));

            // Listar operaciones
            // * CORRECCIÓN: Se usa el parámetro 'operaciones' en lugar de la variable 'lis' no definida. *

             document.add(new Paragraph(info + text + text2));


            document.add(new Paragraph("\nNOMINATORXBYLKA"));

            // Cerrar documento
            document.close();

            System.out.println("Reporte consolidado generado: " + nombreArchivo);

            // Se usa FileNotFoundException ya que PdfWriter la puede lanzar.
        } catch (IOException e) {
            System.err.println("Error al crear el archivo PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Sobrecarga conveniente: recibe el objeto User y usa su Id_User (agregación) al generar el PDF.
     * * NOTA: Esta función asume que la clase 'User' existe en tu proyecto.
     * Tu código original no la incluye, pero si existe en otro archivo, funcionará.

    public static void generarRepo( String user, List<String> operaciones, double saldoFinal) {
        // Asumiendo que la clase 'User' tiene los métodos 'getNombre()' y 'getIdUser()'
        String nombreUsuario = user.getNombre();
        // Asumiendo que 'getIdUser()' devuelve un objeto que tiene un método 'getId()'
        String id = user.getIdUser() != null ? user.getIdUser().getId() : "N/A";

        try {
            String fechaHora = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "Reporte_" + nombreUsuario + "_" + id + "_" + fechaHora + ".pdf";

            // * CORRECCIÓN: Se usa la clase PdfWriter de iText *
            PdfWriter writer = new PdfWriter(new File(nombreArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("TRANSACCIONES\n\n"));
            document.add(new Paragraph("Cliente: " + nombreUsuario));
            document.add(new Paragraph("ID: " + id));
            document.add(new Paragraph("Fecha de generación: " +
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n--- Operaciones realizadas ---\n"));

            // Se usa el parámetro 'operaciones' correctamente
            for (String op : operaciones) {
                document.add(new Paragraph(op));
            }

            document.add(new Paragraph("\nSaldo final en cuenta: $" + saldoFinal));
            document.add(new Paragraph("\nGracias por usar el NEQUIX BY LEX"));

            document.close();

            System.out.println("Reporte consolidado generado: " + nombreArchivo);

        } catch (FileNotFoundException e) {
            System.err.println("Error al crear el archivo PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // NOTA: Si la clase User no existe en tu proyecto, la aplicación no compilará.
    // Si necesitas crear la clase 'User' para probar el código, indícamelo.
     */
}