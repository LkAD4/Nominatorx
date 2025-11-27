package Frontend;

import java.awt.*;
import java.net.URL;


import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;


import Encargado.EstadisticasDepartamentos;
import Encargado.Encargado;
import Encargado.GenerarPDF;
import Encargado.GenerarPayrollStatement;
import models.Administrador;
import models.Analista;
import models.Departament;
import models.Director;
import models.Ejecutivo;
import models.Informe;
import models.Soporte_T;
import models.Trabajador;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentNomina extends JFrame {
   
    String N_Trabajador = "";

    String A_Trabajador = "";
    int E_Trabajador = 0;
    String C_Trabajador = "";
    String D_Trabajador = "";
    String CA_trabajador = "";
    Departament sistemas = new Departament(500,"Sistemas",173);
    Departament marketing = new Departament(450,"Marketing",125);
    Departament RRHH = new Departament(300,"RRHH",140);  
    Departament Finanzas = new Departament(560,"Finanzas",160);
    Departament ventas = new Departament(440,"Ventas",122);
    Encargado encargado = new Encargado("ADMIN", "BEADMIN", "AD1", 22);

    EstadisticasDepartamentos estadDept = new EstadisticasDepartamentos();


    public VentNomina() {
            setTitle("Gestor de Nómina");
            setSize(600,600);
            setBackground(Color.decode("#152A85"));
            setLocationRelativeTo(null); // centrar
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no cerrar la app


            
            
            JPanel panel = new JPanel();
            JLabel title = new JLabel();
            title.setLayout(null);
            title.setFont(new Font("Segoe UI", Font.BOLD, 19));
            title.setBounds( 10, 10, 50, 50);
            title.setAlignmentX(Component.LEFT_ALIGNMENT);

            panel.add(title);

            panel.setPreferredSize(new DimensionUIResource(300, 300));
            panel.setFont(new Font("Segoe UI", Font.BOLD, 17));
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
                
           
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            


// ======= NOMBRE =======
JLabel Tnombre = new JLabel("Nombre");
Tnombre.setAlignmentX(Component.LEFT_ALIGNMENT);

panel.add(Tnombre);

JTextField nombre = new JTextField();
        Tnombre.setFont(new Font("Segoe UI", Font.BOLD, 15));
nombre.setMaximumSize(new Dimension(258, 20));
nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
nombre.setToolTipText("Ingrese el nombre del trabajador");

panel.add(nombre);

panel.add(Box.createRigidArea(new Dimension(0, 15)));


// ======= APELLIDO =======
JLabel Tapellido = new JLabel("Apellido");
Tapellido.setPreferredSize(new DimensionUIResource(40, 40));
        Tapellido.setFont(new Font("Segoe UI", Font.BOLD, 15));
Tapellido.setAlignmentX(Component.LEFT_ALIGNMENT);


panel.add(Tapellido);

JTextField apellido = new JTextField();
apellido.setMaximumSize(new Dimension(258, 20));
apellido.setAlignmentX(Component.LEFT_ALIGNMENT);
apellido.setToolTipText("Ingrese el apellido del trabajador");
panel.add(apellido);

panel.add(Box.createRigidArea(new Dimension(0, 15)));


// ======= EDAD =======
JLabel Tedad = new JLabel("Edad");
        Tedad.setFont(new Font("Segoe UI", Font.BOLD, 15));
Tedad.setAlignmentX(Component.LEFT_ALIGNMENT);
panel.add(Tedad);

JTextField edad = new JTextField();
edad.setMaximumSize(new Dimension(70, 20));
edad.setToolTipText("Solo números. Edad del trabajador.");

edad.setAlignmentX(Component.LEFT_ALIGNMENT);
panel.add(edad);

panel.add(Box.createRigidArea(new Dimension(0, 15)));


// ======= CEDULA =======
JLabel Tcedula = new JLabel("Cedula");
        Tcedula.setFont(new Font("Segoe UI", Font.BOLD, 15));
Tcedula.setAlignmentX(Component.LEFT_ALIGNMENT);
panel.add(Tcedula);

JTextField cedula = new JTextField();
cedula.setMaximumSize(new Dimension(258, 20));
cedula.setAlignmentX(Component.LEFT_ALIGNMENT);
cedula.setToolTipText("Número de identificación ");

panel.add(cedula);

panel.add(Box.createRigidArea(new Dimension(0, 15)));





// initialize listas before using it


// ---------- DATOS (pueden venir de tu clase List_fijas) ----------
String[] departamentos = {
   "Sistemas", "Marketing", "Ventas","RRHH","Finanzas"
};

String[] cargos = {
   "Director", "Analista","Ejecutivo","Administrador","Soporte T"
};


// ---------- PANEL CON BOXLAYOUT ----------

panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setFont(new Font("Segoe UI", Font.BOLD, 17));


// ---------- DEPARTAMENTO ----------
JLabel labelDepartamento = new JLabel("Departamento");
labelDepartamento.setFont(new Font("Segoe UI", Font.BOLD, 15));
labelDepartamento.setAlignmentX(Component.LEFT_ALIGNMENT);
panel.add(labelDepartamento);

// espacio pequeño
panel.add(Box.createRigidArea(new Dimension(0, 5)));

// ComboBox con datos usando un FOR
JComboBox<String> boxDepartamentos = new JComboBox<>();
for (String dep : departamentos) {
    boxDepartamentos.addItem(dep);
}

boxDepartamentos.setMaximumSize(new Dimension(258, 25));
boxDepartamentos.setAlignmentX(Component.LEFT_ALIGNMENT);
boxDepartamentos.setToolTipText("Seleccione el departamento del trabajador");
panel.add(boxDepartamentos);

// espacio grande
panel.add(Box.createRigidArea(new Dimension(0, 15)));


// ---------- CARGO ----------
JLabel labelCargo = new JLabel("Cargo");
labelCargo.setFont(new Font("Segoe UI", Font.BOLD, 15));
labelCargo.setAlignmentX(Component.LEFT_ALIGNMENT);
panel.add(labelCargo);

// espacio pequeño
panel.add(Box.createRigidArea(new Dimension(0, 5)));


JComboBox<String> boxCargos = new JComboBox<>();
for (String cargo : cargos) {
    boxCargos.addItem(cargo);
}

boxCargos.setMaximumSize(new Dimension(258, 25));
boxCargos.setAlignmentX(Component.LEFT_ALIGNMENT);
boxCargos.setToolTipText("Seleccione el cargo del trabajador");
panel.add(boxCargos);

// espacio grande
panel.add(Box.createRigidArea(new Dimension(0, 15)));

JLabel Thoras = new JLabel("Horas trabajadas:");
Thoras.setFont(new Font("Segoe UI", Font.BOLD, 17));//Horas trabajadas
Thoras.setAlignmentX(Component.LEFT_ALIGNMENT);
panel.add(Thoras);

//HORAS
JTextField horas = new JTextField();
horas.setMaximumSize(new Dimension(100, 20));

horas.setAlignmentX(Component.LEFT_ALIGNMENT);
horas.setToolTipText("Ingrese las horas trabajadas (número entero)");
panel.add(horas);

panel.add(Box.createRigidArea(new Dimension(0, 15)));


// ---------- OBTENER DATOS SELECCIONADOS ----------
JButton guardar = new JButton("Generar Nomina");

            guardar.setBackground(Color.decode("#0CCC48"));
            guardar.setBounds(50, 50, 200, 40);
            guardar.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            guardar.setForeground(Color.decode("#063D57"));
            guardar.setContentAreaFilled(true);
            // guardar.setIcon(iconEscalado); // iconEscalado not defined here; set an icon if available
            guardar.addMouseListener(new java.awt.event.MouseAdapter() {
                //maldito HOVER
         @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        guardar.setBackground(new Color(28, 255, 98)); // color hover
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        guardar.setBackground(new Color(12, 204, 72)); // color normal
    }
});

guardar.addActionListener(e -> {


    //Control y verificacion
    String N_Trabajador = (String) Encargado.capitalize(nombre.getText());
     if (A_Trabajador == null  ) {
        JOptionPane.showMessageDialog(null, "Escribe un nombre.");
        return;
    }

    A_Trabajador = (String) Encargado.capitalize(apellido.getText());
    if (A_Trabajador == null  ) {
        JOptionPane.showMessageDialog(null, "Escribe un Apellido.");
        return;
    }
    int Edad_Trabajador =  Integer.parseInt(edad.getText()) ;
    if ( Edad_Trabajador <= 18 ) {
        JOptionPane.showMessageDialog(null, "Escribe una edad mayor a 18.");
        return;
    }
    C_Trabajador =  (String) cedula.getText() ;
    if (C_Trabajador == null  || C_Trabajador.length() < 10 || C_Trabajador.length() > 10) {
        JOptionPane.showMessageDialog(null, "Escribe una cedula de 10 digitos.");
        return;
    }


    
    String D_Trabajador = (String) boxDepartamentos.getSelectedItem();
    String CA_trabajador = (String) boxCargos.getSelectedItem();
    
    

   
    if (D_Trabajador == null || CA_trabajador == null ) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar ambos campos.");
        return;
    }











//CREACION DEL TRABAJADOR


    JOptionPane.showMessageDialog(null,
        "Nomina Generada");
    System.out.println("Nomina \nTrabajador: " + N_Trabajador+ "Cargo:" + CA_trabajador  + " USD. \n Perteneciente al departamento de "+ D_Trabajador);
    Trabajador trabajador = new Trabajador(N_Trabajador,A_Trabajador,C_Trabajador,Edad_Trabajador,D_Trabajador,CA_trabajador);
    Encargado encargado = new Encargado("ADMIN", "ADMIN", "BEADMIN123", 22);
                    switch (CA_trabajador.toLowerCase()) {
                        case "director":
                            trabajador  = new Director(N_Trabajador,A_Trabajador,C_Trabajador,Edad_Trabajador,D_Trabajador,CA_trabajador);
                            break;
                        case "analista":
                            trabajador  = new Analista(N_Trabajador,A_Trabajador,C_Trabajador,Edad_Trabajador,D_Trabajador,CA_trabajador);
                            break;
                        case "soporte t":
                            trabajador  = new Soporte_T(N_Trabajador,A_Trabajador,C_Trabajador,Edad_Trabajador,D_Trabajador,CA_trabajador);
                            break;
                        case "ejecutivo":
                            trabajador  = new Ejecutivo(N_Trabajador,A_Trabajador,C_Trabajador,Edad_Trabajador,D_Trabajador,CA_trabajador);
                            break;
                        case "administrador":
                            trabajador  = new Administrador(N_Trabajador,A_Trabajador,C_Trabajador,Edad_Trabajador,D_Trabajador,CA_trabajador);
                            break;
                        default:
                            break;
                    }
                    //Luego de crear el trabajado.
                    int H_Trabajador = 0;

                    // Get the corresponding department object
                    Departament departamento = null;
                    switch(D_Trabajador) {
                        case "Sistemas": departamento = sistemas; break;
                        case "Marketing": departamento = marketing; break;
                        case "RRHH": departamento = RRHH; break;
                        case "Finanzas": departamento = Finanzas; break;
                        case "Ventas": departamento = ventas; break;
                    }

                    System.out.println("cantidad de horas trabajadas en el mes:");
                    H_Trabajador = Integer.parseInt(horas.getText());
                    
                    while (H_Trabajador > departamento.geth_Totales()) {
                        System.out.println("Error: El trabajador ha excedido el límite de horas del departamento.");
                        break;
                    }
                    //CALCULO Y CREACION DE EL INFORME
                    int Total = encargado.Calcular_N(H_Trabajador, departamento.getSalario(),trabajador.getBono(),trabajador.getPension(),trabajador.benSocial(), trabajador.pagoVac(),trabajador.Salud(),trabajador.getAP());
                    System.out.println("Nomina \nTrabajador: " + trabajador.nombreCompleto() + "Cargo:" + CA_trabajador + "\nPago total: " + Total + " USD. \n Perteneciente al departamento de "+ D_Trabajador+ "\nHoras trabajadas: "+ H_Trabajador+ "\nSalario por dia: "+ departamento.getSalario()+ " USD \n Encargado de nómina: "+ encargado.nombreCompleto()+"\nID: "+ encargado.getId());
                    Informe info = new Informe(trabajador);
                    System.out.print(info.crearInforme(trabajador));
                    String NText1 = "\nDepartamento "+ D_Trabajador+ "\nHoras trabajadas: "+ H_Trabajador+ "\nSalario por dia: "+ departamento.getSalario() +  " USD" + "\nPago total: "    + Total + " USD.";
                    String NText2 = ("");
                                        
                    //generacion del pdf

                    System.out.println("----------------------------------------");
                    
                    GenerarPayrollStatement payroll = new GenerarPayrollStatement(
                        info, 
                        departamento.getSalario(),    // Salario diario del departamento
                        H_Trabajador,                 // Horas trabajadas en el mes
                        0 , D_Trabajador                         // Días de vacaciones usados


                    );

                        estadDept.aumentar(D_Trabajador);
                    payroll.generarPayroll();




});

