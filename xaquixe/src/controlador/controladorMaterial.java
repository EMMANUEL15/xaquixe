package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * @author Emanuel Lopez
 */
public class controladorMaterial implements ActionListener,MouseListener{
    private vista.panel_material vista;
    private modelo.ModeloMaterial modelo;
    
    private vista.panel_autorizacion vistAuto;
    private modelo.ModeloAutorizacion modelAuto;
    private  int tuplaSelecionada;
    /**
     * contructor de controlador de materiales
     * @param view- panel que contiene los tabla, campos de texto y botones para los materiales
     * @param model- contien metodos para insertar, eliminar, actualizar y buscar materiales en la base de datos 
     */
    public controladorMaterial(vista.panel_material view ,modelo.ModeloMaterial model){
        this.vista   = view;
	this.modelo = model;
        this.vista.datos(modelo.Materiales());
        this.tuplaSelecionada = -1;
    }
    /**
     * controlador de autorizacion
     * @param viewA- panel que contiene los tabla de proveedores autorizados
     * @param modelA- contien metodos para dar autorizacion a proveedores en la base de datos 
     */
    public void controladorAutorizacion(vista.panel_autorizacion viewA, modelo.ModeloAutorizacion modelA){
        this.vistAuto = viewA;
        this.modelAuto = modelA;
    }
    /**
     * controla lo eventos de la vista hacia el modelo
     * @param arg0- recive los eventos de los botones del panel Materiales
     */
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        modelo.Material m = new modelo.Material();
        
        switch (comando) {
            case "BUSCAR":
                    vista.datos(modelo.searchMateriales(this.vista.getBuscar()));
            break;
            case "INSERTAR":
                try{
                if(validar()){
                int cantidad =Integer.parseInt(vista.getCampo3());
                m = new modelo.Material(vista.getCampo1(),vista.getCampo2(),cantidad);
                String r = modelo.insertMaterial(m);
		if (r.equals("")){
                    this.vista.datos(modelo.Materiales());
                    limpiar();
                }else
                    this.vista.Mensaje(r);
                }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
 
            case "BORRAR":
                    if(tuplaSelecionada >= 0 ){
                        if(vista.confirmacion(tuplaSelecionada)==true){
                            String r = modelo.deleteMaterial(this.vista.getCampo1());
                            if(r.equals("")){
                                this.vista.datos(modelo.Materiales());
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
                        int cantidad =Integer.parseInt(vista.getCampo3());
                        m = new modelo.Material(vista.getCampo1(),vista.getCampo2(),cantidad);
                        String r = modelo.updateMaterial(m, (String) vista.getTable().getValueAt(tuplaSelecionada,0));
                        if(r.equals("")){
                           this.vista.datos(modelo.Materiales());
                        }else
                        this.vista.Mensaje(r);
                    }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
            case "NUEVO":
		limpiar();
            break;
            case "UPPROVEDOR":
                if(tuplaSelecionada >= 0 ){
                    vistAuto.setId_Material(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
                    vistAuto.datosAutorizados(modelAuto.searchAutorizacion((String) vista.getTable().getValueAt(tuplaSelecionada,0)));
                    vistAuto.setLocationRelativeTo(null);
                    vistAuto.setVisible(true);
                }else{this.vista.Mensaje("No ha selecionado ninguna tupla");  }
            break;
            case "BUSCAR_PROVEEDOR":
                    
            break;
            default:
                
            break;
        }
    }
    private boolean validar(){
        boolean band = true;
        if(this.vista.getCampo1().equals("")){
            vista.Mensaje("ID_MATERIAL esta vacio.");
            band = false;
        }else if(this.vista.getCampo2().equals("")){
            vista.Mensaje("NOMBRE esta vacio.");
            band = false;
        }else if(this.vista.getCampo3().equals("")){
            vista.Mensaje("CANTIDAD esta vacio.");
            band = false;
        }else{
            try{Integer.parseInt(this.vista.getCampo3());}catch(Exception e){
                vista.Mensaje("Digite un numero entero en el campo Cantidad.");
                band = false;
            }
        }
        return band;
    }
    /**
     * limpia los campos de texto del panel materiales
     */
    private void limpiar(){
                this.vista.setCampo1("");
                this.vista.setCampo2("");
                this.vista.setCampo3("");
    }
    /**
     * controla lo eventos del maouse
     * @param me- recive los eventos del mouse sobre la tabla del panel meteriales
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        this.tuplaSelecionada = vista.getTable().rowAtPoint(me.getPoint());
        if(tuplaSelecionada >= 0 ){
            this.vista.setCampo1(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
            this.vista.setCampo2(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,1)));
            this.vista.setCampo3(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,2)));
            this.vista.setDatosCombo(this.modelo.ProveedoresAutorizados(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0))));
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
