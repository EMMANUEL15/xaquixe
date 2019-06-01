package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Emanuel Lopez
 */
public class controladorPrincipal implements ActionListener{
    private vista.Vista_Principal vista;
    private vista.panel_producto vista_producto;
    private vista.panel_proveedor vista_proveedor;
    private vista.panel_material vista_material;
    private vista.panel_cliente vista_cliente;
    private vista.panel_empleado vista_empleado;
    
    public controladorPrincipal(vista.Vista_Principal view){
        this.vista   = view;
    }
    public void cargarPaneles(vista.panel_producto producto, vista.panel_proveedor proveedor,vista.panel_material material,vista.panel_cliente cliente,vista.panel_empleado empleado){
        this.vista_producto = producto;
        this.vista_proveedor = proveedor;
        this.vista_material = material;
        this.vista_cliente = cliente;
        this.vista_empleado = empleado;
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
                this.vista.alternarPanel(vista_empleado);
            break;
            case "PRODUCTOS":
                this.vista.alternarPanel(vista_producto);
            break;
            case "CLIENTES":
                this.vista.alternarPanel(vista_cliente);
            break;
            case "POVEEDORES":
                this.vista.alternarPanel(vista_proveedor);
            break;
            case "VENTAS":
                System.out.println("ventas");
            break;
            case "CERRAR":
                if(vista.confirmacion()){
                    System.exit(0);
                }
            break;
            default:
            break;
        }
    }
}