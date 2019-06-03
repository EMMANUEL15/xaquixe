/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import xaquixe.Conexion;

/**
 *
 * @author Morachel Manic Bernal Gonzalez
 */
public class ControladorVenta implements ActionListener, MouseListener,KeyListener{
    private vista.Venta vista;
    private modelo.ModeloVenta modelo;
    private int tuplaProducto;
    private int tuplaVenta;
    private float subtotal;
    private float total;
    modelo.Venta ventaObjecto;
    modelo.DetalleVenta detalleVenta;
    
    String folio_Venta;
    
    
    Object[] datoVenta= new Object[4];
    private Object [][] registroVentas;
    private String[] columnasVentas={"sku","item","cantidad","precio","Total"};
    DefaultTableModel dtm= new DefaultTableModel(registroVentas, columnasVentas);
    
    
    vista.VerVenta vistaVerVenta = new vista.VerVenta();
    modelo.ModeloVerVenta modeloVerVenta = new modelo.ModeloVerVenta();
    controlador.ControlVerVenta controladorVistaVenta;
    public void verVenta() {

        Conexion c = new Conexion("xaquixe");
        modeloVerVenta.conectar(c.getConexion());
        controladorVistaVenta = new controlador.ControlVerVenta(vistaVerVenta, modeloVerVenta);
        vistaVerVenta.conectar(controladorVistaVenta);
    }
    
    public ControladorVenta(vista.Venta view, modelo.ModeloVenta model){
        this.vista=view;
        this.modelo=model;
        vista.setProductos(modelo.Products());
        this.tuplaProducto=-1;
        this.tuplaVenta=-1;
        verVenta();
    }
    
