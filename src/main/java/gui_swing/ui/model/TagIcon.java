package gui_swing.ui.model;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TagIcon implements Icon {
    //private Icon[] icons;
    List<ImageIcon> icons;
    public TagIcon(List <ImageIcon> imageIcons)
    {
        this.icons = imageIcons;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        for (ImageIcon icon: icons)
        {
            /*icon.paintIcon(c, g, x,y);
            x += icon.getIconWidth();*/
            icon.paintIcon(c, g, x-60,y-5);
            x += icon.getIconWidth()+5;
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
