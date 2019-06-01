package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ImageIcon;

/**
 * @author Emanuel Lopez
 */
public class panel_cliente extends JPanel{
    //DEFINICION DE CAMPOS DE TEXTO
    protected JTextField TextBuscar = new JTextField(20);
    protected JTextField campo1 = new JTextField();
    protected JTextField campo2 = new JTextField();
    protected JTextField campo3 = new JTextField();
    protected JTextField campo4 = new JTextField();
    
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnBuscar= new JButton("Buscar");
    protected JButton btnAgregar = new JButton("Añadir");
    protected JButton btnEliminar = new JButton("Elminar");
    protected JButton btnActualizar = new JButton("Actualizar");
    protected JButton btnNuevo = new JButton("Nuevo");
    protected JButton btnTele = new JButton("Editar");
    protected JButton btnDireccion = new JButton("Editar");
    
    private Object [][] registro;
    private String[] columnas;
    private JTable table = new JTable(new DefaultTableModel(registro,columnas));
    private JScrollPane scrollPane = new JScrollPane(table);
    
    private String label[]={"RFC:","NOMBRE:","APELLIDO PATERNO:","APELLIDO MATERNO"};
    private ArrayList<JTextField> camposTexto= new ArrayList<JTextField>();
    
    //DEFINICION DE FUENTES
    /*Font fuente  = new Font("Bodoni Bd BT", Font.BOLD, 20);
    Font fuente2 = new Font("Bodoni", Font.ITALIC, 15);
    Font fuente3 = new Font("Verdana", Font.BOLD,15);*/
    
    //DEFINICION DE PANEL
    private JPanel panelBusqueda = new JPanel();
    private JPanel PanelDatos = new JPanel();
    private JPanel panelCampos = new JPanel();
    private JPanel panelBotones = new JPanel();
    private JPanel Acciones = new JPanel();
    
    //DEFINICION DE ETIQUETAS
    private JLabel labelTelefono = new JLabel("TELEFONO: ");
    private JLabel labelCorreo = new JLabel("Direccion: ");
    //DEFINICION DE COMBOX
    private JComboBox tele = new JComboBox();
    private JComboBox mail = new JComboBox();
    
