package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * @author Emanuel Lopez
 */
public class controladorProveedor implements ActionListener,MouseListener{
    private vista.panel_proveedor vista;
    private modelo.ModeloProveedor modelo;
    private  int tuplaSelecionada;
    /**
     * contructor de controlador de proveedor
     * @param view- panel que contiene los tabla, campos de texto y botones para los proveedor
     * @param model- contien metodos para insertar, eliminar, actualizar y buscar proveedor en base de datos 
     */
    public controladorProveedor(vista.panel_proveedor view ,modelo.ModeloProveedor model){
        this.vista   = view;
	this.modelo = model;
        this.vista.datos(modelo.Proveedores());
        this.tuplaSelecionada = -1;
    }
    /**
     * controla lo eventos de la vista hacia el modelo
     * @param arg0- recive los eventos de los botones del panel proveedores
     */
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        modelo.Proveedor p = new modelo.Proveedor();
        
        switch (comando) {
            case "BUSCAR":
                    vista.datos(modelo.searchProveedores(this.vista.getBuscar()));
            break;
            case "INSERTAR":
                try{
                if(validar()){
                p = new modelo.Proveedor(vista.getCampo1(),vista.getCampo2(),vista.getCampo3(),vista.getCampo4(),vista.getCampo5(),vista.getCampo6(),vista.getCampo7(),vista.getCampo8(),vista.getCampo9());
                String r = modelo.insertProveedor(p);
		if (r.equals("")){
                    this.vista.datos(modelo.Proveedores());
                    limpiar();
                }else
                    this.vista.Mensaje(r);
                }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
 
            case "BORRAR":
                    if(tuplaSelecionada >= 0 ){
                        if(vista.confirmacion(tuplaSelecionada)==true){
                            String r = modelo.deleteProveedor(this.vista.getCampo1());
                            if(r.equals("")){
                                this.vista.datos(modelo.Proveedores());
                                limpiar();
                                tuplaSelecionada = -1;
                            }else
                                this.vista.Mensaje(r);
                            }
                    }else{
                        this.vista.Mensaje("No ha selecionado ninguna tupla");
                    }
            break;
 
            case "MODIFICAR":
                try{
                    if(tuplaSelecionada >= 0 && validar()){
                        p = new modelo.Proveedor(vista.getCampo1(),vista.getCampo2(),vista.getCampo3(),vista.getCampo4(),vista.getCampo5(),vista.getCampo6(),vista.getCampo7(),vista.getCampo8(),vista.getCampo9());
                        String r = modelo.updateProveedor(p, (String) vista.getTable().getValueAt(tuplaSelecionada,0));
                        if(r.equals("")){
                           this.vista.datos(modelo.Proveedores());
                        }else
                        this.vista.Mensaje(r);
                    }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
            case "NUEVO":
		limpiar();
		break;
            default:
                
            break;
        }
    }
    private boolean validar(){
        boolean band = true;
        if(this.vista.getCampo1().equals("")){
            vista.Mensaje("RFC esta vacio.");
            band = false;
        }else if(this.vista.getCampo2().equals("")){
            vista.Mensaje("NOMBRE esta vacio.");
            band = false;
        }else if(this.vista.getCampo3().equals("")){
            vista.Mensaje("RAZON SOCIAL esta vacio.");
            band = false;
        }else if(this.vista.getCampo4().equals("")){
            vista.Mensaje("CALLE esta vacio.");
            band = false;
        }else if(this.vista.getCampo5().equals("")){
            vista.Mensaje("NUMERO esta vacio.");
            band = false;
        }else if(this.vista.getCampo6().equals("")){
            vista.Mensaje("COLONIA esta vacio.");
            band = false;
        }else if(this.vista.getCampo7().equals("")){
            vista.Mensaje("MUNICIPIO esta vacio.");
            band = false;
        }else if(this.vista.getCampo8().equals("")){
            vista.Mensaje("ENTIDAD esta vacio.");
            band = false;
        }else if(this.vista.getCampo9().equals("")){
            vista.Mensaje("CODIGO POSTAL esta vacio.");
            band = false;
        }
        return band;
    }
    /**
     * limpia los campos de texto del panel proveedores
     */
    private void limpiar(){
                this.vista.setCampo1("");
                this.vista.setCampo2("");
                this.vista.setCampo3("");
                this.vista.setCampo4("");
                this.vista.setCampo5("");
                this.vista.setCampo6("");
                this.vista.setCampo7("");
                this.vista.setCampo8("");
                this.vista.setCampo9("");
    }
    /**
     * controla lo eventos del maouse
     * @param me- recive los eventos del mouse sobre la tabla del panel proveedores
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        this.tuplaSelecionada = vista.getTable().rowAtPoint(me.getPoint());
        if(tuplaSelecionada >= 0 ){
            this.vista.setCampo1(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
            this.vista.setCampo2(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,1)));
            this.vista.setCampo3(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,2)));
            this.vista.setCampo4(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,3)));
            this.vista.setCampo5(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,4)));
            this.vista.setCampo6(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,5)));
            this.vista.setCampo7(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,6)));
            this.vista.setCampo8(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,7)));
            this.vista.setCampo9(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,8)));
            this.vista.setDatosCombo(this.modelo.TelefonoProveedores(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0))),this.modelo.CorreoProveedores(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0))));
        }else
            vista.Mensaje("¡Tupla vacia!");
    }
    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
}
