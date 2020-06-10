package gui_swing.ui.model.tableModels;

import javax.swing.*;
import java.awt.*;

public class GradientButton extends JButton {
    private Color color;
    public GradientButton(String name, Color color) {
        super(name);
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                Color.WHITE,
                new Point(0, getHeight()),
                color));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

    public static GradientButton newInstance(String name, Color color) {
        return new GradientButton(name,color);
    }
}