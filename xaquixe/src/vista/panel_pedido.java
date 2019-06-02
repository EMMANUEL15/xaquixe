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
import xaquixe.Conexion;

/**
 * @author Emanuel Lopez
 */
public class panel_pedido extends JFrame{
    //DEFINICION DE CAMPOS DE TEXTO
    protected JTextField TextBuscar = new JTextField(20);
    protected JTextField TextMaterialSelecioando = new JTextField(20);
    protected JTextField TextMaterialPedido = new JTextField(22);
    
    protected JTextField TextFolio = new JTextField(15);
    protected JTextField TextProveedor = new JTextField(15);
    protected JTextField TextPersonal = new JTextField(15);
    protected JTextField TextFecha = new JTextField(15);
    
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnBuscar = new JButton("Buscar");
    protected JButton btnProveedor = new JButton("Selecionar Proveedor");
    protected JButton btnGenerarPedido = new JButton("Generar Pedido");
    protected JButton btnAgregar = new JButton("<- Añadir a la lista");
    protected JButton btnEliminar = new JButton("Elminar de la lista ->");
    //DEFINICION  TABLA 
    private Object [][] materialPedido;
    private String[] columnas2 ={"ID MATERIAL","NOMBRE","CANTIDAD"};
    private DefaultTableModel Model = new DefaultTableModel(materialPedido,columnas2);
    private JTable tablePedido= new JTable(Model);
    private JScrollPane scrollPane2 = new JScrollPane(tablePedido);
    //DEFINICION  TABLA
    private Object [][] Materiales;
    private String[] columnas;
    private JTable tableMateriales = new JTable(new DefaultTableModel(Materiales,columnas));
    private JScrollPane scrollPane = new JScrollPane(tableMateriales);
    
    //FUENTES
    Font fuente  = new Font("Bodoni Bd BT", Font.BOLD, 20);
    Font fuente2 = new Font("Bodoni", Font.ITALIC, 15);
    Font fuente3 = new Font("Verdana", Font.BOLD,15);
    
    JPanel panelPedido = new JPanel();
    JPanel panelDatos = new JPanel();
    JPanel PanelMateriales = new JPanel();
    JPanel PanelPedido = new JPanel();
     JPanel panelBuscarMaterial = new JPanel();
     JPanel panelAgregarMaterial = new JPanel();
      JPanel panelEliminarMaterial = new JPanel();
      JPanel panelAUXILIAR = new JPanel();
      
    JPanel panelFolio = new JPanel();
    JPanel panelProveedor = new JPanel();
    JPanel panelPersonal = new JPanel();
    JPanel panelFecha = new JPanel();
    JPanel panelAplicacion = new JPanel();
      
    private String id_material;
    
