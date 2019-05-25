package Componente;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
/**
 *
 * @author akuer and Morache
 */
public class Boton extends JButton {
    
     Border lineaNegra;
     private Color color1 = new Color(223,225,230);
     private int redondez;
        
    /**
     * contructor de un boton con solor RGB personalizado
     * @param texto; texto que contendra el boton
     * @param r ; parametro r para crear un color rgb para el boton
     * @param g ; parametro g para crear un color rgb para el boton
     * @param b ; parametro b para crear un color rgb para el boton
     */ 
    public Boton (String texto,int r, int g, int b) {
 
        super(texto);                                       //Hereda el texto introducido en el constructor                             
        //lineaNegra = BorderFactory.createLineBorder(Color.black); // Crea un borde de color Negro y se le asigna 
        //this.setBorder(lineaNegra);
        this.setContentAreaFilled(false);                   //. Si trueel botón pintará el área de contenido. 
                                                            //Si desea tener un botón transparente, por ejemplo, un botón con solo icono, entonces debe configurarlo false. 
                                                            //un botón con solo icono, entonces debe configurarlo false.
        this.setOpaque(false);                               // Permite personalizar el diseño del botón
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        
        
        this.setFont(new Font("Calibri",Font.BOLD,14));     // Define la fuente de la letra y el tamaño
        this.setBackground(new Color(223,225,230));         // Define el color del fondo del botón
        this.setForeground(Color.BLACK);                    // Define el color de la letra
        this.setSize(70, 40);                              //Define la Anchura y la Altura
        
         color1 = new Color(r, g, b);
        
        redondez=15;
    }
    
    /**
     * constructor simple de un boton con texto 
     * @param texto 
     */
    public Boton (String texto) {
 
        super(texto);                                       //Hereda el texto introducido en el constructor                             
        //lineaNegra = BorderFactory.createLineBorder(Color.black); // Crea un borde de color Negro y se le asigna 
        //this.setBorder(lineaNegra);
        this.setContentAreaFilled(false);                   //. Si trueel botón pintará el área de contenido. 
                                                            //Si desea tener un botón transparente, por ejemplo, un botón con solo icono, entonces debe configurarlo false. 
                                                            //un botón con solo icono, entonces debe configurarlo false.
        this.setOpaque(false);                               // Permite personalizar el diseño del botón
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        
        
        this.setFont(new Font("Calibri Light",Font.BOLD,17));     // Define la fuente de la letra y el tamaño
        this.setBackground(new Color(223,225,230));         // Define el color del fondo del botón
        this.setForeground(Color.BLACK);                    // Define el color de la letra
        this.setSize(140, 40);                              //Define la Anchura y la Altura
        
        redondez=20;
    }
    /**
     * constructor simple de un boton con texto 
     * @param texto 
     */
    public Boton (String texto,String imagen) {
        super(texto);                                       //Hereda el texto introducido en el constructor                             
        //lineaNegra = BorderFactory.createLineBorder(Color.black); // Crea un borde de color Negro y se le asigna 
        //this.setBorder(lineaNegra);
        this.setContentAreaFilled(false);                   //. Si trueel botón pintará el área de contenido. 
                                                            //Si desea tener un botón transparente, por ejemplo, un botón con solo icono, entonces debe configurarlo false. 
                                                            //un botón con solo icono, entonces debe configurarlo false.
        this.setOpaque(false);                               // Permite personalizar el diseño del botón
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        
        this.setFont(new Font("Calibri Light",Font.BOLD,17));     // Define la fuente de la letra y el tamaño
        this.setBackground(new Color(223,225,230));         // Define el color del fondo del botón
        this.setForeground(Color.BLACK);                    // Define el color de la letra
        
        //imagen
        ImageIcon img3 = new ImageIcon(imagen);
        ImageIcon img4 = new ImageIcon(img3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        this.setIcon(img4);
        
        redondez=20;
    }
        
    protected void paintComponent(Graphics g) {
        Color c1,c2,c3;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //ButtonModel m = getModel();

         Paint oldPaint = g2.getPaint();
        
           c2=color1;
           c1=color1;
           c3=color1;
       
        
          RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(
                  // en la diguinte line de codigo de pasa como parametro le valor de la redondez
                    0,0,getWidth(),getHeight()-1,redondez,redondez);
            g2.clip(r2d);
            g2.setPaint(new GradientPaint(0.0f, 0.0f, c1,
                    0.0f, getHeight(), c2));
            g2.fillRect(0,0,getWidth(),getHeight());

            g2.setStroke(new BasicStroke(4f));
            g2.setPaint(new GradientPaint(0.0f, 0.0f, c3,
                    0.0f, getHeight(), c3));
            g2.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2, 18, 18);

        g2.setPaint(oldPaint);
        super.paintComponent(g);
    }
    
}
