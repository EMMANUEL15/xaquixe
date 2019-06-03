package xaquixe;
/**
 *
 * @author Wilbert
 */
import Componente.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private Boton aceptar, cancelar;
    // JLabel
    JLabel lblusuario, lblcontrasenia, lema, titulo, lema1;
    // JTextField
    JTextField dusuario =  new JTextField();
    JTextField dcontrasenia = new JPasswordField();
    // Cadenas de Texto 
    public Component confirmation;
    public String usuario,password;
    public xaquixe.Login cargar;
  
        public void datos(String us, String pas){
        usuario = "Admin"; 
        password = "admin";
        }
    
    Font fuente  = new Font("Roboto",Font.BOLD,14);
    
    public Login()
    {
        super();                    // usamos el contructor de la clase padre JFrame
         configurarVentana();        // configuramos la ventana
         inicializarComponentes();
         dusuario.setFocusable(true);
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
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setBackground(java.awt.Color.WHITE);               //Se asigna el color del formulario JFRAME
        this.setUndecorated(true);                             //Metodo oculta la barra de titulo
       // ImageIcon ImageIcon = new ImageIcon(getClass().getResource("Imagenes/logo.png"));
       // Image Image = ImageIcon.getImage();   // Establecer el logo de la empresa 
       // this.setIconImage(Image); 
    }
        
        // Iniciar Componentes 
    private void inicializarComponentes()
    {
        //<<------------------------------------Creación y Configuración de los componentes ------------------------------------>>
         //Etiquetas
         lema1 =  Label.crearLabel(contenedor, "EST. 2002",new Rectangle(260,35,180,10), Color.BLACK,Color.RED); 
         lema1.setFont(new Font("Microsoft Yi Baiti",Font.BOLD,12));
         titulo= Label.crearLabel(contenedor, "XAQUIXE",new Rectangle(200,55,180,35), Color.BLACK,Color.BLACK);
         titulo.setFont(new Font("Microsoft Yi Baiti",Font.BOLD,48));
         lema =  Label.crearLabel(contenedor, "GLASS_INNOVATION_STUDIO",new Rectangle(230,80,180,35), Color.BLACK,Color.BLACK); 
         lema.setFont(new Font("Microsoft Yi Baiti",Font.BOLD,12));
         lblusuario = Label.crearLabel(contenedor, "USUARIO: ",new Rectangle(160,145,180,18), Color.BLACK,Color.BLACK);
         lblusuario.setFont(new Font("Roboto",Font.BOLD,15));
         lblcontrasenia = Label.crearLabel(contenedor, "CONTRASEÑA: ",new Rectangle(160,185,180,18), Color.BLACK,Color.BLACK);
         lblcontrasenia.setFont(new Font("Roboto",Font.BOLD,15));
         // Botones 
         
         dusuario.setFont(fuente);
         dusuario.setBounds(280,140, 140, 30); // X, Y, Ancho, Alto
         dcontrasenia.setFont(fuente);
         dcontrasenia.setBounds(280,180, 140, 30); // X, Y, Ancho, Alto
         aceptar = new Boton("Aceptar");
         aceptar.addActionListener((new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }}));
         aceptar.setBounds(140,250, 140, 30); // X, Y, Ancho, Alto
         cancelar = new Boton ("Cancelar");
         cancelar.addActionListener((new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }}));
         cancelar.setBounds(310,250, 140, 30); // X, Y, Ancho, Alto
         contenedor.add(dusuario); 
         contenedor.add(dcontrasenia);
         contenedor.add(aceptar); 
         contenedor.add(cancelar);
    }
    
    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {
        datos(usuario, password);
        if(usuario.equals(dusuario.getText()) && password.equals(dcontrasenia.getText())){
                            //VISTAS
        vista.panel_producto vista_producto = new vista.panel_producto();
        vista.panel_proveedor vista_proveedor = new vista.panel_proveedor();
        vista.panel_material vista_material = new vista.panel_material();
        vista.panel_cliente vista_cliente = new vista.panel_cliente();
        vista.panel_empleado vista_empleado = new vista.panel_empleado();
        
        vista.panel_autorizacion vista_autorizacion = new vista.panel_autorizacion();
        
            //MODELO
        modelo.ModeloProducto modelo_producto = new modelo.ModeloProducto();
        modelo.ModeloProveedor modelo_proveedor = new modelo.ModeloProveedor();
        modelo.ModeloMaterial modelo_material = new modelo.ModeloMaterial();
        modelo.ModeloCliente modelo_cliente = new modelo.ModeloCliente();
        modelo.ModeloEmpleado modelo_empleado = new modelo.ModeloEmpleado();
        modelo.ModeloAutorizacion modelo_Autorizacion = new modelo.ModeloAutorizacion();
        
            //CONEXION A BASE DE DATOS
        Conexion c= new Conexion("xaquixe");
        modelo_producto.conectar(c.getConexion());
        modelo_proveedor.conectar(c.getConexion());
        modelo_material.conectar(c.getConexion());
        modelo_cliente.conectar(c.getConexion());
        modelo_empleado.conectar(c.getConexion());
        modelo_Autorizacion.conectar(c.getConexion());
        
            //CONEXION DE VISTA MODELO CONTROLADOR
        controlador.controladorCliente controlador_cliente  = new controlador.controladorCliente(vista_cliente, modelo_cliente);
        controlador.controladorEmpleado controlador_empleado  = new controlador.controladorEmpleado(vista_empleado, modelo_empleado);
        controlador.ControladorProducto controlador_producto  = new controlador.ControladorProducto(vista_producto, modelo_producto);
        controlador.controladorProveedor controlador_proveedor  = new controlador.controladorProveedor(vista_proveedor, modelo_proveedor);
        controlador.controladorMaterial controlador_material  = new controlador.controladorMaterial(vista_material, modelo_material);
                                                                    controlador_material.controladorAutorizacion(vista_autorizacion, modelo_Autorizacion);
        controlador.controladorAutorizacion controlador_autorizacion = new controlador.controladorAutorizacion(vista_autorizacion,modelo_Autorizacion);
        
            //VINCULAR VISTA Y CONTROLAR
        vista_cliente.conectaControlador(controlador_cliente);
        vista_empleado.conectaControlador(controlador_empleado);
        vista_producto.conectaControlador(controlador_producto);
        vista_proveedor.conectaControlador(controlador_proveedor); 
        vista_material.conectaControlador(controlador_material);
        vista_autorizacion.conectaControlador(controlador_autorizacion);
        
    vista.Vista_Principal V = new vista.Vista_Principal();
    controlador.controladorPrincipal CP = new controlador.controladorPrincipal(V);
    V.conectaControlador(CP);
    CP.cargarPaneles(vista_producto, vista_proveedor, vista_material,vista_cliente,vista_empleado);
    V.setVisible(true);
        this.dispose();
        }else if(dusuario.getText().equals("") && dcontrasenia.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Usuario y/o Contraseña estan vacios\nIngrese los por favor.");
        dusuario.setFocusable(true);
        }else if(dusuario.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Usuario está vacio\nIngrese lo por favor.");
        dusuario.setFocusable(true);
         }else if(dcontrasenia.getText().equals("")){
        JOptionPane.showMessageDialog(this,"Contraseña está vacio\nIngrese lo por favor.");
        dcontrasenia.setFocusable(true);
        }
        else if(dusuario.getText().compareTo(usuario)!=0 && dcontrasenia.getText().compareTo(password)!=0){
        JOptionPane.showMessageDialog(this,"Usuario y/o Contraseña no válidos\nIngrese nuevamente.");
         dusuario.setFocusable(true);
        }
        else if(dusuario.getText().compareTo(usuario)!=0){
        JOptionPane.showMessageDialog(this,"Usuario no válido\nIngrese nuevamente.");
        dusuario.setFocusable(true);
        }else if(dcontrasenia.getText().compareTo(password)!=0){
        JOptionPane.showMessageDialog(this,"Contraseña no válida\nIngrese nuevamente.");
        dcontrasenia.setFocusable(true);
    }
    }
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {
     Object [] opciones ={"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(confirmation,"Desea salir?","Confirmacion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            if (eleccion == JOptionPane.YES_OPTION)
             {
            System.exit(0);
            }else{}      
    } 
     public static void main(String [] args){
    Login v = new Login();
    v.setVisible(true);
} 
}  