    public panel_pedido(){
        panelEliminarMaterial.setBackground(Color.CYAN);
        PanelPedido.setBackground(Color.ORANGE);
        //COMPONENETES DEL PANEL DE BUAQUEDA
            TextBuscar.setFont(fuente2);
            JLabel etiqueta = new JLabel("Material:");
            etiqueta.setFont(fuente);
            
            btnBuscar.setFont(fuente3);
            TextMaterialSelecioando.setFont(fuente3);
            TextMaterialPedido.setFont(fuente3);
            
        //CONFIGURACION DE LA TABLA
            tableMateriales.setFont(fuente2);
            tableMateriales.setDragEnabled(false);
            tablePedido.setFont(fuente2);
            tablePedido.setDragEnabled(false);
        //CONFIGURACION DEL ENCABEZADO DE LA TABLA
            JTableHeader th; 
            th = tableMateriales.getTableHeader();
            th = tablePedido.getTableHeader();
        // 
        panelDatos.setLayout(new GridLayout(2,6,5,5));
        panelDatos.add(panelFolio);
        panelDatos.add(panelProveedor);
        panelDatos.add(panelPersonal);
        panelDatos.add(panelFecha);
        panelDatos.add(panelAplicacion);
        
        panelFolio.add(new JLabel("Folio"));
        panelFolio.add(TextFolio);
        panelProveedor.add(new JLabel("Proveedor"));
        panelProveedor.add(TextProveedor);
        
        panelPersonal.add(new JLabel("Personal"));
        panelPersonal.add(TextPersonal);
        panelFecha.add(new JLabel("Fecha"));
        panelFecha.add(TextFecha);
        panelAplicacion.add(btnGenerarPedido);
        panelAplicacion.add(btnProveedor);
        
        //PANEL DE ELIMNAR MATERIAL DE LA LISTA 
            panelEliminarMaterial.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelEliminarMaterial.add(TextMaterialPedido);
            panelEliminarMaterial.add(btnEliminar);
        //PANEL DE LA TABLA DE PEDIDO;
            PanelPedido.setLayout(new BorderLayout());
            PanelPedido.add(new JLabel("datos de pedido"),BorderLayout.NORTH);
            PanelPedido.add(scrollPane2,BorderLayout.CENTER);
            PanelPedido.add(panelEliminarMaterial,BorderLayout.SOUTH);
            
        //PANEL DE BUSQUEDA   NORTE
            panelBuscarMaterial.add(etiqueta);
            panelBuscarMaterial.add(TextBuscar);
            panelBuscarMaterial.add(btnBuscar);
        //PANEL DE AUTORIZACION SUR
            panelAgregarMaterial.setLayout(new FlowLayout(FlowLayout.LEFT));
            panelAgregarMaterial.add(TextMaterialSelecioando);
            panelAgregarMaterial.add(btnAgregar);
        //PANEL DE LA TABLA MaterialES 
            PanelMateriales.setLayout(new BorderLayout());
            PanelMateriales.add(panelBuscarMaterial,BorderLayout.NORTH);
            PanelMateriales.add(scrollPane,BorderLayout.CENTER);
            
        //BORDER
            Border line = BorderFactory.createLineBorder(Color.BLUE, 1);
            Border titulo = BorderFactory.createTitledBorder(line, "Datos", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE);
            Border titulo1 = BorderFactory.createTitledBorder(line, "Buscar materiales", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE);
            Border titulo2 = BorderFactory.createTitledBorder(line, "Pedido", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE);
            panelDatos.setBorder(titulo);
            PanelMateriales.setBorder(titulo1);
            PanelPedido.setBorder(titulo2);
        
        panelAUXILIAR.setLayout(new GridLayout(2,1,0,5));
        panelAUXILIAR.add(PanelMateriales);
        panelAUXILIAR.add(panelAgregarMaterial);
                   
        panelPedido.setLayout(new GridLayout(1,2,0,5));
        panelPedido.add(PanelPedido);
        panelPedido.add(panelAUXILIAR);
        
        setLayout(new BorderLayout());
        add(panelDatos,BorderLayout.NORTH);
        add(panelPedido,BorderLayout.CENTER);
        
        //setId_Material(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
        //datosAutorizados(modelAuto.searchAutorizacion((String) vista.getTable().getValueAt(tuplaSelecionada,0)));
        //setLocationRelativeTo(null);
        setVisible(true);
        setSize(1100,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
    }
    /**
     * genera un mensaje
     * @param mensaje- recibe una cadena como mensaje
     */
    public void Mensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
    /**
     * Datos para la tabla
     * @param dtm- contiene datos para la tabla
     */
    public void datosMaterial(DefaultTableModel dtm){
        this.tableMateriales.setModel(dtm);
    }
    public void datosPedido(DefaultTableModel dtm){
        this.tablePedido.setModel(dtm);
    }
    public void setTextAgregar(String d){ TextMaterialSelecioando.setText(d);}
    public void setTextPedido(String d){ TextMaterialPedido.setText(d);}
    public void setTextFolio(String d){ TextFolio.setText(d);}
    public void setTextProveedor(String d){ TextProveedor.setText(d);}
    public void setTextPersonal(String d){ TextPersonal.setText(d);}
    public void setTextFecha(String d){ TextFecha.setText(d);}
    public void setId_Material(String id_material){  this.id_material = id_material;}
    /**
     * getter de la tabla
     */
    public JTable getTableMaterial(){return tableMateriales;}
    public JTable getTablePedido(){return tablePedido;}
    public DefaultTableModel getModelo(){return Model;}
    
    /**
     * genera un mensaje de confirmacion
     * @param int- recibe un entero
     */
    public boolean confirmacion(int tupla){
            int conf = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro "+tableMateriales.getValueAt(tupla,0)+"?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if(conf == JOptionPane.YES_OPTION) 
                return true;
            else
                return false;
    }
    public String getBuscar(){return TextBuscar.getText();}
    public String getTextSelecioando(){return TextMaterialSelecioando.getText();}
    public String getTextFolio(){return TextFolio.getText();}
    public String getTextProveedor(){return TextProveedor.getText();}
    public String getTextPersonal(){return TextPersonal.getText();}
    public String getTextFecha(){return TextFolio.getText();}
    
    public void conectaControlador(controlador.controladorPedido c){
        //campo de texto
        TextBuscar.addActionListener(c);
        TextBuscar.setActionCommand("BUSCAR_MATERIAL");
        //botones
        btnBuscar.addActionListener(c);
        btnBuscar.setActionCommand("BUSCAR_MATERIAL");
        
        btnAgregar.addActionListener(c);
        btnAgregar.setActionCommand("AGREGAR");
        
        btnEliminar.addActionListener(c);
        btnEliminar.setActionCommand("ELIMINAR");
        
        btnGenerarPedido.addActionListener(c);
        btnGenerarPedido.setActionCommand("PEDIDO");
        
        btnProveedor.addActionListener(c);
        btnProveedor.setActionCommand("PROVEEDOR");
        
        tableMateriales.addMouseListener(c);
        tablePedido.addMouseListener(c);
    }
    public static void main(String [] args){
        panel_pedido vista_pedido = new panel_pedido();
        
        modelo.ModeloPedido modelo_pedido = new modelo.ModeloPedido();
        
        Conexion c= new Conexion("xaquixe");
        modelo_pedido.conectar(c.getConexion());
        
        controlador.controladorPedido controlador_pedido = new controlador.controladorPedido(vista_pedido, modelo_pedido,"postgres");
        vista_pedido.conectaControlador(controlador_pedido);
    }
}
