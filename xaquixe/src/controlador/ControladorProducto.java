package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
 * @author Emanuel lopez
 */
public class ControladorProducto implements ActionListener,MouseListener{
    private vista.panel_producto vista;
    private modelo.ModeloProducto modelo;
    private  int tuplaSelecionada;
    /**
     * contructor de controlador de productos
     * @param view- panel que contiene los tabla, campos de texto y botones
     * @param model- contien metodos para insertar, eliminar, actualizar y buscar producto base de datos,demas de las imagenes 
     */
    public ControladorProducto( vista.panel_producto view , modelo.ModeloProducto model){
        this.vista   = view;
	this.modelo = model;
        this.vista.datos(modelo.Products());
        this.tuplaSelecionada = -1;
    }
    /**
     * controla lo eventos de la vista hacia el modelo
     * @param arg0- recive los eventos de los botones del panel productos
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        modelo.Producto p = new modelo.Producto();
        
        switch (comando) {
            case "BUSCAR":
                    vista.datos(modelo.searchProduct(this.vista.getBuscar()));
            break;
            case "INSERTAR":
                try{
                double precio =Double.parseDouble(vista.getCampo5());
                p = new modelo.Producto(vista.getCampo1(),vista.getCampo2(),vista.getCampo3(),vista.getCampo4(),precio);
                String r = modelo.insertProducto(p);
		if (r.equals("")){
                    this.vista.datos(modelo.Products());
                    limpiar();
                }else
                    this.vista.Mensaje(r);
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
 
            case "BORRAR":
                    if(tuplaSelecionada >= 0 ){
                        if(vista.confirmacion(tuplaSelecionada)==true){
                            this.modelo.deleteImageProducto(this.vista.getCampo1());
                            String r = modelo.deleteProducto(this.vista.getCampo1());
                            if(r.equals("")){
                                this.vista.datos(modelo.Products());
                                this.vista.emiminar("imagenes\\"+this.vista.getCampo1()+this.vista.getCampo2()+".JPG");
                                tuplaSelecionada = -1;
                                limpiar();
                            }else
                                this.vista.Mensaje(r);
                            }
                    }else{
                        this.vista.Mensaje("No ha selecionado ninguna tupla");
                    }
            break;
 
            case "MODIFICAR":
                try{
                    if(tuplaSelecionada >= 0 ){
                        double precio2 =Double.parseDouble(vista.getCampo5());
                        p = new modelo.Producto(vista.getCampo1(),vista.getCampo2(),vista.getCampo3(),vista.getCampo4(),precio2);
                        String r = modelo.updateProducto(p,String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
                        if(r.equals("")){
                           this.vista.datos(modelo.Products());
                        }else
                        this.vista.Mensaje(r);
                    }else{this.vista.Mensaje("No ha selecionado ninguna tupla");  }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
            case "CAMBIAR":
                try{
                    if(tuplaSelecionada >= 0 ){
                        String i = this.modelo.searchImage(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0)));
                        String destino = "imagenes\\"+this.vista.getCampo1()+this.vista.getCampo2()+".JPG";
                        if(vista.moverimagen(destino)){
                            if(i.equals(this.modelo.imaDefaul())){
                                this.modelo.insertImageProducto(vista.getCampo1(),destino);
                            }else{
                                this.modelo.updateImageProducto(vista.getCampo1(),destino,vista.getCampo1());
                            }
                            this.vista.SetImagen(destino);
                        }
                    }else{this.vista.Mensaje("No ha selecionado ninguna tupla");  }
                }catch(Exception e){vista.Mensaje(String.valueOf(e.getMessage()));}
            break;
            case "NUEVO":
		limpiar();
            default:
                
            break;
        }
    } 
    /**
     * limpia los campos de texto del panel productos
     */
    private void limpiar(){
                this.vista.setCampo1("");
                this.vista.setCampo2("");
                this.vista.setCampo3("");
                this.vista.setCampo4("");
                this.vista.setCampo5("");
    }
    /**
     * controla lo eventos del maouse
     * @param me- recive los eventos del mouse sobre la tabla del panel productos
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
            this.vista.SetImagen(this.modelo.searchImage(String.valueOf(vista.getTable().getValueAt(tuplaSelecionada,0))));
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