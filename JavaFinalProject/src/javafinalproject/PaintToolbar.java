package javafinalproject;

import javax.swing.*;
import java.awt.*;

public class PaintToolbar extends JPanel {
    public PaintToolbar(DrawingSettings settings, DrawingCanvas canvas) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(240, 240, 240));

        // Tools
        String[] tools = {"Brush", "Line", "Rectangle", "Oval", "Eraser"};
        JComboBox<String> toolBox = new JComboBox<>(tools);
        toolBox.addActionListener(e -> settings.currentTool = (String) toolBox.getSelectedItem());

        // Color
        JButton colorBtn = new JButton("Color");
        colorBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Pick", settings.color);
            if (c != null) settings.color = c;
        });

        // Thickness
        JSlider sizeSlider = new JSlider(1, 50, settings.thickness);
        sizeSlider.addChangeListener(e -> settings.thickness = sizeSlider.getValue());

        // Checkboxes
        JCheckBox fillCb = new JCheckBox("Fill");
        fillCb.addActionListener(e -> settings.filled = fillCb.isSelected());

        JCheckBox dotCb = new JCheckBox("Dotted");
        dotCb.addActionListener(e -> settings.dotted = dotCb.isSelected());

        
        JButton undoBtn = new JButton("Undo");
        undoBtn.addActionListener(e -> canvas.undo());

        JButton clearBtn = new JButton("Clear All");
        clearBtn.setForeground(Color.RED); 
        clearBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to clear the entire canvas?", 
                "Clear Canvas", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                canvas.strokes.clear();
                canvas.bgImage = null; 
                canvas.repaint();
            }
        });

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> FileHandler.save(canvas));

        JButton openBtn = new JButton("Open");
        openBtn.addActionListener(e -> {
            canvas.bgImage = FileHandler.open(canvas);
            canvas.strokes.clear();
            canvas.repaint();
        });

        add(new JLabel("Tool:")); add(toolBox);
        add(colorBtn);
        add(new JLabel("Size:")); add(sizeSlider);
        add(fillCb); add(dotCb);
        add(new JSeparator(JSeparator.VERTICAL));
        add(undoBtn); 
        add(clearBtn); 
        add(saveBtn); 
        add(openBtn);
    }
}