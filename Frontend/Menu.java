package Frontend;

import java.awt.*;


import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;



public class Menu extends JFrame {

    public Menu() {
        JFrame vent = new JFrame("NOMINATORX");
        vent.setSize(300,600);
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vent.setLocationRelativeTo(null);


        vent.setBackground(Color.decode("#1D2026"));
        vent.setLocationRelativeTo(null); // centrar
        vent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // no cerrar la app

        JPanel panel = new JPanel();


        panel.setPreferredSize(new DimensionUIResource(300, 300));
        panel.setLayout(null);
        panel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        JLabel menutxt = new JLabel("MENU");
        menutxt.setLayout(null);
        menutxt.setBounds(60, 7, 150, 40);
        menutxt.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(menutxt);






        //Generar NOMINA
        JButton GenerarN = new JButton("Generar Nomina");//creacion del boton
        GenerarN.setPreferredSize(new DimensionUIResource(100, 40));
        GenerarN.setLayout(null);
        GenerarN.setBorderPainted(false);
        GenerarN.setBackground(Color.decode("#020E24"));
        GenerarN.setBounds(50, 90, 150, 40);
        GenerarN.setFont(new Font("Segoe UI", Font.BOLD, 12));
        GenerarN.setForeground(Color.decode("#9A9FAB"));
        GenerarN.setContentAreaFilled(true);

        GenerarN.addMouseListener(new java.awt.event.MouseAdapter() {
            //maldito HOVER
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                GenerarN.setBackground(new Color(1, 14, 46)); // color hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                GenerarN.setBackground(new Color(2, 14, 36)); // color normal
            }
        });


        GenerarN.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        GenerarN.addActionListener(e -> {
            new VentNomina();
            vent.dispose(); // abrir nueva ventana
        });
        panel.add(GenerarN);



        vent.add(panel);
        vent.setVisible(true);

        //Generar NOMINA
        JButton stats = new JButton("Departamentos");//creacion del boton
        stats.setPreferredSize(new DimensionUIResource(100, 40));
        stats.setLayout(null);
        stats.setBorderPainted(false);
        stats.setBackground(Color.decode("#020E24"));
        stats.setBounds(50, 150, 150, 40);
        stats.setFont(new Font("Segoe UI", Font.BOLD, 12));
        stats.setForeground(Color.decode("#9A9FAB"));
        stats.setContentAreaFilled(true);

        stats.addMouseListener(new java.awt.event.MouseAdapter() {
            //maldito HOVER
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                stats.setBackground(new Color(1, 14, 46)); // color hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                stats.setBackground(new Color(2, 14, 36)); // color normal
            }
        });


        stats.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        stats.addActionListener(e -> {
            new Departments();
            vent.dispose(); // abrir nueva ventana
        });
        panel.add(stats);



        vent.add(panel);
        vent.setVisible(true);

    }
}
