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
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            PdfWriter writer = new PdfWriter(new File("Nominas/" +nombreArchivo));
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
            e.printStackTrace();}
    }

    public static void generarregistroE(String encargado,String text) {
        try {
            //  nombre  del archivo
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "NOMINA  " + encargado + "_       " + fechaHora + ".pdf";

            // Crear el PDF
            // * CORRECCIÓN: Se usa la clase PdfWriter de iText *
            PdfWriter writer = new PdfWriter(new File("Registros/" + nombreArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            // Documento de iText 7 usa el tamaño de página por defecto
            Document document = new Document(pdf);

            // Encabezado
            document.add(new Paragraph("Registro DE ENCARGADO\n\n"));

            document.add(new Paragraph("MAKED IN NOMINATORX "));
            document.add(new Paragraph("Fecha de generación: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n***Registro****\n"));

            // Listar operaciones

            document.add(new Paragraph( text ));


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


}