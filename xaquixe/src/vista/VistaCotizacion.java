/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Componente.Boton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Bernal Gonzalez Morachel Manic;
 */
public class VistaCotizacion extends JFrame{
    
    
     private Object [][] registroVenta;
    private String[] columnasVenta;
    
    private Object [][] registroDetalleVenta;
    private String[] columnasDetalleVenta;
    
    private JTable cotizacion = new JTable(new DefaultTableModel(registroVenta, columnasVenta));
    private JTable detalleCotizacion = new JTable(new DefaultTableModel(registroDetalleVenta,columnasDetalleVenta));
    private JScrollPane scrollCotizacion = new JScrollPane(cotizacion);
    private JScrollPane scrollDetalleCotizacion = new JScrollPane(detalleCotizacion);
    
    private JLabel etiquetaBuscar = new JLabel("Buscar: ");
    private JLabel etiquetaCliente = new JLabel("Cliente: ");
    private JLabel etiquetaTotal = new JLabel("Total: ");
    private JLabel etiquetaSubtotal = new JLabel("Subtotal: ");
    private JLabel etiquetaDescuento = new JLabel("Descuento: ");
    
    private JLabel campoCliente = new JLabel("");
    private JLabel campoTotal = new JLabel("");
    private JLabel campoSubtotal = new JLabel("");
    private JLabel campoDescuento = new JLabel("");
    
    JComboBox comboBuscar= new JComboBox();
    JTextField campoBuscar = new JTextField();
    
    
    private Boton buscar =new Boton("Buscar");
    private Boton salir =new Boton("Salir");
    
    private JPanel  panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel  paneldatos = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel panelTablas = new JPanel(new GridLayout(0, 1, 0, 10));
    
    
    public VistaCotizacion(){
        setTitle("XAQUIXE COTIZACION");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panelBuscar.setBorder(new TitledBorder("Buscar"));
        comboBuscar.setPreferredSize(new Dimension(120,25));
        campoBuscar.setPreferredSize(new Dimension(120,25));
        panelBuscar.add(etiquetaBuscar);
       // panelBuscar.add(comboBuscar);
        panelBuscar.add(campoBuscar);
        panelBuscar.add(buscar);
        
        JTableHeader th; 
        th = detalleCotizacion.getTableHeader();
        
        JTableHeader th2; 
        th2 = cotizacion.getTableHeader();
        
        scrollDetalleCotizacion.setPreferredSize(new Dimension(500,200));
        scrollCotizacion.setPreferredSize(new Dimension(500, 200));
        
        panelTablas.add(scrollCotizacion);
        panelTablas.add(scrollDetalleCotizacion);
        
        campoCliente.setPreferredSize(new Dimension(100, 25));
        campoDescuento.setPreferredSize(new Dimension(50, 25));
        campoSubtotal.setPreferredSize(new Dimension(80, 25));
        campoTotal.setPreferredSize(new Dimension(80, 25));
        
        
        paneldatos.add(etiquetaCliente);
        paneldatos.add(campoCliente);
        paneldatos.add(etiquetaSubtotal);
        paneldatos.add(campoSubtotal);
        paneldatos.add(etiquetaDescuento);
        paneldatos.add(campoDescuento);
        paneldatos.add(etiquetaTotal);
        paneldatos.add(campoTotal);
        paneldatos.add(new JLabel());
        paneldatos.add(salir);
        
       add(panelBuscar,BorderLayout.NORTH) ;
       add(panelTablas,BorderLayout.CENTER);
       add(paneldatos,BorderLayout.SOUTH);
       
       cotizacion.setName("cotizacion");
       buscar.setActionCommand("buscar");
       salir.setActionCommand("salir");
       pack();
    }
    
    
    public void conectar(controlador.ControlVerCotizacion c){
        buscar.addActionListener(c);
        salir.addActionListener(c);
        cotizacion.addMouseListener(c);
     }

    public String getCampoBuscar() {
        return campoBuscar.getText();
    }

    public void setCampoBuscar(String campoBuscar) {
        this.campoBuscar.setText(campoBuscar);
    }
     
    public void mensaje(String msj){
        JOptionPane.showMessageDialog(null,msj);
    }
    
    public void RunWindows(){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new VistaCotizacion().setVisible(true);
            }
        });
    }
    
          
    public static void main(String[] args){
        VistaCotizacion v= new VistaCotizacion();
        v.RunWindows();
    }

    public String getCampoCliente() {
        return campoCliente.getText();
    }

    public void setCampoCliente(String campoCliente) {
        this.campoCliente.setText(campoCliente);
    }

    public String getCampoTotal() {
        return campoTotal.getText();
    }

    public void setCampoTotal(String campoTotal) {
        this.campoTotal.setText(campoTotal);
    }

    public String getCampoSubtotal() {
        return campoSubtotal.getText();
    }

    public void setCampoSubtotal(String campoSubtotal) {
        this.campoSubtotal.setText(campoSubtotal);
    }

    public String getCampoDescuento() {
        return campoDescuento.getText();
    }

    public void setCampoDescuento(String campoDescuento) {
        this.campoDescuento.setText(campoDescuento);
    }
    
    public JTable getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(DefaultTableModel dtm) {
        this.cotizacion.setModel(dtm);
    }

    public JTable getDetalleCotizacion() {
        return detalleCotizacion;
    }

    public void setDetalleCotizacion(DefaultTableModel dtm) {
        this.detalleCotizacion.setModel(dtm);
    }
}
