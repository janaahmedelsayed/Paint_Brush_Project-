package javafinalproject;

import javax.swing.*;
import java.awt.*;

public class PaintApp extends JFrame {
    public PaintApp() {
        super("Handmade Paint");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        DrawingSettings settings = new DrawingSettings();
        DrawingCanvas canvas = new DrawingCanvas(settings);
        PaintToolbar toolbar = new PaintToolbar(settings, canvas);

        add(toolbar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        // Standard human shortcut for Undo
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "undo");
        canvas.getActionMap().put("undo", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { canvas.undo(); }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception e) {}
        new PaintApp();
    }
}