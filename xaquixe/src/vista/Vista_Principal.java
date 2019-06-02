package vista;
import Componente.Boton;
import Componente.FondoSwing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Componente.miJPanel;
/**
 * @author Emanuel Lopez
 */
public class Vista_Principal extends JFrame{
    private Container contenedor = getContentPane();
    
    private miJPanel panel1= new miJPanel("border");
    private miJPanel panel2= new miJPanel("border");
    private miJPanel panel3= new miJPanel("border");
    
    private JPanel paneltitulo= new JPanel();
    private JPanel panelMenu= new JPanel();
    private JPanel panelventanas= new JPanel();
    private miJPanel panelbotone= new miJPanel();
    private JPanel panelComponetes= new JPanel();
    
    private Boton btncotizaciones = new Boton("Cotizaciones","image/cotizacion1.png");
    private Boton btnmateriales   = new Boton("Materiales  ","image/material1.png");
    private Boton btnproductos    = new Boton("Productos   ","image/producto1.png");
    private Boton btnproveedores  = new Boton("Proveedores ","image/proveedor1.png");
    private Boton btnclientes     = new Boton("Clientes    ","image/cliente1.png");
    private Boton btnempleados    = new Boton("Recursos H. ","image/recursosH1.png");
    private Boton btnventas       = new Boton("Ventas        ","image/venta1.png");
    private Boton btncerrar       = new Boton("Cerrar        ","image/cerrar1.png");
    /**
    * configuracion de JFrame principal
    */
    public Vista_Principal(){
         try {
            FondoSwing fondo = new FondoSwing(ImageIO.read(new File("image/vent2.jpg")));
            JPanel panel = (JPanel) this.getContentPane();
            panel.setBorder(fondo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }       
         
         Agregarcomponetes();
                 
        setSize(400,300);
        setDefaultCloseOperation (EXIT_ON_CLOSE );//setUndecorated(true);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo ( null );
        setVisible ( true ); 
    }
    /**
     * se agrega componente a la ventana principal
     */
    private void Agregarcomponetes(){
        Color gris =new Color(235, 235, 224);
        
        paneltitulo.setBackground(new Color(118,118,118,150));
        panelMenu.setBackground(new Color(118,118,118,150));
        panelComponetes.setBackground(new Color(118,118,118,150));
        //panelbotone.setBackground(new Color(118,118,118,150));
        
        JLabel logo = new JLabel();
        ImageIcon img = new ImageIcon("image/logo.png");
        ImageIcon img2 = new ImageIcon(img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        logo.setIcon(img2);
        paneltitulo.add(logo);
        paneltitulo.add(new JLabel("Vidrio Artesanal XA QUIXE S. De R.L Mi. Art."));
        
        
        panelMenu.setLayout(new BorderLayout());
        panelMenu.add(new JLabel("  "),BorderLayout.EAST);
        panelMenu.add(new JLabel("  "),BorderLayout.WEST);
        panelMenu.add(panelbotone,BorderLayout.CENTER);
        
        panelbotone.setLayout(new GridLayout(15,1,8,8));
        panelbotone.add(new JLabel(" OPCIONES "));
        panelbotone.add(btncotizaciones);
        panelbotone.add(btnmateriales);
        panelbotone.add(btnproductos);
        panelbotone.add(btnproveedores);
        panelbotone.add(btnclientes);
        panelbotone.add(btnempleados);
        panelbotone.add(btnventas);
        panelbotone.add(btncerrar);
        
        panelventanas.setLayout(new BorderLayout());
        panelventanas.add(panelComponetes,BorderLayout.CENTER);
        
        panel3.add(panelMenu,BorderLayout.WEST);
        panel3.add(new JLabel("     "),BorderLayout.EAST);
        
        panel2.add(paneltitulo,BorderLayout.NORTH);
        panel2.add(new JLabel("  "),BorderLayout.SOUTH);
        
        panel1.add(panel2,BorderLayout.NORTH);
        panel1.add(panel3,BorderLayout.WEST);
        panel1.add(panelventanas,BorderLayout.CENTER);
        panel1.add(new JLabel("   "),BorderLayout.SOUTH);
        
        contenedor.setLayout(new BorderLayout());
        contenedor.add(new JLabel("   "),BorderLayout.NORTH);
        contenedor.add(new JLabel("      "),BorderLayout.EAST);
        contenedor.add(new JLabel("      "),BorderLayout.WEST);
        contenedor.add(panel1,BorderLayout.CENTER);
        contenedor.add(new JLabel("   "),BorderLayout.SOUTH);
        
    }
    /**
     * Metodo de para controlar los botones
     * @param c - recive un controlador para los eventos de los botones
     */
     public void conectaControlador(controlador.controladorPrincipal c  ){
        btncotizaciones.addActionListener(c);
        btncotizaciones.setActionCommand("COTIZACION");
        
        btnmateriales.addActionListener(c);
        btnmateriales.setActionCommand("MATERIALES");
 
        btnproductos.addActionListener(c);
        btnproductos.setActionCommand("PRODUCTOS");
 
        btnproveedores.addActionListener(c);
        btnproveedores.setActionCommand("POVEEDORES");
        
        btnclientes.addActionListener(c);
        btnclientes.setActionCommand("CLIENTES");
		
	btnempleados.addActionListener(c);
	btnempleados.setActionCommand("EMPLEADOS");
        
        btnventas.addActionListener(c);
        btnventas.setActionCommand("VENTAS");
        
        btncerrar.addActionListener(c);
        btncerrar.setActionCommand("CERRAR");
    }
    /**
     * confirma con un mensaje para cerrar la ventana
     * @return un true para cerrar la ventana.
     */
    public boolean confirmacion(){
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cerrar?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if(conf == JOptionPane.YES_OPTION) 
                return true;
            else
                return false;
    }
    /**
     * alterna los paneles sengun los eventos de los botones
     * @param panelActivo -recibe con el que va a remplazar el panel acutal
     */
    public void alternarPanel(JPanel panelActivo){
        this.panelComponetes.removeAll();
        this.panelComponetes.setLayout(new BorderLayout());
        this.panelComponetes.add(panelActivo,BorderLayout.CENTER);
        this.panelComponetes.revalidate();
        this.panelComponetes.repaint();
    }
}
