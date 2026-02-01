package javafinalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawingCanvas extends JPanel {
    public ArrayList<Stroke> strokes = new ArrayList<>();
    private Stroke activeStroke = null;
    public BufferedImage bgImage = null;
    private DrawingSettings settings;

    public DrawingCanvas(DrawingSettings settings) {
        this.settings = settings;
        setBackground(Color.WHITE);

        MouseAdapter ma = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                activeStroke = new Stroke(settings);
                activeStroke.x1 = activeStroke.x2 = e.getX();
                activeStroke.y1 = activeStroke.y2 = e.getY();
                activeStroke.path.add(e.getPoint());
                repaint();
            }
            public void mouseDragged(MouseEvent e) {
                activeStroke.x2 = e.getX();
                activeStroke.y2 = e.getY();
                activeStroke.path.add(e.getPoint());
                repaint();
            }
            public void mouseReleased(MouseEvent e) {
                strokes.add(activeStroke);
                activeStroke = null;
                repaint();
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    public void undo() {
        if (!strokes.isEmpty()) {
            strokes.remove(strokes.size() - 1);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (bgImage != null) g2.drawImage(bgImage, 0, 0, null);
        for (Stroke s : strokes) s.draw(g2);
        if (activeStroke != null) activeStroke.draw(g2);
    }
}