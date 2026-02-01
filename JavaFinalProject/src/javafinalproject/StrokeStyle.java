/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafinalproject;


import java.awt.BasicStroke;
import java.awt.Graphics2D;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class StrokeStyle {
    public static void apply(Graphics2D g2, int size, boolean dotted) {
        if (dotted) {
            float[] dash = {10.0f};
            g2.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dash, 0));
        } else {
            g2.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }
    }
}