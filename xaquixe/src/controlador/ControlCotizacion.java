/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
 * @author Bernal Gonzalez Morachel Manic
 */
public class ControlCotizacion implements ActionListener,MouseListener,KeyListener{
    
    vista.Cotizacion vista= new vista.Cotizacion();
    modelo.ModeloCotizacion  modelo= new modelo.ModeloCotizacion();
    
    int tuplaProducto;
    int tuplaCotizacion;
    private float subtotal;
    private float total;
    String folio_cotizacion;
    
    modelo.Cotizacion cotizacionObjeto;
    
    private Object [][] registroCotizacion;
    private String[] columnasCotizacion={"sku","item","cantidad","precio","Total"};
    DefaultTableModel dtm= new DefaultTableModel(registroCotizacion, columnasCotizacion);
    
    vista.VistaCotizacion vistaVerCotizacion = new vista.VistaCotizacion();
    modelo.ModeloVerCotizacion modeloVerCotizacion = new modelo.ModeloVerCotizacion();
    controlador.ControlVerCotizacion controladorVistaCotizacion;
    public void verCotizacion() {
       
        Conexion c= new Conexion("xaquixe");
        modeloVerCotizacion.conectar(c.getConexion());
        controladorVistaCotizacion = new controlador.ControlVerCotizacion(vistaVerCotizacion, modeloVerCotizacion);
        vistaVerCotizacion.conectar(controladorVistaCotizacion);

    }
    
    
    
    
    
