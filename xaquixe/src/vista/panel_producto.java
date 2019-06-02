package vista;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import Componente.tabla;
/**
 *
 * @author Emanuel lopez
 */
public class panel_producto extends JPanel{
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    protected JTextField TextBuscar = new JTextField(20);
    protected JTextField campo1 = new JTextField();
    protected JTextField campo2 = new JTextField();
    protected JTextField campo3 = new JTextField();
    protected JTextField campo4 = new JTextField();
    protected JTextField campo5 = new JTextField();
    
    //DEFINICIÓN DE LOS BOTONES
    JButton btnBuscar= new JButton("Buscar");
    protected JButton btnAgregar = new JButton("Añadir");
    protected JButton btnEliminar = new JButton("Elminar");
    protected JButton btnActualizar = new JButton("Actualizar");
    protected JButton btnNuevo = new JButton("Nuevo");
    protected JButton btnImagen = new JButton("Selecioanr imagen");
    //DEFINICION DE TABLAS
    private Object [][] registro;
    private String[] columnas;
    private tabla table = new tabla(new DefaultTableModel(registro,columnas));
    private JScrollPane scrollPane = new JScrollPane(table);
    
    private String label[]={"SKU","ITEM","MEDIDA","CANTIDAD","PRECIO"};
    private ArrayList<JTextField> camposTexto= new ArrayList<JTextField>();
    
    Font fuente  = new Font("Bodoni Bd BT", Font.BOLD, 20);
    Font fuente2 = new Font("Bodoni", Font.ITALIC, 15);
    Font fuente3 = new Font("Verdana", Font.BOLD,15);
    Font fuente4  = new Font("Verdana", Font.BOLD,12);
     
    private JPanel panelBusqueda = new JPanel();
    private JPanel PanelDatos = new JPanel();
    private JPanel panelCampos = new JPanel();
    private JPanel panelBotones = new JPanel();
    private JPanel detalles = new JPanel();
    
    private JLabel imagen = new JLabel();
    
    /**
    * componete general
    * CONSTRUCTOR
    */
    public panel_producto(){
        //COMPONENETES DEL PANEL DE BUAQUEDA
            TextBuscar.setFont(fuente2);
            JLabel etiqueta = new JLabel("Producto:");
            etiqueta.setFont(fuente);
            
        //PANEL DE BUSQUEDA
            panelBusqueda.add(etiqueta);
            panelBusqueda.add(TextBuscar);
            panelBusqueda.add(btnBuscar);
        
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
            camposTexto.add(campo5);
            
            panelCampos.setLayout(new GridLayout(5,1,10,5));
            int i=0;
                for(JTextField jtx: camposTexto){
                    JPanel Columna1 = new JPanel();
                    Columna1.setLayout(new GridLayout(1,2,5,5));
                    JLabel etiq = new JLabel(label[i]);
                    etiq.setFont(fuente3);
                    jtx.setFont(fuente3);
                    Columna1.add(new JLabel());
                    Columna1.add(etiq);
                    Columna1.add(jtx);
                    panelCampos.add(Columna1);
                    i++;
                }     
        //PANEL DE IMAGEN
            JPanel panelImagen = new JPanel();
            panelImagen.add(imagen);
        //PANEL DE BOTONES
            JPanel aux = new JPanel();
            aux.add(panelBotones);
            panelBotones.setLayout(new GridLayout(5,1,5,5));
            panelBotones.add(btnImagen);
            panelBotones.add(btnAgregar);
            panelBotones.add(btnEliminar);
            panelBotones.add(btnActualizar);
            panelBotones.add(btnNuevo);
        
            detalles.setLayout(new GridLayout(1,3,5,5));
            detalles.add(panelImagen); 
            detalles.add(panelCampos);   
            detalles.add(aux); 
        
        //PANEL PRINCIPAL
            setLayout(new BorderLayout());
            add(PanelDatos,BorderLayout.CENTER);
            add(detalles,BorderLayout.SOUTH);
            setBackground(new Color(118,118,118,150));
    }
     /**
     * Agrega una nueva imagen
     * @param image- recibe una cadena como nombre de la imagen
     */
    public void SetImagen(String image){
            ImageIcon img = new ImageIcon(image);
            ImageIcon img2 = new ImageIcon(img.getImage().getScaledInstance(260,150, Image.SCALE_SMOOTH));
            imagen.setIcon(img2);
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
    public String getCampo5() {return campo5.getText();}
    public String getBuscar(){return TextBuscar.getText();}
    /**
     * setters de los campos de texto
     * @param s- recibe una cadena para los campos
     */
    public void setCampo1(String s) {this.campo1.setText(s);}
    public void setCampo2(String s) {this.campo2.setText(s);}
    public void setCampo3(String s) {this.campo3.setText(s);}
    public void setCampo4(String s) {this.campo4.setText(s);}
    public void setCampo5(String s) {this.campo5.setText(s);}
    /**
     * copia la imagen
     * @param ruta- recibe una cadena de la ruta de origen de la imagen
     */
    public boolean moverimagen(String ruta){
        boolean band = true;
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        int valor = fileChooser.showOpenDialog(fileChooser);
        if (valor == JFileChooser.APPROVE_OPTION) {
            try {
                File origen = new File(fileChooser.getSelectedFile().getAbsolutePath());
                File destino = new File(ruta);
                    InputStream in = new FileInputStream(origen);
                    OutputStream out = new FileOutputStream(destino);
                    
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();
            }catch (Exception e) {
                band =false;
            }finally {
                if (entrada != null) {
                    entrada.close();
                }
            }
        } else { Mensaje("No se ha seleccionado ningún fichero"); }
        return band;
    }
    /**
     * elimina la imagen
     * @param archivo- nombre de la imagen
     */
    public void emiminar(String archivo){
        File fichero = new File(archivo);
        if(fichero.exists()) {
            fichero.delete();
        }
    }
     /**
     * controlador
     * @param c- recibe un controlador para los eventos
     */
    public void conectaControlador(controlador.ControladorProducto c  ){
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
        
        btnImagen.addActionListener(c);
	btnImagen.setActionCommand("CAMBIAR");
        
        table.addMouseListener(c);
    }
}