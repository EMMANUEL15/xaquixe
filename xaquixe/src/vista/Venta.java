/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.*;
import javax.swing.border.*;
import Componente.Boton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Bernal Gonzalez Morachel Manic
 */
public class Venta extends JPanel{
    
    private Boton buscar =new Boton("Buscar");
    private Boton cancelar =new Boton("Cancelar"); 
    private Boton agregar =new Boton("Agregar");
    private Boton eliminar =new Boton("Eliminar");
    private Boton vender =new Boton("Vender");
    private Boton verVenta =new Boton("Ver Ventas");
    
    private JLabel etiquetaBuscar = new JLabel("Buscar por: ");
    
    private JLabel etiquetaFecha = new JLabel("Fecha: ");
    private JLabel etiquetaCliente = new JLabel("Cliente: ");
    private JLabel etiquetaTotal = new JLabel("Total: ");
    private JLabel etiquetaSubtotal = new JLabel("Subtotal: ");
    private JLabel etiquetaDescuento = new JLabel("Descuento: ");
    
    private JLabel labelImagen = new JLabel();
     
    
    private JLabel campoSubtotal = new JLabel();
    private JLabel campoTotal = new JLabel();
    private JLabel campoFecha = new JLabel();
    
    JComboBox comboBuscar= new JComboBox();
    JTextField campoBuscar = new JTextField();
    JTextField campoCliente = new JTextField();
    JTextField campoDescuento = new JTextField();
    
    
    
    private Object [][] registroProductos;
    private String[] columnasProductos;
    private Object [][] registroVenta;
    private String[] columnasVenta;
    private JTable productos = new JTable(new DefaultTableModel(registroProductos, columnasProductos));
    private JTable venta = new JTable(new DefaultTableModel(registroVenta,columnasVenta));
    private JScrollPane scrollPaneProductos = new JScrollPane(productos);
    private JScrollPane scrollPaneVenta = new JScrollPane(venta);
    
    Border bordeDatosr = new TitledBorder(new EtchedBorder(), "Datos");
    Border bordeImg = new TitledBorder(new EtchedBorder(), "Imagen");
    
