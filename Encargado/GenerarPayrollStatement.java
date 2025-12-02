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
    private int horasext;
    public String departamento;

    // Constructor con Informe (extrae datos del trabajador del informe)
    public GenerarPayrollStatement(Informe informe, double salarioDiario,
                                   double horasTrabajadas, int horasext,String departamento) {
        this.informe = informe;
        // Asumiendo que Informe tiene getTrabajador(), como se usa en el constructor original
        this.trabajador = informe.getTrabajador();
        this.salarioDiario = salarioDiario;
        this.horasTrabajadas = horasTrabajadas;
        this.horasext = horasext;
        this.departamento = departamento;
    }


    public void generarPayroll() {
        // Asegúrate de que la carpeta exista
        new File(NOMINAS_FOLDER).mkdirs();

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
            System.out.println("Estado de Nómina generado: " + NOMINAS_FOLDER + nombreArchivo);

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
            System.err.println("Logo no encontrado: " + LOGO_PATH);
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

        agregarCelda(infoTable, "Nombre del Empleado", trabajador.nombreCompleto());
        agregarCelda(infoTable, "Cédula del Empleado", trabajador.getCedula());
        agregarCelda(infoTable, "Departamento", departamento != null ? departamento : "N/A");
        agregarCelda(infoTable, "Puesto", trabajador.getCargo());

        // Cálculos de fechas
        String payBeginDate = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String payEndDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        agregarCelda(infoTable, "Fecha de Inicio de Pago", payBeginDate);
        agregarCelda(infoTable, "Fecha de Fin de Pago", payEndDate);

        document.add(infoTable);
        document.add(new Paragraph("\n"));
    }

    // ====================== GANANCIAS (EARNINGS) CORREGIDO ======================
    private void agregarEarnings(Document document) {

        Paragraph titleEarnings = new Paragraph("GANANCIAS ACTUALES")
                .setFontSize(11)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY);
        document.add(titleEarnings);

        Table earningsTable = new Table(new float[]{3, 1, 1, 1.5f}); // Aumenté la primera columna
        earningsTable.setWidth(UnitValue.createPercentValue(100));

        agregarCeldaEncabezado(earningsTable, "TIPO DE GANANCIA");
        agregarCeldaEncabezado(earningsTable, "DÍAS / %");
        agregarCeldaEncabezado(earningsTable, "TARIFA");
        agregarCeldaEncabezado(earningsTable, "MONTO");

        // Calculo de componentes base
        double diasTrabajados = horasTrabajadas / 8.0;
        double pagoRegular = diasTrabajados * salarioDiario;
        double diasextra = horasext / 8.0;
        double pagoHorasext = diasextra * salarioDiario;

        // CÁLCULOS AÑADIDOS
        double bono = trabajador.getBono(); // Valor fijo
        // Asumiendo que pagoVac es un porcentaje del pago regular
        double pagoVacaciones = pagoRegular * trabajador.pagoVac();

        // 1. Salario Regular
        agregarCeldaDato(earningsTable, "Salario Regular",
                String.format("%.2f", diasTrabajados),
                String.format("$%.2f", salarioDiario),
                String.format("$%.2f", pagoRegular));

        // 2. Pago de Horas extra
        agregarCeldaDato(earningsTable, "Pago de Horas extra",
                String.format("%.2f", diasextra), // Días de 8 horas trabajadas extra
                String.format("$%.2f", salarioDiario),
                String.format("$%.2f", pagoHorasext));

        // 3. Pago de Bono (AÑADIDO)
        agregarCeldaDato(earningsTable, "Bono por Desempeño",
                "N/A",
                "N/A",
                String.format("$%.2f", bono));

        // 4. Pago de Vacaciones (AÑADIDO)
        agregarCeldaDato(earningsTable,
                "Pago de Vacaciones (" + String.format("%.0f", trabajador.pagoVac() * 100) + "%)",
                String.format("%.2f", trabajador.pagoVac()),
                "N/A",
                String.format("$%.2f", pagoVacaciones));


        double subtotalEarnings = pagoRegular + pagoHorasext + bono + pagoVacaciones;

        Paragraph subtotalText = new Paragraph(
                "SUELDO BRUTO: $" + String.format("%.2f", subtotalEarnings))
                .setFontSize(11)
                .setTextAlignment(TextAlignment.RIGHT);

        document.add(earningsTable);
        document.add(subtotalText);
        document.add(new Paragraph("\n"));
    }


    // ====================== DEDUCTIONS CORREGIDO ======================
    private void agregarDeductions(Document document) {

        Paragraph titleDeductions = new Paragraph("DEDUCCIONES")
                .setFontSize(11)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY);
        document.add(titleDeductions);

        Table deductionsTable = new Table(new float[]{3, 1.5f});
        deductionsTable.setWidth(UnitValue.createPercentValue(100));

        agregarCeldaEncabezado(deductionsTable, "TIPO DE DEDUCCIÓN");
        agregarCeldaEncabezado(deductionsTable, "MONTO");

        // Recalculo de Total Earnings (Gross Pay) - Debe ser el mismo que en agregarEarnings
        double diasTrabajados = horasTrabajadas / 8.0;
        double pagoRegular = diasTrabajados * salarioDiario;
        double diasextra = horasext / 8.0;
        double pagoHext = diasextra * salarioDiario;
        double bono = trabajador.getBono();
        double pagoVacaciones = pagoRegular * trabajador.pagoVac();

        double totalEarnings = pagoRegular + pagoHext + bono + pagoVacaciones;

        // Cálculos de deducciones
        double descuentoSalud = totalEarnings * trabajador.Salud();
        double descuentoPensionAP = totalEarnings * trabajador.getAP();

        // DEDUCCIONES AÑADIDAS
        // Asumiendo benSocial() es una deducción porcentual del trabajador
        double descuentoBenSocial = totalEarnings * trabajador.benSocial();
        double pensionFixed = trabajador.getPension(); // Deducción de pensión si edad > 60

        // 1. Seguro de Salud
        agregarCeldaDatoDeduction(deductionsTable,
                "Seguro de Salud (" + String.format("%.0f", trabajador.Salud() * 100) + "%)",
                String.format("$%.2f", descuentoSalud));

        // 2. Aporte a Pensión
        agregarCeldaDatoDeduction(deductionsTable,
                "Aporte Obligatorio Pensión (" + String.format("%.0f", trabajador.getAP() * 100) + "%)",
                String.format("$%.2f", descuentoPensionAP));

        // 3. Deducción Beneficio Social (AÑADIDO)
        agregarCeldaDatoDeduction(deductionsTable,
                "Deducción Beneficio Social (" + String.format("%.0f", trabajador.benSocial() * 100) + "%)",
                String.format("$%.2f", descuentoBenSocial));

        // 4. Deducción Fija por Pensión (AÑADIDO - solo si aplica)
        if (pensionFixed > 0) {
            agregarCeldaDatoDeduction(deductionsTable, "Deducción Fija por Pensión (Edad > 60)",
                    String.format("$%.2f", pensionFixed));
        }

        document.add(deductionsTable);
        document.add(new Paragraph("\n"));
    }


    // ====================== FUNCIONES DE RESUMEN DE TOTALES CORREGIDO ======================
    private void agregarTotales(Document document) {

        Table totalesTable = new Table(new float[]{3, 1.5f});
        totalesTable.setWidth(UnitValue.createPercentValue(100));

        // Recalculo de todos los componentes para consistencia
        double diasTrabajados = horasTrabajadas / 8.0;
        double pagoRegular = diasTrabajados * salarioDiario;
        double diasextra = horasext / 8.0;
        double pagoHext = diasextra * salarioDiario;
        double bono = trabajador.getBono();
        double pagoVacaciones = pagoRegular * trabajador.pagoVac();

        // 1. Total Earnings (Sueldo Bruto)
        double totalEarnings = pagoRegular + pagoHext + bono + pagoVacaciones;

        // 2. Total Deductions
        double descuentoSalud = totalEarnings * trabajador.Salud();
        double descuentoPensionAP = totalEarnings * trabajador.getAP();
        double descuentoBenSocial = totalEarnings * trabajador.benSocial();
        double pensionFixed = trabajador.getPension(); // 0 si edad <= 60

        double totalDeductions = descuentoSalud + descuentoPensionAP + descuentoBenSocial + pensionFixed;

        // 3. Net Pay (Sueldo Neto)
        double netPay = totalEarnings - totalDeductions;

        // 1. SUELDO BRUTO
        agregarCelda(totalesTable, "SUELDO BRUTO",
                String.format("$%.2f", totalEarnings));

        // 2. TOTAL DEDUCCIONES
        agregarCelda(totalesTable, "TOTAL DEDUCCIONES",
                String.format("$%.2f", totalDeductions));

        // 3. SUELDO NETO (Resaltado)
        Cell c1 = new Cell().add(new Paragraph("SUELDO NETO"))
                .setBackgroundColor(ColorConstants.YELLOW);

        Cell c2 = new Cell().add(new Paragraph(String.format("$%.2f", netPay)))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBackgroundColor(ColorConstants.YELLOW);


        totalesTable.addCell(c1);
        totalesTable.addCell(c2);


        document.add(totalesTable);
    }


    // ====================== FUNCIONES AUXILIARES (Sin Cambios) ======================
    private void agregarCeldaEncabezado(Table table, String texto) {
        table.addCell(new Cell()
                .add(new Paragraph(texto))
                .setBackgroundColor(ColorConstants.GRAY)
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER));
    }

    private void agregarCeldaDato(Table table, String t1, String t2, String t3, String t4) {
        table.addCell(t1);
        table.addCell(t2).setTextAlignment(TextAlignment.RIGHT); // Alineación para datos numéricos
        table.addCell(t3).setTextAlignment(TextAlignment.RIGHT); // Alineación para datos numéricos
        table.addCell(t4).setTextAlignment(TextAlignment.RIGHT); // Alineación para datos numéricos
    }

    private void agregarCeldaDatoDeduction(Table table, String nombre, String monto) {
        table.addCell(nombre);
        table.addCell(monto).setTextAlignment(TextAlignment.RIGHT); // Alineación para el monto
    }

    private void agregarCelda(Table table, String nombre, String valor) {
        Cell c1 = new Cell().add(new Paragraph(nombre));
        Cell c2 = new Cell().add(new Paragraph(valor))
                .setTextAlignment(TextAlignment.RIGHT);



        table.addCell(c1);
        table.addCell(c2);
    }
}