package vista;
import Componente.*;
import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author akuer
 * 
 */
public class VentanaPrincipal extends JFrame{
// Componentes a Utilizar en la Ventana
    String              ruta = "src/Imagenes/";
    private Container contenedor;// En este contenedor guardaremos todos nuestros componentes
    private JPanel panelMenu,paneltitulo,panelventas, panelcompras, panelempleados,panelproveedores; 
    private Boton btnmateriales,btnproductos, btnventas, btncotizaciones, btncompras, btnempleados, btnproveedores;
    private JPanel panelmateriales = new JPanel();
    private JPanel panelproductos = new JPanel();
    private JPanel panelcotizaciones = new JPanel();
// Constructor de la Ventana
    public VentanaPrincipal(){
         super();                    // usamos el contructor de la clase padre JFrame
         configurarVentana();        // configuramos la ventana
         inicializarComponentes(); 
    }
    
// Configuracion de la Ventana
    private void configurarVentana(){
        this.setTitle("                                                                                                        Vidrio Artesanal XA QUIXE S. De R.L Mi. Art.");                   // colocamos titulo a la ventana
        this.setSize(950,700);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        contenedor = getContentPane();                          // Contenedor que guarda los componentes de la ventana
        contenedor.setLayout(null);                             // Se administra manualmente la posición de los componentes
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        this.setBackground(java.awt.Color.WHITE);               //Se asigna el color del formulario JFRAME
        this.setUndecorated(false);                             //Metodo oculta la barra de titulo
//        ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/src/Imagenes/logo.png"));
  //      Image Image = ImageIcon.getImage();   // Establecer el logo de la empresa 
    //    this.setIconImage(Image); 
        
          // En este apartado creamos el fondo de la aplicación a utilizar.
        try {
            FondoSwing fondo = new FondoSwing(ImageIO.read(new File("image/vent2.jpg")));
            JPanel panel = (JPanel) this.getContentPane();
            panel.setBorder(fondo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

// Iniciar Componentes 
    private void inicializarComponentes()
    {
        //<<------------------------------------Creación y Configuración de los componentes ------------------------------------>>
         // Paneles
         panelMenu       = PanelImagen.crearPanel(contenedor, new Rectangle(20, 100, 180, 400),ruta + "imgbtns1.png", false); // X, Y, Ancho, Alto}
         paneltitulo     = PanelImagen.crearPanel(contenedor, new Rectangle(20, 20, 900, 70),ruta + "imgtitulo.png", false);
         //panelmateriales = PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false); 
         //panelproductos  = PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false); 
         //panelventas     = PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false); 
         //panelcotizaciones = PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false);
         //panelcompras    = PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false);
         //panelempleados  = PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false);
         //panelproveedores= PanelImagen.crearPanel(contenedor, new Rectangle(220, 100, 700, 500),ruta + "imgcontenedor.png", false);
         //panelmateriales.add(new JButton("ola"));
         panelproductos.setBounds(220, 100, 700, 500);
         contenedor.add(panelproductos);
         
         
         panelproductos.setLayout(new BorderLayout());
         panelproductos.add(panelcotizaciones,BorderLayout.CENTER);
         panelproductos.add(new JLabel(" "),BorderLayout.SOUTH);
         panelproductos.add(new JLabel(" "),BorderLayout.EAST);
         panelproductos.add(new JLabel(" "),BorderLayout.WEST);
         
         // Botones
         btncotizaciones = new Boton("Cotizaciones");
         btncotizaciones.setBounds(20,70, 140, 30); // X, Y, Ancho, Alto
         btnmateriales   = new Boton("Materiales");
         btnmateriales.setBounds(20,120, 140, 30); // X, Y, Ancho, Alto
         btnproductos    = new Boton("Productos");
         btnproductos.setBounds(20,170, 140, 30); // X, Y, Ancho, Alto
         btnproveedores  = new Boton("Proveedores");
         btnproveedores.setBounds(20, 220, 140, 30); // X, Y, Ancho, Alto
         btnempleados    = new Boton("Recursos H.");
         btnempleados.setBounds(20, 270, 140, 30); // X, Y, Ancho, Alto
         btnventas       = new Boton("Ventas");
         btnventas.setBounds(20, 320, 140, 30); // X, Y, Ancho, Alto
        //<<---------------------------------- Agregamos los componentes a la ventana------------------------------------------>>
        
        panelMenu.add(btncotizaciones);
        panelMenu.add(btnmateriales);
        panelMenu.add(btnproductos);
        panelMenu.add(btnproveedores);
        panelMenu.add(btnempleados);
        panelMenu.add(btnventas);
    }
    public void conectaControlador(controlador.controladorPrincipal c  ){
        btncotizaciones.addActionListener(c);
        btncotizaciones.setActionCommand("COTIZACION");
        
        btnmateriales.addActionListener(c);
        btnmateriales.setActionCommand("MATERIALES");
 
        btnproductos.addActionListener(c);
        btnproductos.setActionCommand("PRODUCTOS");
 
        btnproveedores.addActionListener(c);
        btnproveedores.setActionCommand("POVEEDORES");
		
	btnempleados.addActionListener(c);
	btnempleados.setActionCommand("EMPLEADOS");
        
        btnventas.addActionListener(c);
        btnventas.setActionCommand("VENTAS");
    }
    public void alternarPanel(JPanel panelActivo){
        this.panelcotizaciones.removeAll();
        this.panelcotizaciones.setLayout(new BorderLayout());
        this.panelcotizaciones.add(panelActivo,BorderLayout.CENTER);
        this.panelcotizaciones.revalidate();
        this.panelcotizaciones.repaint();
    }

}    
    