    public ControlCotizacion(vista.Cotizacion view, modelo.ModeloCotizacion model){
        this.vista=view;
        this.modelo=model;
        vista.setProductos(modelo.Products());
        this.tuplaProducto=-1;
        this.tuplaCotizacion=-1;
        verCotizacion();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando  = e.getActionCommand();
        Object[] datoCotizacion = new Object[5];
      
       switch (comando) {
            
            case "agregar":
            if(tuplaProducto >= 0 ){
                                      
                int cantidad =Integer.valueOf(JOptionPane.showInputDialog(null,"Cantidad a Cotizar"));
                
                datoCotizacion[0]=vista.getProductos().getValueAt(tuplaProducto,0);
                datoCotizacion[1]=vista.getProductos().getValueAt(tuplaProducto,1);
                datoCotizacion[2]=cantidad;
                datoCotizacion[3]=vista.getProductos().getValueAt(tuplaProducto,3);
                               
                                    
                int comprobacion=comprobarCotizacion(String.valueOf(datoCotizacion[0]));
                
                if(comprobacion==-1){
                
                    dtm.addRow(datoCotizacion);
                    vista.setCotizacion(dtm);
                    calcularTotalVenta();
                    calcularSubtotal();
                    CalcularTotal();
                    
                
                }else{
                    
                    int  can= Integer.valueOf(String.valueOf(vista.getCoizacion().getValueAt(comprobacion,2)));
                    can=can+cantidad;
                    vista.UpdateCantidad(comprobacion,can);
                    calcularTotalVenta();
                    calcularSubtotal();
                    CalcularTotal();
                                     
                }
                
            
            
            } else{vista.mensaje("no se haleccionado un producto");}    
            break;
            
            case "eliminar":
                if(tuplaCotizacion>=0){
                    
                    int cantidad=vista.mensajeEliminar();
                    
                    if(cantidad==-1){
                        eliminarVenta(tuplaCotizacion);
                        int cantEliminar=Integer.valueOf(String.valueOf(vista.getCoizacion().getValueAt(tuplaCotizacion,2)));
                        calcularTotalVenta();
                        calcularSubtotal();
                        CalcularTotal();
                    }else{  
                        UpdateCantidadCotizacion(tuplaCotizacion, cantidad);
                        calcularTotalVenta();
                        calcularSubtotal();
                        CalcularTotal();
                    }
                    
                    
                }else{ vista.mensaje("no se haleccionado un Producto");}    
            break;  
                
            case "cancelar":
                    vista.setProductos(modelo.Products());
                    vista.setCampoDescuento("");
                    vista.setCampoSubTotal("");
                    vista.setCampoTotal("");
                    vista.limpiarVenta();        
            break;      
            
            case "cotizar":
                String rfc="";
                
                               
                if(comprobarCampos()){
                    
                    if(confirmarVenta()){
                    
                    folio_cotizacion=modelo.getFolio();
                    
                    cotizacionObjeto= new modelo.Cotizacion(folio_cotizacion,vista.getRfc_empleado(),vista.getCampoCliente(),Integer.valueOf(String.valueOf(vista.getCampoDescuento())));
                    modelo.insertCotizacion(cotizacionObjeto);
                    
                    modelo.insertDetalleCotizacion(cotizacionObjeto,vista.getDtmCotizacion());
                    //modelo.updateProducto(vista.getDtmVenta());
                    vista.setProductos(modelo.Products());
                    vista.setCampoCliente("");
                    vista.setCampoDescuento("");
                    vista.setCampoSubTotal("");
                    vista.setCampoTotal("");
                    vista.limpiarVenta();
                    }
                    
                }else{vista.mensaje("los campos deben estar rellenado correctamente");}
                
                
            break;  
            case "verCotizacion":  
                vistaVerCotizacion.setVisible(true);
            break;    
       }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      String nombre=e.getComponent().getName();
        if(nombre=="producto"){
            this.tuplaProducto=vista.getProductos().rowAtPoint(e.getPoint());
            if(tuplaProducto >= 0 ){
                
                
            }else{  vista.mensaje("¡Tupla vacia!"); }
        }
        
        if(nombre=="cotizacion"){
            this.tuplaCotizacion=vista.getCoizacion().rowAtPoint(e.getPoint());
            if(tuplaCotizacion >= 0 ){
                
                
            }else{  vista.mensaje("¡Tupla vacia!");}
        }   
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {       }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {  }
    
    public int comprobarCotizacion(String sku){
        JTable ventaAux= vista.getCoizacion();
        int registros=ventaAux.getRowCount();
        for(int i=0;i<registros;i++){
            
            if(String.valueOf(ventaAux.getValueAt(i,0))==sku)
                return i;
        }
        return -1;
    }
    
    /**
     * calculael total de cada uno delas tuplas de la tabla venta, multiplicando cantidad*precio
     */
    public void calcularTotalVenta(){
        JTable tablaAux=vista.getCoizacion();
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
    
     public void calcularSubtotal(){
        subtotal=0;
        int numeroTplas=vista.getCoizacion().getRowCount();
        JTable tabVenta=vista.getCoizacion();
        for(int i=0;i<numeroTplas;i++){
            subtotal+=Float.valueOf(String.valueOf(tabVenta.getValueAt(i,4)));
        }
        vista.setCampoSubTotal(String.valueOf(subtotal));
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
    
    public void eliminarVenta(int tupla){
        JTable ventas=vista.getCoizacion();
        DefaultTableModel dtm=(DefaultTableModel) ventas.getModel();
        dtm.removeRow(tupla);
        vista.setCotizacion(dtm);
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
    
    public void UpdateCantidadCotizacion(int tupla, int cant){
        JTable venta = vista.getCoizacion();
        int cantidad = Integer.valueOf(String.valueOf(venta.getValueAt(tupla,2)));
        vista.UpdateCantidad(tupla,(cantidad-cant));
    }
    
    public boolean comprobarCampos(){
        int registroVenta=vista.getCoizacion().getRowCount();
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
            vista.mensaje("NO se angregado poductos para Cotizar");
            return false;
        }
        
        if(vista.getCampoCliente().isEmpty()){
            vista.setCampoCliente("xaquixe");
        }
                
        return true;
    }
    
    
    public boolean confirmarVenta(){
        int respuesta=JOptionPane.showConfirmDialog(vista,"Registar Cotizacion");
        if(respuesta==0) return true;
        else return false;
           }

    @Override
    public void keyTyped(KeyEvent e) {          }

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
