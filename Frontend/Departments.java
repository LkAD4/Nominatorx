package Frontend;

import models.Departament;
import Encargado.GenerarPDF;
import java.awt.*;


import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class Departments extends JFrame {
    Departament sistemas = new Departament(500,"Sistemas",173);
    Departament marketing = new Departament(450,"Marketing",125);
    Departament RRHH = new Departament(300,"RRHH",140);
    Departament Finanzas = new Departament(560,"Finanzas",160);
    Departament ventas = new Departament(440,"Ventas",122);
    public Departments() {
        JFrame vent = new JFrame("NOMINATORX");
        vent.setSize(600,600);
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vent.setLocationRelativeTo(null);


        vent.setBackground(Color.decode("#1D2026"));
        vent.setLocationRelativeTo(null); // centrar
        vent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no cerrar la app

        JPanel panel = new JPanel();


        panel.setPreferredSize(new DimensionUIResource(300, 300));
        panel.setLayout(null);
        panel.setFont(new Font("Segoe UI", Font.BOLD, 19));
        JLabel dept = new JLabel("Departamentos.");
        dept.setLayout(null);
        dept.setBounds(60, 7, 150, 40);
        dept.setFont(new Font("Segoe UI", Font.BOLD, 19));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        int yPos = 20; // Empezamos 20 píxeles abajo del borde superior


        panel.add(new JLabel("Salarios"));//Salarios de los departamentos
        panel.add(new JLabel("Sistemas: " + sistemas.getSalario() + " USD"));
        panel.add(new JLabel("Marketing: " + marketing.getSalario() + " USD"));
        panel.add(new JLabel("RRHH: " + RRHH.getSalario() + " USD"));
        panel.add(new JLabel("Finanzas: " + Finanzas.getSalario() + " USD"));
        panel.add(new JLabel("Ventas: " + ventas.getSalario() + " USD"));
            //Limites de horas
        panel.add(new JLabel("\nLímites de horas mensuales:"));
        panel.add(new JLabel("Sistemas: " + sistemas.geth_Totales() + " horas"));
        panel.add(new JLabel("Marketing: " + marketing.geth_Totales() + " horas"));
        panel.add(new JLabel("RRHH: " + RRHH.geth_Totales() + " horas"));
        panel.add(new JLabel("Finanzas: " + Finanzas.geth_Totales() + " horas"));
        panel.add(new JLabel("Ventas: " + ventas.geth_Totales() + " horas"));


        JButton stas = new JButton("Generar documento");
        stas.setPreferredSize(new DimensionUIResource(100, 40));
        stas.setLayout(null);
        stas.setBorderPainted(false);
        stas.setBackground(Color.decode("#73021D"));
        stas.setBounds(50, 200, 30, 40);
        stas.setFont(new Font("Segoe UI", Font.BOLD, 12));
        stas.setForeground(Color.decode("#000000"));
        stas.setContentAreaFilled(true);

        stas.addMouseListener(new java.awt.event.MouseAdapter() {
            //maldito HOVER
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                stas.setBackground(new Color(186, 7, 7)); // color hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                stas.setBackground(new Color(115, 2, 29)); // color normal
            }
        });


        stas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));


        stas.addActionListener(e -> {
             GenerarPDF.generarReporteConfig();
        });


        JButton back = new JButton("Volver al menu");//creacion del boton
        back.setPreferredSize(new DimensionUIResource(100, 40));
        back.setLayout(null);
        back.setBorderPainted(false);
        back.setBackground(Color.decode("#EDEBEB"));
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
                back.setBackground(new Color(181, 181, 181)); // color normal
            }
        });


        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));


        back.addActionListener(e -> {
            new Menu();
            vent.dispose(); // abrir nueva ventana
        });


        vent.add(panel);
        panel.add(back);
        panel.add(stas);

        vent.setVisible(true);
        }





}


