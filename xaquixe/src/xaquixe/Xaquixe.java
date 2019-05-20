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
        
            //MODELO
        modelo.ModeloProducto modelo_producto = new modelo.ModeloProducto();
        modelo.ModeloProveedor modelo_proveedor = new modelo.ModeloProveedor();
        modelo.ModeloMaterial modelo_material = new modelo.ModeloMaterial();
        
            //CONEXION A BASE DE DATOS
        Conexion c= new Conexion("xaquixe");
        modelo_producto.conectar(c.getConexion());
        modelo_proveedor.conectar(c.getConexion());
        modelo_material.conectar(c.getConexion());
        
            //CONEXION DE VISTA MODELO CONTROLADOR
        controlador.ControladorProducto controlador_producto  = new controlador.ControladorProducto(vista_producto, modelo_producto);
        controlador.controladorProveedor controlador_proveedor  = new controlador.controladorProveedor(vista_proveedor, modelo_proveedor);
        controlador.controladorMaterial controlador_material  = new controlador.controladorMaterial(vista_material, modelo_material);
        
            //VINCULAR VISTA Y CONTROLAR
        vista_producto.conectaControlador(controlador_producto);
        vista_proveedor.conectaControlador(controlador_proveedor); 
        vista_material.conectaControlador(controlador_material); 
    }
}