   /**
    * componete general
    * CONSTRUCTOR
    */
    public panel_cliente(){
        //COMPONENETES DEL PANEL DE BUAQUEDA
            //TextBuscar.setFont(fuente2);
            JLabel etiqueta = new JLabel("cliente:");
            //etiqueta.setFont(fuente);
            
        //PANEL DE BUSQUEDA
            panelBusqueda.add(etiqueta);
            panelBusqueda.add(TextBuscar);
            panelBusqueda.add(btnBuscar);
               
        //CONFIGURACION DE LA TABLA
            //table.setFont(fuente2);
            table.setDragEnabled(false);
        //CONFIGURACION DEL ENCABEZADO DE LA TABLA
            JTableHeader th; 
            th = table.getTableHeader(); 
        
        //PANEL PARA LA TABLA 
            PanelDatos.setLayout(new BorderLayout());
            PanelDatos.add(panelBusqueda,BorderLayout.NORTH);
            PanelDatos.add(scrollPane,BorderLayout.CENTER);          // tabla
            PanelDatos.add(new JLabel("   "),BorderLayout.WEST);
            PanelDatos.add(new JLabel("   "),BorderLayout.EAST);
            PanelDatos.add(new JLabel("   "),BorderLayout.SOUTH);
        
            camposTexto.add(campo1);
            camposTexto.add(campo2);
            camposTexto.add(campo3);
            camposTexto.add(campo4);
            
            panelCampos.setLayout(new GridLayout(3,3,10,5));
            int i=0;
                for(JTextField jtx: camposTexto){
                    JPanel Columna1 = new JPanel();
                    Columna1.setLayout(new GridLayout(2,1,5,5));
                    //Columna1.setLayout(new FlowLayout(FlowLayout.LEFT));
                    JLabel etiq = new JLabel(label[i]);
                    //etiq.setFont(fuente3);
                    //jtx.setFont(fuente3);
                    Columna1.add(etiq);
                    Columna1.add(jtx);
                    panelCampos.add(Columna1);
                    i++;
                }
                      
        //PANEL DE BOTONES
            JPanel auxBotones = new JPanel();
            auxBotones.add(panelBotones);
            panelBotones.setLayout(new GridLayout(5,1,5,5));
            panelBotones.add(btnAgregar);
            panelBotones.add(btnEliminar);
            panelBotones.add(btnActualizar);
            panelBotones.add(btnNuevo);
            
            Acciones.setLayout(new GridLayout(1,1,5,5));
            Acciones.add(panelCampos); 
            
            /*labelCorreo.setFont(fuente3);
            labelTelefono.setFont(fuente3);
            tele.setFont(fuente3);
            mail.setFont(fuente3);*/
            
            JPanel panelCombox = new JPanel();
            panelCombox.add(labelCorreo);
            panelCombox.add(mail);
            panelCombox.add(btnDireccion);
            panelCombox.add(new JLabel("         "));
            panelCombox.add(labelTelefono);
            panelCombox.add(tele);
            panelCombox.add(btnTele);
            
            JPanel TextBotone = new JPanel();
            TextBotone.setLayout(new BorderLayout());
            TextBotone.add(new JLabel("  "),BorderLayout.WEST);
            TextBotone.add(Acciones,BorderLayout.CENTER);
            TextBotone.add(auxBotones,BorderLayout.EAST);
            TextBotone.add(panelCombox,BorderLayout.SOUTH);
        
        //PANEL PRINCIPAL
            setLayout(new BorderLayout());
            add(PanelDatos,BorderLayout.CENTER);
            add(TextBotone,BorderLayout.SOUTH);
        
    }
     /**
     * genera un mensaje
     * @param mensaje- recibe una cadena como mensaje
     */
    public void Mensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
    /**
     * genera un mensaje de confirmacion
     * @param int- recibe un entero
     */
    public boolean confirmacion(int tupla){
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro "+table.getValueAt(tupla,0)+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if(conf == JOptionPane.YES_OPTION) 
                return true;
            else
                return false;
    }
    /**
     * Datos para la tabla
     * @param dtm- contiene datos para la tabla
     */
    public void datos(DefaultTableModel dtm){
        this.table.setModel(dtm);
    }
    /**
     * getter de la tabla
     */
    public JTable getTable(){
        return table;
    }
    /**
     * getters de los campos de texto
     */
    public String getCampo1() {return campo1.getText();}
    public String getCampo2() {return campo2.getText();}
    public String getCampo3() {return campo3.getText();}
    public String getCampo4() {return campo4.getText();}
    public String getBuscar(){return TextBuscar.getText();}
    /**
     * setters de los campos de texto
     * @param s- recibe una cadena para los campos
     */
    public void setCampo1(String s) {this.campo1.setText(s);}
    public void setCampo2(String s) {this.campo2.setText(s);}
    public void setCampo3(String s) {this.campo3.setText(s);}
    public void setCampo4(String s) {this.campo4.setText(s);}
    /**
     * datos para combox
     * @param Telefonos- recibe una lista de los telefono de los clientes
     * @param Correos- recibe una lista de los correo de los clientes
     */
    public void setDatosCombo(ArrayList<String> Telefonos,ArrayList<String> Correos){
        tele.removeAllItems();
        mail.removeAllItems();
         for(String t: Telefonos){
             tele.addItem(t);
         }
         for(String c: Correos){
             mail.addItem(c);
         }
    }
    /**
     * controlador
     * @param c- recibe un controlador para los eventos
     */
    public void conectaControlador(controlador.controladorCliente c  ){
        //botones
        btnBuscar.addActionListener(c);
        btnBuscar.setActionCommand("BUSCAR");
        
        btnAgregar.addActionListener(c);
        btnAgregar.setActionCommand("INSERTAR");
 
        btnEliminar.addActionListener(c);
        btnEliminar.setActionCommand("BORRAR");
 
        btnActualizar.addActionListener(c);
        btnActualizar.setActionCommand("MODIFICAR");
		
	btnNuevo.addActionListener(c);
	btnNuevo.setActionCommand("NUEVO");
        ////**************
        btnDireccion.addActionListener(c);
        btnDireccion.setActionCommand("UPMAIL");
		
	btnTele.addActionListener(c);
	btnTele.setActionCommand("UPTELE");
        // tabla
        table.addMouseListener(c);
    }
}
