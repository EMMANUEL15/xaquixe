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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Bernal gonzalez Morachel Manic
 */
public class VerVenta extends JFrame{
    
    private Object [][] registroVenta;
    private String[] columnasVenta;
    
    private Object [][] registroDetalleVenta;
    private String[] columnasDetalleVenta;
    
    private JTable venta = new JTable(new DefaultTableModel(registroVenta, columnasVenta));
    private JTable detalleVenta = new JTable(new DefaultTableModel(registroDetalleVenta,columnasDetalleVenta));
    private JScrollPane scrollVenta = new JScrollPane(venta);
    private JScrollPane scrollDetalleVenta = new JScrollPane(detalleVenta);
    
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
    
    
    public VerVenta(){
        setTitle("XAQUIXE VENTA");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panelBuscar.setBorder(new TitledBorder("Buscar"));
        comboBuscar.setPreferredSize(new Dimension(120,25));
        campoBuscar.setPreferredSize(new Dimension(120,25));
        panelBuscar.add(etiquetaBuscar);
        //panelBuscar.add(comboBuscar);
        panelBuscar.add(campoBuscar);
        panelBuscar.add(buscar);
        
        JTableHeader th; 
        th = detalleVenta.getTableHeader();
        
        JTableHeader th2; 
        th2 = venta.getTableHeader();
        
        scrollDetalleVenta.setPreferredSize(new Dimension(500,200));
        scrollVenta.setPreferredSize(new Dimension(500, 200));
        
        panelTablas.add(scrollVenta);
        panelTablas.add(scrollDetalleVenta);
        
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
       
        venta.setName("venta");
        detalleVenta.setName("DetalleVenta");
       buscar.setActionCommand("buscar");
       salir.setActionCommand("salir");
       pack();
    }
    
    
     public void conectar(controlador.ControlVerVenta c){
        buscar.addActionListener(c);
        salir.addActionListener(c);
        venta.addMouseListener(c);
     }

    public String getCampoBuscar() {
        return campoBuscar.getText();
    }

    public void setCampoBuscar(String campoBuscar) {
        this.campoBuscar.setText(campoBuscar);
    }
     
    
     
     
    public void RunWindows(){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new VerVenta().setVisible(true);
            }
        });
    }
    
          
    public static void main(String[] args){
        VerVenta v= new VerVenta();
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

    public JTable getVenta() {
        return venta;
    }

    public void setVenta(DefaultTableModel dtm) {
        this.venta.setModel(dtm);
    }

    public JTable getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DefaultTableModel dtm) {
        this.detalleVenta.setModel(dtm);
    }
    
    public void mensaje(String msj){
        JOptionPane.showMessageDialog(null,msj);
    }
    
    
}
