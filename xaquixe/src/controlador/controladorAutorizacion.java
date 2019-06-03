package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * @author Emanuel Lopez
 */
public class controladorAutorizacion implements ActionListener,MouseListener{
    private vista.panel_autorizacion vista;
    private modelo.ModeloAutorizacion modelo;
    private  int tuplaSelecionada;
    private  int tuplaSelecionada2;
    /**
     * contructor de controlador de proveedor
     * @param view- panel que contiene los tabla, campos de texto y botones para los proveedor
     * @param model- contien metodos para insertar, eliminar, actualizar y buscar proveedor en base de datos 
     */
    public controladorAutorizacion(vista.panel_autorizacion view ,modelo.ModeloAutorizacion model){
        this.vista   = view;    
	this.modelo = model;
        this.tuplaSelecionada = -1;
        this.tuplaSelecionada2 = -1;
    }
    /**
     * muestra los proveedores autorizados para surtir material
     * @param dato- id_materiala
     */
    public void datosAutorizacion(String dato){
        this.vista.datosAutorizados(modelo.searchAutorizacion(dato));
    }
    /**
     * controla lo eventos de la vista hacia el modelo
     * @param arg0- recive los eventos de los botones del panel proveedores
     */
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        
        switch (comando) {
            case "BUSCAR_PROVEEDOR":
                    vista.datosProveedor(modelo.searchProveedor(this.vista.getBuscar()));
            break;
            case "AUTORIZAR":
              try{
                String r = modelo.insertAutorizacion(vista.getId_Material(), String.valueOf(vista.getTableProveedor().getValueAt(tuplaSelecionada,0)));
		if (r.equals("")){
                   this.vista.datosAutorizados(modelo.searchAutorizacion(vista.getId_Material()));
                    limpiar();
                }else
                    this.vista.Mensaje(r);
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
                
                buscarProvedores();
            break;
            case "ELIMINAR":
                    if(tuplaSelecionada2 >= 0 ){
                        if(vista.confirmacion2(tuplaSelecionada2)==true){
                            String r = modelo.deleteAutorizcion(vista.getId_Material(), String.valueOf(vista.getTableAutorizacion().getValueAt(tuplaSelecionada2,0)));
                            if(r.equals("")){
                                this.vista.datosAutorizados(modelo.searchAutorizacion(vista.getId_Material()));
                                limpiar();
                                tuplaSelecionada2 = -1;
                            }else
                                this.vista.Mensaje(r);
                            }
                    }else{
                        this.vista.Mensaje("No ha selecionado ninguna tupla");
                    }
                buscarProvedores();
            break;
            default:                
            break;
        }
    }
    /**
     * limpia los campos de texto del panel proveedores
     */
    private void limpiar(){
                this.vista.setTextSelecioando("");
    }
    public void buscarProvedores(){
        vista.datosAutorizados(modelo.searchAutorizacion(this.vista.getId_Material()));
    }
    /**
     * controla lo eventos del maouse
     * @param me- recive los eventos del mouse sobre la tabla del panel proveedores
     */
    @Override
    public void mouseClicked(MouseEvent me){
        this.tuplaSelecionada = vista.getTableProveedor().rowAtPoint(me.getPoint());
        this.tuplaSelecionada2 = vista.getTableAutorizacion().rowAtPoint(me.getPoint());
        boolean band =true;
        if(tuplaSelecionada >= 0 ){
            String cadena = String.valueOf(vista.getTableProveedor().getValueAt(tuplaSelecionada,0))+" "+
                            String.valueOf(vista.getTableProveedor().getValueAt(tuplaSelecionada,1));
            this.vista.setTextSelecioando(cadena);
            band=false;
        }
        if(tuplaSelecionada2 >= 0){
            String cadena2 = String.valueOf(vista.getTableAutorizacion().getValueAt(tuplaSelecionada2,0))+" "+
                             String.valueOf(vista.getTableAutorizacion().getValueAt(tuplaSelecionada2,1));
            this.vista.setTextAutorizado(cadena2);
            band=false;
        }
        if(band)
            vista.Mensaje("¡Tupla vacia!");
    }
    @Override
    public void mousePressed(MouseEvent me){}
    @Override
    public void mouseReleased(MouseEvent me){}
    @Override
    public void mouseEntered(MouseEvent me){}
    @Override
    public void mouseExited(MouseEvent me){}
}