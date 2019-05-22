package Componente;
/**
 * @author akuer
 */
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel{    
    private URL url;
    Image image;
 
    public PanelImagen(String img){
        //this.url = getClass().getResource(img);
        this.image = new ImageIcon(img).getImage();
        //new ImageIcon(img)
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.setOpaque(false);
        super.paint(g);
    }
        public static JPanel crearPanel(Container ui, Rectangle bounds, String urlImg, boolean opaque) {
        JPanel panel = null;
        if (urlImg!=null) panel = new PanelImagen(urlImg);
        else panel = new JPanel();
        ui.add(panel);
        panel.setLayout(null);
        panel.setBounds(bounds);
       //panel.setOpaque(opaque);        
        return panel;
    }
}
