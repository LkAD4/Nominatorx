/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encargado;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import models.Informe;
import models.Regi_Nomina;
import models.Trabajador;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

            // Crear carpeta si no existe
            new File("Nominas").mkdirs();

            //imagenes del pdf
            String rutaImagen = "assets/Logo.jpg"; // Asegúrate de que esta ruta sea correcta

            // 2. Carga los datos de la imagen
            ImageData data = ImageDataFactory.create(rutaImagen);

            // 3. Crea el objeto Image de iText
            Image logo = new Image(data);

            // Opcional: Ajusta el tamaño de la imagen (manteniendo la proporción)
            logo.scaleToFit(150, 75); // Ancho máximo de 150, Alto máximo de 75

            // Opcional: Alineación (por ejemplo, centrar la imagen)
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

            //imagenes del pdf
            String Imagen2 = "assets/Downpage.jpg"; // Asegúrate de que esta ruta sea correcta

            ImageData datos = ImageDataFactory.create(Imagen2);
            Image logox = new Image(datos);
            logox.scaleToFit(150, 75);
            logox.setHorizontalAlignment(HorizontalAlignment.RIGHT);

            // Crear el PDF (archivo dentro de Nominas)
            PdfWriter writer = new PdfWriter("Nominas/" + nombreArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Encabezado
            document.add(logo);

            document.add(new Paragraph("Fecha de generación: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n***INFORME****\n"));

            // Listar operaciones
            document.add(new Paragraph(info + text + text2));

            document.add(new Paragraph("\nNOMINATORXBYLKA"));
            document.add(logox);

            // Cerrar documento
            document.close();

            System.out.println("Reporte consolidado generado: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo PDF: " + e.getMessage());
            e.printStackTrace();}
    }



    // Nuevo: genera un reporte general usando Informe + Regi_Nomina y agrega info del encargado
    public static void generarReporteGeneral(
            String encargadoNombre,
            String notaAdicional,
            Map<String, Integer> conteoDepartamento,
            Map<String, Integer> conteoCargo

    ) {
        try {
            new File("Reportes").mkdirs();
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "REPORTE_GENERAL_" + (encargadoNombre != null ? encargadoNombre.replaceAll("\\s+","_") : "anonimo") + "_" + fechaHora + ".pdf";


            String rutaImagen = "assets/Logo.jpg"; // Asegúrate de que esta ruta sea correcta

            // 2. Carga los datos de la imagen
            ImageData data = ImageDataFactory.create(rutaImagen);

            // 3. Crea el objeto Image de iText
            Image logo = new Image(data);

            // Opcional: Ajusta el tamaño de la imagen (manteniendo la proporción)
            logo.scaleToFit(150, 75); // Ancho máximo de 150, Alto máximo de 75

            // Opcional: Alineación (por ejemplo, centrar la imagen)
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

            //imagenes del pdf
            String Imagen2 = "assets/Downpage.jpg"; // Asegúrate de que esta ruta sea correcta

            ImageData datos = ImageDataFactory.create(Imagen2);
            Image logox = new Image(datos);
            logox.scaleToFit(150, 75);
            logox.setHorizontalAlignment(HorizontalAlignment.RIGHT);



            PdfWriter writer = new PdfWriter("Reportes/" + nombreArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // ---------- ENCABEZADO ----------
            document.add(logo);
            document.add(new Paragraph("REPORTE GENERAL"));
            document.add(new Paragraph("Generado: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            document.add(new Paragraph("\n"));

            // ---------- INFORMACIÓN DEL ENCARGADO ----------
            document.add(new Paragraph("Encargado: " + (encargadoNombre != null && !encargadoNombre.isBlank() ? encargadoNombre : "No proporcionado")));
            if (notaAdicional != null && !notaAdicional.isBlank()) {
                document.add(new Paragraph("Nota: " + notaAdicional));
            }
            document.add(new Paragraph("\n"));



            // ---------- TABLA 2: CONTEO POR DEPARTAMENTO ----------
            document.add(new Paragraph("Distribución por Departamento:"));
            Table tablaDept = new Table(UnitValue.createPercentArray(new float[]{4,1})).useAllAvailableWidth();
            tablaDept.addHeaderCell(new Cell().add(new Paragraph("Departamento")));
            tablaDept.addHeaderCell(new Cell().add(new Paragraph("Cantidad")));
            document.add(logox);

            if (conteoDepartamento == null || conteoDepartamento.isEmpty()) {
                tablaDept.addCell(new Cell().add(new Paragraph("Sin registros")));
                tablaDept.addCell(new Cell().add(new Paragraph("0")));
            } else {
                for (Map.Entry<String, Integer> e : conteoDepartamento.entrySet()) {
                    tablaDept.addCell(new Cell().add(new Paragraph(e.getKey())));
                    tablaDept.addCell(new Cell().add(new Paragraph(String.valueOf(e.getValue()))));
                }
            }
            document.add(tablaDept);

            // ---------- FINALIZAR ----------
            document.close();
            System.out.println("Reporte general generado: " + nombreArchivo);


        } catch (Exception e) {
            System.err.println("Error generando reporte general: " + e.getMessage());
            e.printStackTrace();
        }





        }
    public static void generarReporteConfig() {
        try {
            new File("StatsConfig").mkdirs();
            PdfWriter writer = new PdfWriter("StatsConfig/statsconfig.pdf");
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título
            document.add(
                    new Paragraph("📌 CONFIGURACIÓN GENERAL DE SALARIOS Y LÍMITES DE HORAS")
                            .setFontSize(14)
                            .setTextAlignment(TextAlignment.CENTER)
            );

            document.add(new Paragraph(
                    "Fecha de Generación: " +
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
            ).setFontSize(9).setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // Tabla de Salarios
            document.add(new Paragraph("💰 SALARIOS POR DEPARTAMENTO"));
            Table tablaSalarios = new Table(new float[]{2, 1});
            tablaSalarios.setWidth(UnitValue.createPercentValue(100));

            addHeader(tablaSalarios, "Departamento");
            addHeader(tablaSalarios, "Salario (USD)");

            addRow(tablaSalarios, "Sistemas", "500");
            addRow(tablaSalarios, "Marketing", "450");
            addRow(tablaSalarios, "RRHH", "300");
            addRow(tablaSalarios, "Finanzas", "560");
            addRow(tablaSalarios, "Ventas", "440");

            document.add(tablaSalarios);
            document.add(new Paragraph("\n"));

            // Tabla Límites
            document.add(new Paragraph("⏱️ LÍMITES DE HORAS MENSUALES"));
            Table tablaHoras = new Table(new float[]{2, 1});
            tablaHoras.setWidth(UnitValue.createPercentValue(100));

            addHeader(tablaHoras, "Departamento");
            addHeader(tablaHoras, "Horas Máximas");

            addRow(tablaHoras, "Sistemas", "173");
            addRow(tablaHoras, "Marketing", "125");
            addRow(tablaHoras, "RRHH", "140");
            addRow(tablaHoras, "Finanzas", "160");
            addRow(tablaHoras, "Ventas", "130");

            document.add(tablaHoras);

            // Pie
            document.add(new Paragraph("\nGenerado por NOMINATORX © LKA").setFontSize(9)
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();
            System.out.println("PDF generado con éxito: ");

        } catch (Exception e) {
            System.err.println("Error creando el PDF: " + e.getMessage());
        }
    }

    private static void addHeader(Table table, String text) {
        table.addCell(new Cell().add(new Paragraph(text))
                .setBackgroundColor(ColorConstants.DARK_GRAY)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
    }

    private static void addRow(Table table, String col1, String col2) {
        table.addCell(new Cell().add(new Paragraph(col1)));
        table.addCell(new Cell().add(new Paragraph(col2)).setTextAlignment(TextAlignment.RIGHT));
    }
    }