    private JPanel  panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEADING));
    private JPanel  panelImagen = new JPanel(new FlowLayout());
    private JPanel  panelVentaDatos = new JPanel(new GridLayout(4,2,5,5));    
    private JPanel  panelCentral = new JPanel(new GridBagLayout());  
    private JPanel  panelDerechoTabla = new JPanel(new GridLayout(0, 1));   
    private JPanel  panelDerecho = new JPanel(new GridLayout(0,1,10,10));
    
    private JLabel imagen = new JLabel();
    
    
    String rfc_empleado;
    
    
    public Venta(){
        
        //setTitle("XAQUIXE VENTA");
        setLayout(new BorderLayout(10, 10));
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        panelBuscar.setBorder(new TitledBorder("Buscar"));
        comboBuscar.setPreferredSize(new Dimension(120,25));
        campoBuscar.setPreferredSize(new Dimension(120,25));
        panelBuscar.add(etiquetaBuscar);
        panelBuscar.add(comboBuscar);
        panelBuscar.add(campoBuscar);
        panelBuscar.add(buscar);
        
        JTableHeader th; 
        th = productos.getTableHeader();
        
        JTableHeader th2; 
        th2 = venta.getTableHeader();
        
        
        
        panelImagen.setBorder(new TitledBorder("Imagen"));
        //labelImagen.setPreferredSize(new Dimension(150,150));
        panelImagen.add(imagen);
        
        campoSubtotal.setBorder(new LineBorder(Color.BLACK));
        campoTotal.setBorder(new LineBorder(Color.BLACK));
        
        panelVentaDatos.setBorder(new TitledBorder("Datos"));
        panelVentaDatos.setPreferredSize(new Dimension(200,150));
        panelVentaDatos.add(etiquetaCliente);
        panelVentaDatos.add(campoCliente);
        panelVentaDatos.add(etiquetaSubtotal);
        panelVentaDatos.add(campoSubtotal);
        panelVentaDatos.add(etiquetaDescuento);
        panelVentaDatos.add(campoDescuento);
        panelVentaDatos.add(etiquetaTotal);
        panelVentaDatos.add(campoTotal);
        
        
        scrollPaneProductos.setPreferredSize(new Dimension(400,300));
        scrollPaneVenta.setPreferredSize(new Dimension(400,150));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // panel gridbaog layout
            
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.gridwidth=2;
            gbc.gridheight=2;
            gbc.weightx=1.5;
            gbc.weighty=1.2;
            gbc.fill=GridBagConstraints.BOTH;
            panelCentral.add(scrollPaneProductos,gbc);
             
            gbc.gridx=2;
            gbc.gridy=0;
            gbc.gridwidth=1;
            gbc.gridwidth=1;
            gbc.weightx=0.0;
            gbc.weighty=0.0;
            gbc.fill=GridBagConstraints.NONE;
            panelCentral.add(panelImagen,gbc);
        
            gbc.gridx=0;
            gbc.gridy=2;
            gbc.gridwidth=2;
            gbc.gridwidth=2;
            gbc.weightx=1.5;
            gbc.weighty=1.2;
            gbc.fill=GridBagConstraints.BOTH;
            panelCentral.add(scrollPaneVenta,gbc);
            
            gbc.gridx=2;
            gbc.gridy=2;
            gbc.gridwidth=1;
            gbc.gridheight=1;
            gbc.weightx=0.0;
            gbc.weighty=0.5;
            gbc.fill=GridBagConstraints.VERTICAL;
            panelCentral.add(panelVentaDatos,gbc);
            
            
        panelDerecho.add(etiquetaFecha);
        panelDerecho.add(campoFecha);
        panelDerecho.add(agregar);
        panelDerecho.add(new JLabel());
        panelDerecho.add(verVenta);
        panelDerecho.add(new JLabel());
        panelDerecho.add(eliminar);
        panelDerecho.add(cancelar);
        panelDerecho.add(vender);
        
        //add(panelBuscar,BorderLayout.NORTH);
        add(panelCentral,BorderLayout.CENTER);
        add(panelDerecho,BorderLayout.EAST);
        
        setBackground(new Color(118,118,118,150));
        
        buscar.setActionCommand("buscar");
        agregar.setActionCommand("agregar"); 
        eliminar.setActionCommand("eliminar");
        cancelar.setActionCommand("cancelar");
        vender .setActionCommand("vender");
        verVenta .setActionCommand("verVenta");
        
        productos.setName("producto");
        venta.setName("venta");
        
        //pack();
    }
    
    public void conectar(controlador.ControladorVenta c){
        buscar.addActionListener(c);
        agregar.addActionListener(c);
        eliminar.addActionListener(c);
        cancelar.addActionListener(c);
        vender.addActionListener(c);
        productos.addMouseListener(c);
        venta.addMouseListener(c);
        verVenta.addActionListener(c);
        campoDescuento.addKeyListener(c);
    }
    
    public void SetImagen(String image){
            ImageIcon img = new ImageIcon(image);
            ImageIcon img2 = new ImageIcon(img.getImage().getScaledInstance(130,130, Image.SCALE_SMOOTH));
            imagen.setIcon(img2);
    }
    
    
    public void setProductos(DefaultTableModel dtm){
        productos.setModel(dtm);
    }
    
    public void setVenta(DefaultTableModel dtm){
        venta.setModel(dtm);
    }
        
    public JTable getProductos(){
        return productos;
    }
    
    public JTable getVenta(){
        return venta;
    }
    
     public DefaultTableModel getDtmVenta(){
        return (DefaultTableModel) venta.getModel();
    }
    
     public void limpiarVenta(){
         venta.setModel(new DefaultTableModel(registroVenta,columnasVenta));
     }
    
    public void insertarTotal(int tupla, Object total){
        venta.setValueAt(total, tupla, 4);
    }
    
    public void setImagen(ImageIcon img){
    imagen.setIcon(img);
    }
    
    public void mensaje(String msj){
        JOptionPane.showMessageDialog(null,msj);
    }

    public String getCampoCliente() {
        return campoCliente.getText();
    }

    public void setCampoCliente(String campoCliente) {
        this.campoCliente.setText(campoCliente);
    }

    
    
    public String getCampoSubtotal() {
        return campoSubtotal.getText();
    }

    public void setCampoSubtotal(String campoSubtotal) {
        this.campoSubtotal.setText(campoSubtotal);
    }

    public String getCampoTotal() {
        return campoTotal.getText();
    }

    public void setCampoTotal(String campoTotal) {
        this.campoTotal.setText(campoTotal);
    }

    public String getCampoDescuento() {
        return campoDescuento.getText().trim();
    }

    public void setCampoDescuento(String campoDescuento) {
        this.campoDescuento.setText(campoDescuento);
    }
    
           
    public void UpdateCantidad(int tupla,Object cantidad){
        this.venta.setValueAt(cantidad, tupla,2);
    }
    
    public void UpdateCantidadProducto(int tupla,Object cantidad){
        this.productos.setValueAt(cantidad, tupla,3);
    }
    
        
    public int  mensajeEliminar(){
        String numero=JOptionPane.showInputDialog(null,"catidad a eliminar");
        try{
        if(numero.isEmpty()) return -1;
        else
        return Integer.valueOf(numero);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Debe introducir un número");
            return -1;
        }
    }

    public String getRfc_empleado() {
        return rfc_empleado;
    }

    public void setRfc_empleado(String rfc_empleado) {
        this.rfc_empleado = rfc_empleado;
    }
    
    
        
       
          
    public static void main(String[] args){
        Venta v= new Venta();
        
    }
}
