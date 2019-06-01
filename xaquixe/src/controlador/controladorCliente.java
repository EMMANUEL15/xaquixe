package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * @author Emanuel Lopez
 */
public class controladorCliente implements ActionListener,MouseListener{
    private vista.panel_cliente vista;
    private modelo.ModeloCliente modelo;
    private  int tuplaSelecionada;
    /**
     * contructor de controlador de Client
     * @param view- panel que contiene los tabla, campos de texto y botones para los Client
     * @param model- contien metodos para Cinsertar, eliminar, actualizar y buscar Client en base de datos 
     */
    public controladorCliente(vista.panel_cliente view ,modelo.ModeloCliente model){
        this.vista   = view;
	this.modelo = model;
        this.vista.datos(modelo.Clientes());
        this.tuplaSelecionada = -1;
    }
    /**
     * controla lo eventos de la vista hacia el modelo
     * @param arg0- recive los eventos de los botones del panel Clientes
     */
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        modelo.Cliente c = new modelo.Cliente();
        
        switch (comando) {
            case "BUSCAR":
                    vista.datos(modelo.searchClientes(this.vista.getBuscar()));
            break;
            case "INSERTAR":
                try{
                if(validar()){
                c = new modelo.Cliente(vista.getCampo1(),vista.getCampo2(),vista.getCampo3(),vista.getCampo4());
                String r = modelo.insertCliente(c);
		if (r.equals("")){
                    this.vista.datos(modelo.Clientes());
                    limpiar();
                }else
                    this.vista.Mensaje(r);
                }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
 
            case "BORRAR":
                    if(tuplaSelecionada >= 0){
                        if(vista.confirmacion(tuplaSelecionada)==true){
                            String r = modelo.deleteCliente(this.vista.getCampo1());
                            if(r.equals("")){
                                this.vista.datos(modelo.Clientes());
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
                        c = new modelo.Cliente(vista.getCampo1(),vista.getCampo2(),vista.getCampo3(),vista.getCampo4());
                        String r = modelo.updateCliente(c, (String) vista.getTable().getValueAt(tuplaSelecionada,0));
                        if(r.equals("")){
                           this.vista.datos(modelo.Clientes());
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
    /**
     * Valida que todos los campos no esten vacios y con datos correctos
     * @return un verdadero si esta correctamente rellenados
     */
    private boolean validar(){
        boolean band = true;
        if(this.vista.getCampo1().equals("")){
            vista.Mensaje("RFC esta vacio.");
            band = false;
        }else if(this.vista.getCampo2().equals("")){
            vista.Mensaje("NOMBRE esta vacio.");
            band = false;
        }else if(this.vista.getCampo3().equals("")){
            vista.Mensaje("APELLIDO PATERNO esta vacio.");
            band = false;
        }else if(this.vista.getCampo4().equals("")){
            vista.Mensaje("APELLIDO MATERNO esta vacio.");
            band = false;
        }
        return band;
    }
    /**
     * limpia los campos de texto del panel Clientes
     */
    private void limpiar(){
                this.vista.setCampo1("");
                this.vista.setCampo2("");
                this.vista.setCampo3("");
                this.vista.setCampo4("");
    }
    /**
     * controla lo eventos del maouse
     * @param me- recive los eventos del mouse sobre la tabla del panel Clientes
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        this.tuplaSelecionada = vista.getTable().rowAtPoint(me.getPoint());
        if(tuplaSelecionada >= 0 ){
            this.vista.setCampo1(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
            this.vista.setCampo2(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,1)));
            this.vista.setCampo3(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,2)));
            this.vista.setCampo4(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,3)));
            this.vista.setDatosCombo(this.modelo.TelefonoCliente(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0))),this.modelo.DireccionCliente(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0))));
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
