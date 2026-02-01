/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafinalproject;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FileHandler {
    public static void save(JPanel panel) {
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.paint(img.getGraphics());
        
        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
            try {
                File f = jfc.getSelectedFile();
                String path = f.getAbsolutePath();
                if(!path.endsWith(".png")) f = new File(path + ".png");
                ImageIO.write(img, "PNG", f);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public static BufferedImage open(DrawingCanvas canvas) {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(canvas) == JFileChooser.APPROVE_OPTION) {
            try {
                return ImageIO.read(jfc.getSelectedFile());
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }
}
