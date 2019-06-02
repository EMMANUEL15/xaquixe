package Componente;
/**
 *
 * @author akuer
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author akuer
 */
public class Label extends JLabel {  
        

public static JLabel crearLabel(Container ui, String texto, Rectangle bounds,
            Color background, Color foreground, Object... args) {
        JLabel lb = new JLabel(texto);
        ui.add(lb);
        lb.setBounds(bounds);
        if (background != null) {
            lb.setBackground(background);
        }
        if (foreground != null) {
            lb.setForeground(foreground);
        }
        if (args.length>0)lb.setFont((Font)args[0]);
        if (args.length>1)lb.setVisible((Boolean)args[1]);
        else lb.setVisible(true);
        return lb;
    }
public static JLabel crear(Container ui, String texto,
            Color background, Color foreground, Object... args) {
        JLabel lb = new JLabel(texto);
        ui.add(lb);
        //lb.setBounds(bounds);
        if (background != null) {
            lb.setBackground(background);
        }
        if (foreground != null) {
            lb.setForeground(foreground);
        }
        if (args.length>0)lb.setFont((Font)args[0]);
        if (args.length>1)lb.setVisible((Boolean)args[1]);
        else lb.setVisible(true);
        return lb;
    }

    public static JLabel crearLabelImg(Container context, String url, Rectangle bounds) {
        Icon icon = new ImageIcon(url);
        JLabel rtn = new JLabel(icon);
        context.add(rtn);
        rtn.setBounds(bounds);
        rtn.setVisible(true);
        return rtn;
    }
}

