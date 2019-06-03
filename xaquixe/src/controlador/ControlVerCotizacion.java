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
import javax.swing.JTable;

/**
 *
 * @author Bernal GOnzalez Morachel Manic
 */
public class ControlVerCotizacion implements ActionListener,MouseListener{
    
    private vista.VistaCotizacion vista;
    private modelo.ModeloVerCotizacion modelo;
    int tuplaCotizacion;
    
    String rfc_cliente;
    String rfc_empleado;
    float total;
    float subtotal;
    int descuento;
    
    
    
   public ControlVerCotizacion(vista.VistaCotizacion view, modelo.ModeloVerCotizacion modelo){
        this.vista=view;
        this.modelo=modelo;
        tuplaCotizacion=-1;
        vista.setCotizacion(modelo.Cotizacion());
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
       String comando  = e.getActionCommand();
       
      
       switch (comando) {
            
            case "buscar":
                
                String str=vista.getCampoBuscar();
                if(str.isEmpty()){
                    vista.mensaje("Campo Buscar vacio");
                    vista.setCotizacion(modelo.Cotizacion());
                }              
                else vista.setCotizacion(modelo.searchCotizacion(str));
                
                
                break;
                
            case "salir":
                
                vista.setVisible(false);
                break;
    
    
    }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
        
        String nombre=e.getComponent().getName();
        if(nombre=="cotizacion"){
            this.tuplaCotizacion=vista.getCotizacion().rowAtPoint(e.getPoint());
            
            if(tuplaCotizacion >= 0 ){
             String folio=String.valueOf(vista.getCotizacion().getValueAt(tuplaCotizacion,0));
             vista.setDetalleCotizacion(modelo.detalleCotizacion(folio));
             getCampos();
             calcularSubtotal();
             CalcularTotal();
            }else{  vista.mensaje("¡Tupla vacia!"); }
        }
        
        
           
    
    }
    
    
    
    public void getCampos(){
        rfc_empleado=String.valueOf(vista.getCotizacion().getValueAt(tuplaCotizacion,1));
        rfc_cliente=String.valueOf(vista.getCotizacion().getValueAt(tuplaCotizacion,2));
        descuento=Integer.valueOf(String.valueOf(vista.getCotizacion().getValueAt(tuplaCotizacion,4)));
        vista.setCampoCliente(rfc_cliente);
        vista.setCampoDescuento(String.valueOf(descuento));
    }
    
    
    public void calcularSubtotal(){
        subtotal=0;
        
        JTable tabVenta=vista.getDetalleCotizacion();
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
