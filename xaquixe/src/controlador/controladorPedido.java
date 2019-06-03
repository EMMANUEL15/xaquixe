package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author Emanuel Lopez
 */
public class controladorPedido implements ActionListener,MouseListener{
    private vista.panel_pedido vista;
    private modelo.ModeloPedido modelo;
    private  int tuplaSelecionada;
    private  int tuplaSelecionada2;
    /**
     * contructor de controlador de Compra
     * @param view- panel que contiene los tabla, campos de texto y botones para los Compra
     * @param model- contien metodos para insertar, eliminar, actualizar y buscar Compra en base de datos 
     */
    public controladorPedido(vista.panel_pedido view ,modelo.ModeloPedido model,String p){
        this.vista   = view;    
	this.modelo = model;
        this.vista.setTextPersonal(p);
        this.tuplaSelecionada  = -1;
        this.tuplaSelecionada2 = -1;
    }
    /**
     * contructor de controlador de Compra
     * @param view- panel que contiene los tabla, campos de texto y botones para los Compra
     * @param model- contien metodos para insertar, eliminar, actualizar y buscar Compra en base de datos 
     */
    private void proveedor(String rfc_e){
        this.vista.setTextProveedor("22");
    }
    private void Datos(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
        this.vista.setTextFecha(dateFormat.format(date));
        this.vista.setTextFolio("2654");
    }
    /**
     * controla lo eventos de la vista hacia el modelo
     * @param arg0- recive los eventos de los botones del panel Compraes
     */
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        switch (comando) {
            case "BUSCAR_MATERIAL":
                    vista.datosMaterial(modelo.searchMateriales(this.vista.getBuscar()));
            break;
            case "AGREGAR":
                if(tuplaSelecionada2 >= 0 ){
                    String id     =String.valueOf(vista.getTableMaterial().getValueAt(tuplaSelecionada2,0));
                    String nombre =String.valueOf(vista.getTableMaterial().getValueAt(tuplaSelecionada2,1));
                    int cantidad  = 1;
                    vista.getModelo().addRow(new Object[]{id,nombre,cantidad});
                    limpiar();
                }else{
                    this.vista.Mensaje("No ha selecionado ninguna tupla");
                }
            break;
            case "ELIMINAR":
                vista.getModelo().removeRow(tuplaSelecionada);
                limpiar();
            break;
            case "PEDIDO":
                try{
                for (int i = 0; i < vista.getTablePedido().getRowCount(); i++) {
                    String folio =String.valueOf(vista.getTablePedido().getValueAt(i, 0));
                    String id    = String.valueOf(vista.getTablePedido().getValueAt(i, 1));
                    int cantidad    = Integer.parseInt((String) vista.getTablePedido().getValueAt(i, 2));
                    
                    String r = modelo.AgregarMaterial(folio, id, i);
                    
                    if (r.equals("")){
                       vista.getModelo().removeRow(i);
                    }
                }
                }catch(Exception e){}
            break;
            default:                
            break;
        }
    }
    /**
     * limpia los campos de texto del panel Compraes
     */
    private void limpiar(){
                this.vista.setTextAgregar("");
                this.vista.setTextPedido("");
    }
    /**
     * controla lo eventos del maouse
     * @param me- recive los eventos del mouse sobre la tabla del panel Compra
     */
    @Override
    public void mouseClicked(MouseEvent me){
        this.tuplaSelecionada2 = vista.getTableMaterial().rowAtPoint(me.getPoint());
        this.tuplaSelecionada = vista.getTablePedido().rowAtPoint(me.getPoint());
        boolean band =true;
        
        if(tuplaSelecionada >= 0 ){
            String cadena = String.valueOf(vista.getTablePedido().getValueAt(tuplaSelecionada,0))+" "+
                            String.valueOf(vista.getTablePedido().getValueAt(tuplaSelecionada,1));
            this.vista.setTextPedido(cadena);
            band=false;
        }
        if(tuplaSelecionada2 >= 0){
            String cadena2 = String.valueOf(vista.getTableMaterial().getValueAt(tuplaSelecionada2,0))+" "+
                             String.valueOf(vista.getTableMaterial().getValueAt(tuplaSelecionada2,1));
            this.vista.setTextAgregar(cadena2);
            band=false;
        }
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
