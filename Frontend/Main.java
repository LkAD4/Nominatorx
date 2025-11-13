package Frontend;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Puedes usar FlatLightLaf, FlatDarkLaf, FlatIntelliJLaf o FlatDarculaLaf
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("NOMINATORX");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            JButton btn = new JButton("Welcome to NOMINATORX");
            frame.add(btn);
            btn.setSize(50,50);

            frame.setVisible(true);
        });
    }
}