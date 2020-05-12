package gui_swing.ui.model;

import jdk.internal.org.jline.terminal.Size;
import sun.java2d.pipe.TextRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class TagIcon implements Icon {
    //private Icon[] icons;
    List<String> strings;
    List<ImageIcon> icons;
    public TagIcon(List <ImageIcon> imageIcons, List<String> imageStrings)
    {
        this.icons = imageIcons;
        this.strings = imageStrings;


    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Font font = new Font("Arial", Font.PLAIN, 12);
        FontMetrics metrics = new FontMetrics(font) {
        };
        g.setFont(font);
        x -= 200;

        g.setColor(Color.black);
        int i = 0;
        for (ImageIcon icon: icons)
        {
            /*icon.paintIcon(c, g, x,y);
            x += icon.getIconWidth();*/
            icon.paintIcon(c, g, x,y-5);
            x += icon.getIconWidth()+5;

            Rectangle2D bounds = metrics.getStringBounds(strings.get(i), null);
            int widthInPixels = (int) bounds.getWidth();

            g.drawString(strings.get(i++),x,y+5);
            x+= widthInPixels + 5;
        }




    }

    @Override
    public int getIconWidth() {
        return 12;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}
