/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xaquixe;

/**
 * @author Emanuel lopez
 */
public class Xaquixe {
    public static void main(String[] args) {
                
            //VISTAS
        vista.panel_producto vista_producto = new vista.panel_producto();
        vista.panel_proveedor vista_proveedor = new vista.panel_proveedor();
        vista.panel_material vista_material = new vista.panel_material();
        vista.panel_cliente vista_cliente = new vista.panel_cliente();
        vista.panel_empleado vista_empleado = new vista.panel_empleado();
        
        vista.panel_autorizacion vista_autorizacion = new vista.panel_autorizacion();
        
            //MODELO
        modelo.ModeloProducto modelo_producto = new modelo.ModeloProducto();
        modelo.ModeloProveedor modelo_proveedor = new modelo.ModeloProveedor();
        modelo.ModeloMaterial modelo_material = new modelo.ModeloMaterial();
        modelo.ModeloCliente modelo_cliente = new modelo.ModeloCliente();
        modelo.ModeloEmpleado modelo_empleado = new modelo.ModeloEmpleado();
        modelo.ModeloAutorizacion modelo_Autorizacion = new modelo.ModeloAutorizacion();
        
            //CONEXION A BASE DE DATOS
        Conexion c= new Conexion("xaquixe");
        modelo_producto.conectar(c.getConexion());
        modelo_proveedor.conectar(c.getConexion());
        modelo_material.conectar(c.getConexion());
        modelo_cliente.conectar(c.getConexion());
        modelo_empleado.conectar(c.getConexion());
        modelo_Autorizacion.conectar(c.getConexion());
        
            //CONEXION DE VISTA MODELO CONTROLADOR
        controlador.controladorCliente controlador_cliente  = new controlador.controladorCliente(vista_cliente, modelo_cliente);
        controlador.controladorEmpleado controlador_empleado  = new controlador.controladorEmpleado(vista_empleado, modelo_empleado);
        controlador.ControladorProducto controlador_producto  = new controlador.ControladorProducto(vista_producto, modelo_producto);
        controlador.controladorProveedor controlador_proveedor  = new controlador.controladorProveedor(vista_proveedor, modelo_proveedor);
        controlador.controladorMaterial controlador_material  = new controlador.controladorMaterial(vista_material, modelo_material);
                                                                    controlador_material.controladorAutorizacion(vista_autorizacion, modelo_Autorizacion);
        controlador.controladorAutorizacion controlador_autorizacion = new controlador.controladorAutorizacion(vista_autorizacion,modelo_Autorizacion);
        
            //VINCULAR VISTA Y CONTROLAR
        vista_cliente.conectaControlador(controlador_cliente);
        vista_empleado.conectaControlador(controlador_empleado);
        vista_producto.conectaControlador(controlador_producto);
        vista_proveedor.conectaControlador(controlador_proveedor); 
        vista_material.conectaControlador(controlador_material);
        vista_autorizacion.conectaControlador(controlador_autorizacion);
        
    vista.Vista_Principal V = new vista.Vista_Principal();
    controlador.controladorPrincipal CP = new controlador.controladorPrincipal(V);
    V.conectaControlador(CP);
    CP.cargarPaneles(vista_producto, vista_proveedor, vista_material,vista_cliente,vista_empleado);
    V.setVisible(true);  
    }
}
