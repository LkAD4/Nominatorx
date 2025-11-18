/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encargado;

// Importaciones corregidas y añadidas}

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
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
    
    
    
            //imagenes del pdf
            String rutaImagen = "assets/Logo.jpg"; // Asegúrate de que esta ruta sea correcta
    
            // 2. Carga los datos de la imagen
            ImageData data = ImageDataFactory.create(rutaImagen);
            
            // 3. Crea el objeto Image de iText
            Image logo = new Image(data);
            
            // Opcional: Ajusta el tamaño de la imagen (manteniendo la proporción)
            logo.scaleToFit(150, 75); // Ancho máximo de 150, Alto máximo de 75
            
            // Opcional: Alineación (por ejemplo, centrar la imagen)
            logo.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);


            
            //imagenes del pdf
            String Imagen2 = "assets/Downpage.jpg"; // Asegúrate de que esta ruta sea correcta
    
            // 2. Carga los datos de la imagen
            ImageData datos = ImageDataFactory.create(Imagen2);
            
            // 3. Crea el objeto Image de iText
            Image logox = new Image(datos);
            
            // Opcional: Ajusta el tamaño de la imagen (manteniendo la proporción)
            logox.scaleToFit(150, 75); // Ancho máximo de 150, Alto máximo de 75
            
            // Opcional: Alineación (por ejemplo, centrar la imagen)
            logox.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.RIGHT);
            
          

            // Crear el PDF
            // * CORRECCIÓN: Se usa la clase PdfWriter de iText *
            PdfWriter writer = new PdfWriter(new File("Nominas/" +nombreArchivo));
            PdfDocument pdf = new PdfDocument(writer);
            // Documento de iText 7 usa el tamaño de página por defecto
            Document document = new Document(pdf);

            // Encabezado
            document.add(logo);
            
            // * CORRECCIÓN: Se usa el parámetro 'usuario' en lugar de la variable interna 'usuario' no definida. *
            
            document.add(new Paragraph("Fecha de generación: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n***INFORME****\n"));

            // Listar operaciones
            // * CORRECCIÓN: Se usa el parámetro 'operaciones' en lugar de la variable 'lis' no definida. *

            document.add(new Paragraph(info + text + text2));


            document.add(new Paragraph("\nNOMINATORXBYLKA"));
            document.add(logox);

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