    public void calcularSubtotal(){
        subtotal=0;
        int numeroTplas=vista.getVenta().getRowCount();
        JTable tabVenta=vista.getVenta();
        for(int i=0;i<numeroTplas;i++){
            subtotal+=Float.valueOf(String.valueOf(tabVenta.getValueAt(i,4)));
        }
        vista.setCampoSubtotal(String.valueOf(subtotal));
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
           
        
       String comando  = e.getActionCommand();
       
      
       switch (comando) {
            
            case "agregar":
            if(tuplaProducto >= 0 ){
             
            if(compobarExitencia(tuplaProducto)){
                
                int cantidad =Integer.valueOf(JOptionPane.showInputDialog(null,"Cantidad De Compra"));
                
                datoVenta[0]=vista.getProductos().getValueAt(tuplaProducto,0);
                datoVenta[1]=vista.getProductos().getValueAt(tuplaProducto,1);
                datoVenta[2]=cantidad;
                datoVenta[3]=vista.getProductos().getValueAt(tuplaProducto,4);
                
                
                //comprueba que laexixtencia de los productos sea mayor o igual al que se desea vender
                if(Integer.valueOf(String.valueOf(vista.getProductos().getValueAt(tuplaProducto,3)))>=cantidad){
                    
                int comprobacion=comprobarVenta(String.valueOf(datoVenta[0]));
                
                if(comprobacion==-1){
                
                    dtm.addRow(datoVenta);
                    vista.setVenta(dtm);
                    calcularTotalVenta();
                    calcularSubtotal();
                    CalcularTotal();
                    restarProductos(tuplaProducto, cantidad);
                
                }else{
                    
                    int  can= Integer.valueOf(String.valueOf(vista.getVenta().getValueAt(comprobacion,2)));
                    can=can+cantidad;
                    vista.UpdateCantidad(comprobacion,can);
                    calcularTotalVenta();
                    calcularSubtotal();
                    CalcularTotal();
                    restarProductos(tuplaProducto, cantidad);
                    
                }
                }else{vista.mensaje("Cantidad de productos no disponible");}
            }else{vista.mensaje("Exitencia del producto 0");}
            
            } else{vista.mensaje("no se haleccionado un producto");}    
            break;
            
            case "eliminar":
                if(tuplaVenta>=0){
                    
                    int cantidad=vista.mensajeEliminar();
                    
                    if(cantidad==-1){
                        eliminarVenta(tuplaVenta);
                        int cantEliminar=Integer.valueOf(String.valueOf(vista.getVenta().getValueAt(tuplaVenta,2)));
                        int tuplaPro=buscarProducto(String.valueOf(vista.getVenta().getValueAt(tuplaVenta, 0)));
                        sumarProductos(tuplaPro,cantEliminar);
                        calcularTotalVenta();
                        calcularSubtotal();
                        CalcularTotal();
                    }else{  
                        UpdateCantidadVenta(tuplaVenta, cantidad);
                        int tuplaPro=buscarProducto(String.valueOf(vista.getVenta().getValueAt(tuplaVenta, 0)));
                        sumarProductos(tuplaPro,cantidad);
                        calcularTotalVenta();
                        calcularSubtotal();
                        CalcularTotal();
                    }
                    
                    
                }else{ vista.mensaje("no se haleccionado un Producto");}    
            break;  
                
            case "cancelar":
                    vista.setProductos(modelo.Products());
                    vista.setCampoCliente("");
                    vista.setCampoDescuento("");
                    vista.setCampoSubtotal("");
                    vista.setCampoTotal("");
                    vista.limpiarVenta();        
            break;      
            
            case "vender":
                String rfc="";
                
                               
                if(comprobarCampos()){
                    
                      if(confirmarVenta()){
                    
                    folio_Venta=modelo.getFolio();
                    
                    ventaObjecto= new modelo.Venta(folio_Venta,vista.getRfc_empleado(),vista.getCampoCliente(),Integer.valueOf(String.valueOf(vista.getCampoDescuento())));
                    modelo.insertVenta(ventaObjecto);
                    modelo.insertDetalleVenta(ventaObjecto,vista.getDtmVenta());
                    modelo.updateProducto(vista.getDtmVenta());
                    vista.setProductos(modelo.Products());
                    vista.setCampoCliente("");
                    vista.setCampoDescuento("");
                    vista.setCampoSubtotal("");
                    vista.setCampoTotal("");
                    vista.limpiarVenta();
                    }
                    
                }else{vista.mensaje("los campos deben estar rellenado correctamente");}
                
                
            break;  
                
            case "verVenta":  
                vistaVerVenta.setVisible(true);
            break;   
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String nombre=e.getComponent().getName();
        if(nombre=="producto"){
            this.tuplaProducto=vista.getProductos().rowAtPoint(e.getPoint());
            if(tuplaProducto >= 0 ){
                this.vista.SetImagen(this.modelo.searchImage(String.valueOf(vista.getProductos().getValueAt(tuplaProducto,0))));
                
            }else{  vista.mensaje("¡Tupla vacia!"); }
        }
        
        if(nombre=="venta"){
            this.tuplaVenta=vista.getVenta().rowAtPoint(e.getPoint());
            if(tuplaVenta >= 0 ){
                
                
            }else{  vista.mensaje("¡Tupla vacia!");}
        }
    }

    /**
     * calculael total de cada uno delas tuplas de la tabla venta, multiplicando cantidad*precio
     */
    public void calcularTotalVenta(){
        JTable tablaAux=vista.getVenta();
        float total =0;
        float cantidad=0;
        float precio=0;
        
       int  registros =tablaAux.getRowCount();
       
       for(int i=0; i<registros;i++){
          cantidad=Float.valueOf(String.valueOf(tablaAux.getValueAt(i,2)));
          precio=Float.valueOf(String.valueOf(tablaAux.getValueAt(i,3)));
          total=precio*cantidad;
          vista.insertarTotal(i,total);
            total =0;
            cantidad=0;
            precio=0;
       }
        
        
    }
    
    /**
     * comprueba si el producto ya se ha aagreado antes
     * @param sku ; codido del producto que se va a comprovar si ya se agrego con aterioridad
     */
    public int comprobarVenta(String sku){
        JTable ventaAux= vista.getVenta();
        int registros=ventaAux.getRowCount();
        for(int i=0;i<registros;i++){
            
            if(String.valueOf(ventaAux.getValueAt(i,0))==sku)
                return i;
        }
        return -1;
    }
    
    public void restarProductos(int tupla, int num){
        JTable producto=vista.getProductos();
        int cantidad=Integer.valueOf(String.valueOf(producto.getValueAt(tupla,3)));
        cantidad=cantidad-num;
        vista.UpdateCantidadProducto(tupla, cantidad);
        
    }
    
    public void sumarProductos(int tupla, int num){
        JTable producto=vista.getProductos();
        int cantidad=Integer.valueOf(String.valueOf(producto.getValueAt(tupla,3)));
        cantidad=cantidad+num;
        vista.UpdateCantidadProducto(tupla, cantidad);
        
    }
    
    
    public boolean compobarExitencia(int tupla){
        JTable producto=vista.getProductos();
        int cantidad=Integer.valueOf(String.valueOf(producto.getValueAt(tupla,3)));
        if(cantidad>=1){
         return true;
        }else
        return false;
    }
    
    
    public void CalcularTotal(){
       String descuento=vista.getCampoDescuento();
       if(descuento.isEmpty()){
       total=subtotal;
       }else {
        float porcentaje=Float.valueOf(descuento);
        float desc=subtotal*(porcentaje/100);
        total=subtotal-desc;
       }
       vista.setCampoTotal(String.valueOf(total));
    }
    
    public void UpdateCantidadVenta(int tupla, int cant){
        JTable venta = vista.getVenta();
        int cantidad = Integer.valueOf(String.valueOf(venta.getValueAt(tupla,2)));
        vista.UpdateCantidad(tupla,(cantidad-cant));
    }
    
    public void eliminarVenta(int tupla){
        JTable ventas=vista.getVenta();
        DefaultTableModel dtm=(DefaultTableModel) ventas.getModel();
        dtm.removeRow(tupla);
        vista.setVenta(dtm);
    }
    
    public int buscarProducto(String sku){
        JTable tabla=vista.getProductos();
        int registro=tabla.getRowCount();
        
        for(int i=0;i<registro;i++){
            if(String.valueOf(tabla.getValueAt(i,0))==sku){
                return i;
            }
        }
        return -1;
    }
    
    public boolean comprobarCampos(){
        int registroVenta=vista.getVenta().getRowCount();
        String cliente=vista.getCampoCliente();
        String desc=vista.getCampoDescuento();
        
        if(!desc.isEmpty()){
            if(desc.matches("[0-9]*")){
             int descuento=Integer.valueOf(desc);
            }else{
                vista.mensaje("EL descuento Debe ser Númerico");
                return false;
            }
        }
                        
        if(registroVenta==0){
            vista.mensaje("NO se angregado poductos para vender");
            return false;
        }
        
        if(vista.getCampoCliente().isEmpty()){
            vista.setCampoCliente("xaquixe");
        }
                
        return true;
    }
    
    
    public boolean confirmarVenta(){
        int respuesta=JOptionPane.showConfirmDialog(vista,"Registar venta");
        if(respuesta==0) return true;
        else return false;
           }
    
    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {   }

    @Override
    public void mouseEntered(MouseEvent e) {   }

    @Override
    public void mouseExited(MouseEvent e) {    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {    
        
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
        
        calcularSubtotal();
        CalcularTotal();
    
    }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {    }
    
}