panel.add(guardar);

//Stadisticas boton
        JButton statsE = new JButton(" generar Estadisticas");//creacion del boton
        statsE.setPreferredSize(new DimensionUIResource(100, 40));
        statsE.setLayout(null);
        statsE.setBorderPainted(false);
        statsE.setBackground(Color.decode("#020E24"));
        statsE.setBounds(80, 70, 200, 40);
        statsE.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statsE.setForeground(Color.decode("#9A9FAB"));
        statsE.setContentAreaFilled(true);

        statsE.addMouseListener(new java.awt.event.MouseAdapter() {
            //maldito HOVER
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                statsE.setBackground(new Color(1, 14, 46)); // color hover

                statsE.setForeground(Color.decode("#C4F5FF"));


            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                statsE.setBackground(new Color(2, 14, 36));
                // color normal
                statsE.setForeground(Color.decode("#9A9FAB"));
            }
        });


        statsE.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        statsE.addActionListener(e -> {
            Map<String, Integer> conteoDepartamento = estadDept.getContadores();
            Map<String, Integer> conteoCargo = estadDept.getContadores();

            GenerarPDF.generarReporteGeneral(
                    encargado.nombreCompleto(),
                    "info",
                    conteoDepartamento,
                    conteoCargo // Este se hace igual pero en otra clase
            );

        });
        panel.add(statsE);
        JButton back = new JButton("Volver al menu");//creacion del boton
        back.setPreferredSize(new DimensionUIResource(100, 40));
        back.setLayout(null);
        back.setBorderPainted(false);
        back.setBackground(Color.decode("#B5B5B5"));
        back.setBounds(50, 250, 30, 40);
        back.setFont(new Font("Segoe UI", Font.BOLD, 12));
        back.setForeground(Color.decode("#000000"));
        back.setContentAreaFilled(true);

        back.addMouseListener(new java.awt.event.MouseAdapter() {
            //maldito HOVER
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                back.setBackground(new Color(230, 230, 230)); // color hover

            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                back.setBackground(new Color(181, 181, 181));
                // color normal
            }
        });


        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        back.addActionListener(e -> {
            new Menu();
            this.dispose();
             // abrir nueva ventana
        });



        panel.add(back);





            

                


                add(panel);
                setVisible(true);
        }
         private static void setAppIcon(JFrame frame) {
        // La ruta debe ser relativa al classpath (generalmente la carpeta src o el JAR)
        // Sustituye "app_icon.png" por el nombre de tu archivo.
        URL iconURL = Main.class.getResource("/assets/Downpage.jpg");
        
        if (iconURL != null) {
            // Carga la imagen usando la utilidad Toolkit
            Image icon = Toolkit.getDefaultToolkit().getImage(iconURL);
            
            // Establece el icono del JFrame
            frame.setIconImage(icon);
        } else {
            System.err.println("Advertencia: No se encontró el archivo del icono de la aplicación.");
        }
        
    }


    }

    
