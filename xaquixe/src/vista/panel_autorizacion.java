package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @author Emanuel Lopez
 */
public class panel_autorizacion extends JFrame{
    //DEFINICION DE CAMPOS DE TEXTO
    protected JTextField TextBuscar = new JTextField(20);
    protected JTextField TextProveedorSelecioando = new JTextField(20);
    protected JTextField TextProveedorAutorizado = new JTextField(22);
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnBuscar = new JButton("Buscar");
    protected JButton btnAutorizar = new JButton("Autorizar");
    protected JButton btnEliminar = new JButton("Elminar");
    //DEFINICION  TABLA
    private Object [][] proveedores;
    private String[] columnas;
    private JTable tableProveedores = new JTable(new DefaultTableModel(proveedores,columnas));
    private JScrollPane scrollPane = new JScrollPane(tableProveedores);
    //DEFINICION  TABLA 
    private Object [][] autorizados;
    private String[] columnas2;
    private DefaultTableModel Model = new DefaultTableModel(autorizados,columnas2);
    private JTable tableAutorizacion = new JTable(Model);
    private JScrollPane scrollPane2 = new JScrollPane(tableAutorizacion);
    
    //FUENTES
    Font fuente  = new Font("Bodoni Bd BT", Font.BOLD, 20);
    Font fuente2 = new Font("Bodoni", Font.ITALIC, 15);
    Font fuente3 = new Font("Verdana", Font.BOLD,15);
    
    JPanel PanelProveedores = new JPanel();
    JPanel PanelAutorizacion = new JPanel();
     JPanel panelBuscarProveedor = new JPanel();
     JPanel panelAutorizarProveedor = new JPanel();
      JPanel panelEliminarAutorizacion = new JPanel();
      
      private String id_material;
    
    public panel_autorizacion(){
        super("Autorizacion");
        panelEliminarAutorizacion.setBackground(Color.CYAN);
        PanelAutorizacion.setBackground(Color.ORANGE);
        //COMPONENETES DEL PANEL DE BUAQUEDA
            TextBuscar.setFont(fuente2);
            JLabel etiqueta = new JLabel("Proveedor:");
            etiqueta.setFont(fuente);
            JLabel etiquetaP = new JLabel("Proveedor Seleccionado: ");
            etiquetaP.setFont(fuente);
            JLabel etiquetaA = new JLabel("Proveedor Autorizado: ");
            etiquetaA.setFont(fuente);
            
            btnBuscar.setFont(fuente3);
            TextProveedorSelecioando.setFont(fuente3);
            TextProveedorAutorizado.setFont(fuente3);
            
        //CONFIGURACION DE LA TABLA
            tableProveedores.setFont(fuente2);
            tableProveedores.setDragEnabled(false);
            tableAutorizacion.setFont(fuente2);
            tableAutorizacion.setDragEnabled(false);
        //CONFIGURACION DEL ENCABEZADO DE LA TABLA
            JTableHeader th; 
            th = tableProveedores.getTableHeader();
            th = tableAutorizacion.getTableHeader();
        
         //PANEL DE BUSQUEDA   NORTE
            panelBuscarProveedor.add(etiqueta);
            panelBuscarProveedor.add(TextBuscar);
            panelBuscarProveedor.add(btnBuscar);
        //PANEL DE AUTORIZACION SUR
            panelAutorizarProveedor.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelAutorizarProveedor.add(etiquetaP);
            panelAutorizarProveedor.add(TextProveedorSelecioando);
            panelAutorizarProveedor.add(btnAutorizar);
        //PANEL DE LA TABLA PROVEEDORES 
            PanelProveedores.setLayout(new BorderLayout());
            PanelProveedores.add(panelBuscarProveedor,BorderLayout.NORTH);
            PanelProveedores.add(scrollPane,BorderLayout.CENTER);
            PanelProveedores.add(panelAutorizarProveedor,BorderLayout.SOUTH);
        
        //PANEL DE ELIMNAR AUTORIZACION 
            panelEliminarAutorizacion.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelEliminarAutorizacion.add(etiquetaA);
            panelEliminarAutorizacion.add(TextProveedorAutorizado);
            panelEliminarAutorizacion.add(btnEliminar);
            
        //PANEL DE LA TABLA PROVEEDORES AUTORIZADOS;
            PanelAutorizacion.setLayout(new BorderLayout());
            PanelAutorizacion.add(scrollPane2,BorderLayout.CENTER);
            PanelAutorizacion.add(panelEliminarAutorizacion,BorderLayout.SOUTH);
            
        //BORDER
            Border line = BorderFactory.createLineBorder(Color.BLUE, 1);
            Border titulo1 = BorderFactory.createTitledBorder(line, "Proveedores", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE);
            Border titulo2 = BorderFactory.createTitledBorder(line, "Autorizacion", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE);
            PanelProveedores.setBorder(titulo1);
            PanelAutorizacion.setBorder(titulo2);
        
        setLayout(new GridLayout(2,1,0,5));
        add(PanelProveedores);
        add(PanelAutorizacion);
        
        setSize(900,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
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
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro "+tableProveedores.getValueAt(tupla,0)+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if(conf == JOptionPane.YES_OPTION) 
                return true;
            else
                return false;
    }
    public boolean confirmacion2(int tupla){
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro "+tableAutorizacion.getValueAt(tupla,0)+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if(conf == JOptionPane.YES_OPTION) 
                return true;
            else
                return false;
    }
    /**
     * Datos para la tabla
     * @param dtm- contiene datos para la tabla
     */
    public void datosProveedor(DefaultTableModel dtm){
        this.tableProveedores.setModel(dtm);
    }
    public void datosAutorizados(DefaultTableModel dtm){
        this.tableAutorizacion.setModel(dtm);
    }
    
    public void setTextSelecioando(String d){ TextProveedorSelecioando.setText(d);}
    public void setTextAutorizado(String d){ TextProveedorAutorizado.setText(d);}
    /**
     * getter de la tabla
     */
    public JTable getTableProveedor(){return tableProveedores;}
    public JTable getTableAutorizacion(){return tableAutorizacion;}
    
    public String getBuscar(){return TextBuscar.getText();}
    public String getTextSelecioando(){return TextProveedorSelecioando.getText();}
    public String getId_Material(){return id_material;}
    public void setId_Material(String id_material){
        this.id_material = id_material;
    }
    
    public void conectaControlador(controlador.controladorAutorizacion c  ){
        //campo de texto
        TextBuscar.addActionListener(c);
        TextBuscar.setActionCommand("BUSCAR_PROVEEDOR");
        //botones
        btnBuscar.addActionListener(c);
        btnBuscar.setActionCommand("BUSCAR_PROVEEDOR");
        
        btnAutorizar.addActionListener(c);
        btnAutorizar.setActionCommand("AUTORIZAR");
        
        btnEliminar.addActionListener(c);
        btnEliminar.setActionCommand("ELIMINAR");
        
        tableProveedores.addMouseListener(c);
        tableAutorizacion.addMouseListener(c);
    }
}
