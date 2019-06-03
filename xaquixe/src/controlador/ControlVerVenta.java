/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.Stream;
import javax.swing.JTable;

/**
 *
 * @author Bernal Gonzalez Morachel Manic
 */
public class ControlVerVenta implements ActionListener,MouseListener{

    private vista.VerVenta vista;
    private modelo.ModeloVerVenta modelo;
    int tuplaVenta;
    
    String rfc_cliente;
    String rfc_empleado;
    float total;
    float subtotal;
    int descuento;
    
    
    public ControlVerVenta(vista.VerVenta view, modelo.ModeloVerVenta modelo){
        this.vista=view;
        this.modelo=modelo;
        tuplaVenta=-1;
        vista.setVenta(modelo.ventas());
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
       String comando  = e.getActionCommand();
       
      
       switch (comando) {
            
            case "buscar":
                
                String str=vista.getCampoBuscar();
                if(str.isEmpty()){
                    vista.mensaje("Campo Buscar vacio");
                    vista.setVenta(modelo.ventas());
                }              
                else vista.setVenta(modelo.searchventa(str));
                
                
                break;
                
            case "salir":
                
                vista.setVisible(false);
                break;
    
    
    }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
        
        String nombre=e.getComponent().getName();
        if(nombre=="venta"){
            this.tuplaVenta=vista.getVenta().rowAtPoint(e.getPoint());
            
            if(tuplaVenta >= 0 ){
             String folio=String.valueOf(vista.getVenta().getValueAt(tuplaVenta,0));
             vista.setDetalleVenta(modelo.detalleVenta(folio));
             getCampos();
             calcularSubtotal();
             CalcularTotal();
            }else{  vista.mensaje("¡Tupla vacia!"); }
        }
        
        
           
    
    }
    
    
    
    public void getCampos(){
        rfc_cliente=String.valueOf(vista.getVenta().getValueAt(tuplaVenta,1));
        rfc_empleado=String.valueOf(vista.getVenta().getValueAt(tuplaVenta,2));
        descuento=Integer.valueOf(String.valueOf(vista.getVenta().getValueAt(tuplaVenta,4)));
        vista.setCampoCliente(rfc_cliente);
        vista.setCampoDescuento(String.valueOf(descuento));
    }
    
    
    public void calcularSubtotal(){
        subtotal=0;
        
        JTable tabVenta=vista.getDetalleVenta();
        int numeroTplas=tabVenta.getRowCount();
        for(int i=0;i<numeroTplas;i++){
            subtotal+=Float.valueOf(String.valueOf(tabVenta.getValueAt(i,4)));
        }
        vista.setCampoSubtotal(String.valueOf(subtotal));
    }
    
     public void CalcularTotal(){
       String descuento=vista.getCampoDescuento();
       if(descuento.isEmpty()){
       total=subtotal;
       }else {
        float porcentaje=Float.valueOf(descuento);
        float desc=subtotal*(porcentaje/100);
        total=subtotal-Float.valueOf(descuento);
       }
       vista.setCampoTotal(String.valueOf(total));
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }
    
}
