/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafinalproject;


import java.awt.*;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Stroke {
    public String tool;
    public Color color;
    public int thickness;
    public boolean filled, dotted;
    public int x1, y1, x2, y2;
    public ArrayList<Point> path = new ArrayList<>();

    public Stroke(DrawingSettings settings) {
        this.tool = settings.currentTool;
        // If eraser, force color to white
        this.color = tool.equals("Eraser") ? Color.WHITE : settings.color;
        this.thickness = settings.thickness;
        this.filled = settings.filled;
        this.dotted = settings.dotted;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        StrokeStyle.apply(g2, thickness, dotted);

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);

        switch (tool) {
            case "Line" -> g2.drawLine(x1, y1, x2, y2);
            case "Rectangle" -> {
                if (filled) g2.fillRect(x, y, w, h);
                else g2.drawRect(x, y, w, h);
            }
            case "Oval" -> {
                if (filled) g2.fillOval(x, y, w, h);
                else g2.drawOval(x, y, w, h);
            }
            default -> { // Brush or Eraser
                for (int i = 1; i < path.size(); i++) {
                    Point p1 = path.get(i - 1);
                    Point p2 = path.get(i);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
}
