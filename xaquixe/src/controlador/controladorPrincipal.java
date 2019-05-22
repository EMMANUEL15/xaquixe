package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Emanuel Lopez
 */
public class controladorPrincipal implements ActionListener{
    private vista.VentanaPrincipal vista;
    private vista.panel_producto vista_producto;
    private vista.panel_proveedor vista_proveedor;
    private vista.panel_material vista_material;
    
    public controladorPrincipal(vista.VentanaPrincipal view){
        this.vista   = view;
    }
    public void cargarPaneles(vista.panel_producto producto, vista.panel_proveedor proveedor,vista.panel_material material){
        this.vista_producto = producto;
        this.vista_proveedor = proveedor;
        this.vista_material = material;
    }
    
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
        
        switch (comando) {
            case "COTIZACION":
                System.out.println("contizacion");
            break;
            case "MATERIALES":
                this.vista.alternarPanel(vista_material);
            break;
            case "EMPLEADOS":
                System.out.println("empleados");
            break;
            case "PRODUCTOS":
                this.vista.alternarPanel(vista_producto);
            break;
            case "POVEEDORES":
                this.vista.alternarPanel(vista_proveedor);
            break;
            case "VENTAS":
                System.out.println("ventas");
            break;
            default:
            break;
        }
    }
}