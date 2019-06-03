package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
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
public class Ventana_Proveedor extends JFrame{
    //DEFINICION DE CAMPOS DE TEXTO
    protected JTextField TextBuscar = new JTextField(20);
    protected JTextField TextProveedorSelecioando = new JTextField(20);
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnBuscar = new JButton("Buscar");
    protected JButton btnAutorizar = new JButton("Seleccionar");
    //DEFINICION  TABLA
    private Object [][] proveedores;
    private String[] columnas;
    private JTable tableProveedores = new JTable(new DefaultTableModel(proveedores,columnas));
    private JScrollPane scrollPane = new JScrollPane(tableProveedores);
    private  int tuplaSelecionada;
    //FUENTES
    Font fuente  = new Font("Bodoni Bd BT", Font.BOLD, 20);
    Font fuente2 = new Font("Bodoni", Font.ITALIC, 15);
    Font fuente3 = new Font("Verdana", Font.BOLD,15);
    
    JPanel PanelProveedores = new JPanel();
     JPanel panelBuscarProveedor = new JPanel();
     JPanel panelAutorizarProveedor = new JPanel();
      
      private String id_material;
      private  String rfc;
      private  String Nombre;
    
    public Ventana_Proveedor(){
        super("Selecionar Proveedor");
        //COMPONENETES DEL PANEL DE BUAQUEDA
            TextBuscar.setFont(fuente2);
            JLabel etiqueta = new JLabel("Proveedor:");
            etiqueta.setFont(fuente);
            JLabel etiquetaP = new JLabel("Proveedor Seleccionado: ");
            etiquetaP.setFont(fuente);
            
            btnBuscar.setFont(fuente3);
            TextProveedorSelecioando.setFont(fuente3);
            
        //CONFIGURACION DE LA TABLA
            tableProveedores.setFont(fuente2);
            tableProveedores.setDragEnabled(false);
        //CONFIGURACION DEL ENCABEZADO DE LA TABLA
            JTableHeader th; 
            th = tableProveedores.getTableHeader();
            tableProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }});
        
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
            
        //BORDER
            Border line = BorderFactory.createLineBorder(Color.BLUE, 1);
            Border titulo = BorderFactory.createTitledBorder(line, "Proveedores", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE);
            PanelProveedores.setBorder(titulo);
        
        setLayout(new GridLayout(1,1,0,5));
        add(PanelProveedores);
        
        setSize(900,250);
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
    /**
     * Datos para la tabla
     * @param dtm- contiene datos para la tabla
     */
    public void datosProveedor(DefaultTableModel dtm){
        this.tableProveedores.setModel(dtm);
    }
    
    public void setTextSelecioando(String d){ TextProveedorSelecioando.setText(d);}
    public void setRfc(String d){ this.rfc = d;}
    /**
     * getter de la tabla
     */
    public JTable getTableProveedor(){return tableProveedores;}
    
    public String getBuscar(){return TextBuscar.getText();}
    public String getTextSelecioando(){return TextProveedorSelecioando.getText();}
    public String getId_Material(){return id_material;}
    public void setId_Material(String id_material){
        this.id_material = id_material;
    }
    public String getRfc(){ return rfc;}
    public String getNombre(){ return Nombre;}
    private void tableMouseClicked(MouseEvent evt) {
        this.tuplaSelecionada = tableProveedores.rowAtPoint(evt.getPoint());
        try{
            if(tuplaSelecionada>=0){
            this.Nombre = String.valueOf(tableProveedores.getValueAt(tuplaSelecionada,1));
            this.rfc = String.valueOf(tableProveedores.getValueAt(tuplaSelecionada,0));
            this.setTextSelecioando(Nombre+" "+rfc);
            
            }
        }catch(Exception e){}
    }
    public void conectaControlador(controlador.controladorPedido c  ){
        //campo de texto
        TextBuscar.addActionListener(c);
        TextBuscar.setActionCommand("BUSCAR_PROVEEDOR");
        //botones
        btnBuscar.addActionListener(c);
        btnBuscar.setActionCommand("BUSCAR_PROVEEDOR");
        
        btnAutorizar.addActionListener(c);
        btnAutorizar.setActionCommand("SELECCIONADO");
        
        tableProveedores.addMouseListener(c);
    }
}