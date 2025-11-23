package Frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.net.URL;
import java.awt.*;

public class Main  extends JFrame {
    public static void main(String[] args) {
        try {


    // moved setAppIcon to a static helper below
            // Puedes usar FlatLightLaf, FlatDarkLaf, FlatIntelliJLaf o FlatDarculaLaf
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            //VENTANA
            JFrame vent = new JFrame("NOMINATORX");
            vent.setSize(300,300);
            vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vent.setLocationRelativeTo(null);       // Centrar

            //assets config
            ImageIcon icon = new ImageIcon("NOMINATORX/assets/ingresar.png");

            Image imgingress = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            ImageIcon iconEscalado = new ImageIcon(imgingress);
            
            
            JPanel panel = new JPanel();
            panel.setLayout(null);

            panel.setPreferredSize(new DimensionUIResource(30, 30));
            panel.setBounds(60,80,150,40);
            panel.setBackground(Color.decode("#1D2026"));
            panel.setFont(new Font("Segoe UI", Font.ITALIC,14));
            //Configuracion del boton
            JButton entrar = new JButton("Ingresar");//creacion del boton
            entrar.setPreferredSize(new DimensionUIResource(80, 40));
            entrar.setLayout(null);
            entrar.setBorderPainted(false);
            entrar.setBackground(Color.decode("#020E24"));
            entrar.setBounds(60, 80, 150, 40);
            entrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
            entrar.setForeground(Color.decode(""));
            entrar.setContentAreaFilled(true);
            entrar.setIcon(iconEscalado);
            entrar.addMouseListener(new java.awt.event.MouseAdapter() {
                //maldito HOVER
         @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        entrar.setBackground(new Color(1, 14, 46)); // color hover
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        entrar.setBackground(new Color(2, 14, 36)); // color normal
    }
});


    entrar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

    entrar.addActionListener(e -> {
    new Menu();
    vent.dispose(); // abrir nueva ventana
});



            



            panel.add(entrar);

            
            
            vent.add(panel);
            vent.setVisible(true);
            vent.add(panel);
            setAppIcon(vent);
            vent.setVisible(true);
            
        });
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
