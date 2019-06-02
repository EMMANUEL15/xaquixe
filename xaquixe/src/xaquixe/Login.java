package xaquixe;

/**
 *
 * @author Wilbert
 */

import Componente.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

/**
 *
 * @author Wilbert
 */
public class Login extends JFrame{
    
    // Componentes a Utilizar en la Ventana
    private Container contenedor;// En este contenedor guardaremos todos nuestros componentes
    private JPanel panelGestor;
    // Botones
    private Boton aceptar;
    // JLabel
    JLabel usuario, contrasenia, lema, titulo, lema1;
    // JTextField
    JTextField dusuario =  new JTextField();
    JTextField dcontrasenia = new JPasswordField();
    
    Font fuente  = new Font("Roboto",Font.BOLD,14);
    
    public Login()
    {
        super();                    // usamos el contructor de la clase padre JFrame
         configurarVentana();        // configuramos la ventana
         inicializarComponentes();
    }
    
    // Configuracion de la Ventana
    
    private void configurarVentana()
    {
        this.setTitle("Vidrio Artesanal XA QUIXE S. De R.L Mi. Art.");                   // colocamos titulo a la ventana
        this.setSize(600,400);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        contenedor = getContentPane();                          // Contenedor que guarda los componentes de la ventana
        contenedor.setLayout(null);                             // Se administra manualmente la posición de los componentes
        contenedor.setBackground(new Color(255,255,255));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        this.setBackground(java.awt.Color.WHITE);               //Se asigna el color del formulario JFRAME
        this.setUndecorated(false);                             //Metodo oculta la barra de titulo
       // ImageIcon ImageIcon = new ImageIcon(getClass().getResource("Imagenes/logo.png"));
       // Image Image = ImageIcon.getImage();   // Establecer el logo de la empresa 
       // this.setIconImage(Image); 
    }
        
        // Iniciar Componentes 
    private void inicializarComponentes()
    {
        //<<------------------------------------Creación y Configuración de los componentes ------------------------------------>>
         //Etiquetas
         lema1 =  Label.crearLabel(contenedor, "EST. 2002",new Rectangle(260,40,180,10), Color.BLACK,Color.RED); 
         lema1.setFont(new Font("Microsoft Yi Baiti",Font.BOLD,12));
         titulo= Label.crearLabel(contenedor, "XAQUIXE",new Rectangle(200,55,180,35), Color.BLACK,Color.BLACK);
         titulo.setFont(new Font("Microsoft Yi Baiti",Font.BOLD,48));
         lema =  Label.crearLabel(contenedor, "GLASS_INNOVATION_STUDIO",new Rectangle(230,80,180,35), Color.BLACK,Color.BLACK); 
         lema.setFont(new Font("Microsoft Yi Baiti",Font.BOLD,12));
         usuario = Label.crearLabel(contenedor, "USUARIO: ",new Rectangle(160,145,180,18), Color.BLACK,Color.BLACK);
         usuario.setFont(new Font("Roboto",Font.BOLD,15));
         contrasenia = Label.crearLabel(contenedor, "CONTRASEÑA: ",new Rectangle(160,185,180,18), Color.BLACK,Color.BLACK);
         contrasenia.setFont(new Font("Roboto",Font.BOLD,15));
         // Botones 
         
         dusuario.setFont(fuente);
         dusuario.setBounds(280,140, 140, 30); // X, Y, Ancho, Alto
         dcontrasenia.setFont(fuente);
         dcontrasenia.setBounds(280,180, 140, 30); // X, Y, Ancho, Alto
         aceptar = new Boton("Aceptar");
         aceptar.setBounds(230,250, 140, 30); // X, Y, Ancho, Alto
         contenedor.add(dusuario); 
         contenedor.add(dcontrasenia);
         contenedor.add(aceptar); 

    }
// Iniciar la ventana mediante el metodo maín
public static void main(String [] args){
    
    Login V = new Login(); // Creación de una Ventana
    V.setVisible(true);                          // Se hace visible la ventana creada
    }
}    
