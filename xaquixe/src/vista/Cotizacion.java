/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Componente.Boton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bernal Gonzalez Morachel Manic
 */
public class Cotizacion extends JPanel{
    
    private JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel panelTablas = new JPanel(new GridLayout(0, 1, 0, 10));
    private JPanel panelDerecho = new JPanel(new GridLayout(0,1,10,10));
    private JPanel panelDatos = new JPanel(new GridLayout(0,2,5,5));
    private JPanel panelCuenta = new JPanel(new GridLayout(4,2,5,5));
    private JPanel panelbotones = new JPanel(new GridLayout(0,2,5,5));
    private JPanel panelAgregar = new JPanel(new FlowLayout(FlowLayout.LEFT));
 
    private Boton buscar =new Boton("Buscar");
    private Boton cancelar =new Boton("Cancelar"); 
    private Boton agregar =new Boton("Agregar");
    private Boton eliminar =new Boton("Eliminar");
    private Boton cotizar =new Boton("Cotizar");
    private Boton verCotizacion =new Boton("Cotizaciones");
    
    private JLabel etiquetaBuscar = new JLabel("Buscar por: ");
    private JLabel etiquetaCliente = new JLabel("Cliente");
   
   
    private JLabel etiquetaTotal = new JLabel("Total: ");
    private JLabel etiquetaSubtotal = new JLabel("Subtotal: ");
    private JLabel etiquetaDescuento = new JLabel("Descuento: ");
   
    
    
    private JLabel campoSubTotal = new JLabel("");
    private JLabel campoTotal = new JLabel("");
    
    JComboBox comboBuscar= new JComboBox();
    JTextField campoBuscar = new JTextField();
    JTextField campoCliente = new JTextField();
    JTextField campoDescuento = new JTextField();
    
    private Object [][] registroProductos;
    private String[] columnasProductos;
    private Object [][] registroCotizacion;
    private String[] columnasCotizacion;
    private JTable productos = new JTable(new DefaultTableModel(registroProductos, columnasProductos));
    private JTable cotizacion = new JTable(new DefaultTableModel(registroCotizacion, columnasCotizacion));
    
    private JScrollPane scrollPaneProductos = new JScrollPane(productos);
    private JScrollPane scrollPaneCotizacion = new JScrollPane(cotizacion);
    
    String rfc_empleado;
    
    public Cotizacion(){
        setLayout(new BorderLayout(10, 10));
        //setTitle("XAQUIXE COTIZACION");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(700, 500);
        
        campoBuscar.setPreferredSize(new Dimension(100, 25));
        comboBuscar.setPreferredSize(new Dimension(100, 25));
       
        panelBuscar.add(etiquetaBuscar);
        panelBuscar.add(comboBuscar);
        panelBuscar.add(campoBuscar);
        panelBuscar.add(buscar);
        
        
        //productos.setPreferredSize(new Dimension(500,300));
        //cotizacion.setPreferredSize(new Dimension(500,300));
        
        
        panelTablas.add(scrollPaneProductos);
        panelTablas.add(scrollPaneCotizacion);
        
        panelDatos.add(agregar);
        panelDatos.add(new JLabel());
        panelDatos.add(new JLabel());
         panelDatos.add(new JLabel());
        panelDatos.add(etiquetaCliente);
        panelDatos.add(campoCliente);
        
        
        
        panelCuenta.add(etiquetaSubtotal);
        panelCuenta.add(campoSubTotal);
        panelCuenta.add(etiquetaDescuento);
        panelCuenta.add(campoDescuento);
        /*panelCuenta.add(etiquetaPaquete);
        panelCuenta.add(campoPaquete);*/
        
        panelCuenta.add(etiquetaTotal);
        panelCuenta.add(campoTotal);
        
        panelbotones.add(verCotizacion);
        panelbotones.add(new JLabel());
        panelbotones.add(new JLabel());
        panelbotones.add(new JLabel());
        panelbotones.add(eliminar);
        panelbotones.add(new JLabel());
        panelbotones.add(cotizar);
        panelbotones.add(cancelar);
        
        panelDerecho.add(panelDatos);
        panelDerecho.add(panelCuenta);
        panelDerecho.add(panelbotones);
        
        //add(panelBuscar,BorderLayout.NORTH);
        add(panelTablas,BorderLayout.CENTER);
        add(panelDerecho,BorderLayout.EAST);
       // setLocationRelativeTo(null);
        
        //pack();
        setBackground(new Color(118,118,118,150));
        
        buscar.setActionCommand("buscar");
        agregar.setActionCommand("agregar"); 
        eliminar.setActionCommand("eliminar");
        cancelar.setActionCommand("cancelar");
        cotizar .setActionCommand("cotizar");
        verCotizacion .setActionCommand("verCotizacion");
        
        productos.setName("producto");
        cotizacion.setName("cotizacion");
        
    }
    
    public void conectar(controlador.ControlCotizacion c){
        buscar.addActionListener(c);
        agregar.addActionListener(c);
        eliminar.addActionListener(c);
        cotizar.addActionListener(c);
        verCotizacion.addActionListener(c);
        cancelar.addActionListener(c);
        productos.addMouseListener(c);
        cotizacion.addMouseListener(c);
        campoDescuento.addKeyListener(c);
    }
    
    public void mensaje(String msj){
        JOptionPane.showMessageDialog(null,msj);
    }
    
     public void limpiarVenta(){
         cotizacion.setModel(new DefaultTableModel(registroCotizacion,columnasCotizacion));
     }
    
    public void setDatosProductos(DefaultTableModel dtm){
        productos.setModel(dtm);
    }
    
    public void setDatosCotizacion(DefaultTableModel dtm){
        cotizacion.setModel(dtm);
    }
    
    public JTable getDatosProductos(){
        return productos;
    }
    
     public JTable getDatosCotizacion(){
        return cotizacion;
    }
     
          
    public void setProductos(DefaultTableModel dtm){
        productos.setModel(dtm);
    }
    
    public void setCotizacion(DefaultTableModel dtm){
       cotizacion.setModel(dtm);
    }
        
    public JTable getProductos(){
        return productos;
    }
    
    public JTable getCoizacion(){
        return cotizacion;
    } 

    public String  getCampoSubTotal() {
        return campoSubTotal.getText();
    }

    public void setCampoSubTotal(String campoSubTotal) {
        this.campoSubTotal.setText(campoSubTotal);
    }

    public String  getCampoDescuento() {
        return campoDescuento.getText();
    }

    public void setCampoDescuento(String campoDescuento) {
        this.campoDescuento.setText(campoDescuento);
    }

    public String getCampoTotal() {
        return campoTotal.getText();
    }

    public void setCampoTotal(String campoTotal) {
        this.campoTotal.setText(campoTotal);
    }
     
     public void insertarTotal(int tupla, Object total){
        cotizacion.setValueAt(total, tupla, 4);
    }
    
     public void UpdateCantidad(int tupla,Object cantidad){
        this.cotizacion.setValueAt(cantidad, tupla,2);
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

    public String getCampoCliente() {
        return campoCliente.getText();
    }

    public void setCampoCliente(String campoCliente) {
        this.campoCliente.setText(campoCliente);
    }
    
     public DefaultTableModel getDtmCotizacion(){
        return (DefaultTableModel) cotizacion.getModel();
    }
    
    
    public void RunWindows(){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new Cotizacion().setVisible(true);
                
            }
        });
    }
    
    
    
    
    public static void main(String[] args){
        Cotizacion c= new Cotizacion();
        c.RunWindows();
    }
    
}
