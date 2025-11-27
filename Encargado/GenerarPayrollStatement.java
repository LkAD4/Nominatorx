/*
 * Generador de Payroll Statement en PDF con iText
 * Utiliza datos de la clase Trabajador para generar un estado de nómina profesional
 */
package Encargado;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import models.Trabajador;
import models.Informe;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerarPayrollStatement {

    private static final String LOGO_PATH = "assets/Logo.jpg";
    private static final String NOMINAS_FOLDER = "Nominas/";
    
    private Trabajador trabajador;
    private Informe informe;
    private double salarioDiario;
    private double horasTrabajadas;
    private double diasVacacionesUsados;
    public String departamento;

    // Constructor con Informe (extrae datos del trabajador del informe)
    public GenerarPayrollStatement(Informe informe, double salarioDiario, 
                                   double horasTrabajadas, double diasVacacionesUsados,String departamento) {
        this.informe = informe;
        this.trabajador = informe.getTrabajador();
        this.salarioDiario = salarioDiario;
        this.horasTrabajadas = horasTrabajadas;
        this.diasVacacionesUsados = diasVacacionesUsados;
        this.departamento = departamento;
    }

    // Constructor alternativo sin Informe (para mantener compatibilidad)
    public GenerarPayrollStatement(Trabajador trabajador, double salarioDiario, 
                                   double horasTrabajadas, double diasVacacionesUsados) {
        this.trabajador = trabajador;
        this.informe = null;
        this.salarioDiario = salarioDiario;
        this.horasTrabajadas = horasTrabajadas;
        this.diasVacacionesUsados = diasVacacionesUsados;
    }

    public void generarPayroll() {
        try {
            // Crear nombre del archivo
            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String nombreArchivo = "NOMINA_" + trabajador.getT_nombre() + "--" + fechaHora + ".pdf";
            
            // Crear writer y documento
            PdfWriter writer = new PdfWriter(new File(NOMINAS_FOLDER + nombreArchivo));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            
            // Agregar contenido
            agregarEncabezado(document);
            agregarInfoEmpleado(document);
            agregarEarnings(document);

            agregarDeductions(document);
            agregarTotales(document);

            
            document.close();
            System.out.println("Estado de Nómina generado: " + nombreArchivo);
            
        } catch (IOException e) {
            System.err.println("Error al crear el Estado de Nómina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void agregarEncabezado(Document document) throws IOException {
        // Logo
        try {
            ImageData imageData = ImageDataFactory.create(LOGO_PATH);
            Image logo = new Image(imageData);
            logo.scaleToFit(100, 60);
            logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(logo);
        } catch (IOException e) {
            System.out.println("Logo no encontrado: " + LOGO_PATH);
        }
        
        // Título
        Paragraph titulo = new Paragraph(new Text("ESTADO DE NÓMINA"))
                .setFontSize(16)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(titulo);
        
        Paragraph fecha = new Paragraph("Fecha de generación: " + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .setFontSize(9)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(fecha);
        
        document.add(new Paragraph("\n"));
    }

    private void agregarInfoEmpleado(Document document) {
        // Tabla de información del empleado
        Table infoTable = new Table(new float[]{1, 1});
        infoTable.setWidth(UnitValue.createPercentValue(100));
        
        agregarCelda(infoTable, "Nombre del Empleado", trabajador.nombreCompleto(), true);
        agregarCelda(infoTable, "Cédula del Empleado", trabajador.getCedula(), true);
        agregarCelda(infoTable, "Departamento", departamento != null ? departamento : "N/A", true);
        agregarCelda(infoTable, "Puesto", trabajador.getCargo(), true);
        
        // Cálculos de fechas
        String payBeginDate = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String payEndDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        agregarCelda(infoTable, "Fecha de Inicio de Pago", payBeginDate, true);
        agregarCelda(infoTable, "Fecha de Fin de Pago", payEndDate, true);
        
        document.add(infoTable);
        document.add(new Paragraph("\n"));
    }

    private void agregarEarnings(Document document) {

        Paragraph titleEarnings = new Paragraph("GANANCIAS ACTUALES")
                .setFontSize(11)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY);
        document.add(titleEarnings);

        Table earningsTable = new Table(new float[]{2, 1, 1, 1});
        earningsTable.setWidth(UnitValue.createPercentValue(100));

        agregarCeldaEncabezado(earningsTable, "TIPO DE GANANCIA");
        agregarCeldaEncabezado(earningsTable, "DÍAS");
        agregarCeldaEncabezado(earningsTable, "TARIFA");
        agregarCeldaEncabezado(earningsTable, "MONTO");

        double diasTrabajados = horasTrabajadas / 8.0;
        double pagoRegular = diasTrabajados * salarioDiario;
        double pagoVacaciones = diasVacacionesUsados * salarioDiario;

        agregarCeldaDato(earningsTable, "Salario Regular",
                String.format("%.2f", diasTrabajados),
                String.format("$%.2f", salarioDiario),
                String.format("$%.2f", pagoRegular));

        agregarCeldaDato(earningsTable, "Pago de Vacaciones",
                String.format("%.2f", diasVacacionesUsados),
                String.format("$%.2f", salarioDiario),
                String.format("$%.2f", pagoVacaciones));

        double subtotalEarnings = pagoRegular + pagoVacaciones;

        Paragraph subtotalText = new Paragraph(
                "SUBTOTAL: $" + String.format("%.2f", subtotalEarnings))
                .setTextAlignment(TextAlignment.RIGHT);

        document.add(earningsTable);
        document.add(subtotalText);
        document.add(new Paragraph("\n"));
    }


    // ====================== FUNCIONES DE DEDUCCIONES ======================
    private void agregarDeductions(Document document) {

        Paragraph titleDeductions = new Paragraph("DEDUCCIONES")
                .setFontSize(11)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY);
        document.add(titleDeductions);

        Table deductionsTable = new Table(new float[]{2, 1});
        deductionsTable.setWidth(UnitValue.createPercentValue(100));

        agregarCeldaEncabezado(deductionsTable, "TIPO DE DEDUCCIÓN");
        agregarCeldaEncabezado(deductionsTable, "MONTO");

        double diasTrabajados = horasTrabajadas / 8.0;
        double salarioMensual = diasTrabajados * salarioDiario;
        double pagoVacaciones = diasVacacionesUsados * salarioDiario;
        double totalEarnings = salarioMensual + pagoVacaciones;

        double descuentoSalud = totalEarnings * trabajador.Salud();
        double descuentoPension = totalEarnings * trabajador.getAP();

        agregarCeldaDatoDeduction(deductionsTable, "Seguro de Salud",
                String.format("$%.2f", descuentoSalud));

        agregarCeldaDatoDeduction(deductionsTable, "Aporte Pensión",
                String.format("$%.2f", descuentoPension));

        document.add(deductionsTable);
        document.add(new Paragraph("\n"));
    }


    // ====================== FUNCIONES DE RESUMEN DE TOTALES ======================
    private void agregarTotales(Document document) {

        Table totalesTable = new Table(new float[]{2, 1});
        totalesTable.setWidth(UnitValue.createPercentValue(100));

        double diasTrabajados = horasTrabajadas / 8.0;
        double salarioMensual = diasTrabajados * salarioDiario;
        double pagoVacaciones = diasVacacionesUsados * salarioDiario;
        double totalEarnings = salarioMensual + pagoVacaciones;

        double totalDeductions = (totalEarnings * trabajador.Salud()) +
                (totalEarnings * trabajador.getAP());

        double netPay = totalEarnings - totalDeductions;

        agregarCelda(totalesTable, "SUELDO BRUTO",
                String.format("$%.2f", totalEarnings), true);

        agregarCelda(totalesTable, "TOTAL DEDUCCIONES",
                String.format("$%.2f", totalDeductions), true);

        agregarCelda(totalesTable, "SUELDO NETO",
                String.format("$%.2f", netPay), true);

        document.add(totalesTable);
    }


    // ====================== FUNCIONES AUXILIARES ======================
    private void agregarCeldaEncabezado(Table table, String texto) {
        table.addCell(new Cell()
                .add(new Paragraph(texto))

                .setBackgroundColor(ColorConstants.GRAY)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
    }

    private void agregarCeldaDato(Table table, String t1, String t2, String t3, String t4) {
        table.addCell(t1);
        table.addCell(t2);
        table.addCell(t3);
        table.addCell(t4);
    }

    private void agregarCeldaDatoDeduction(Table table, String nombre, String monto) {
        table.addCell(nombre);
        table.addCell(monto);
    }

    private void agregarCelda(Table table, String nombre, String valor, boolean bold) {
        Cell c1 = new Cell().add(new Paragraph(nombre));
        Cell c2 = new Cell().add(new Paragraph(valor))
                .setTextAlignment(TextAlignment.RIGHT);



        table.addCell(c1);
        table.addCell(c2);
    }
}