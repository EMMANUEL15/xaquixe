package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 * @author Emanuel Lopez
 * * * METODOS * * *
 * conectar
 * Insertar producto
 * Eliminar Producto
 * Actualizar priducto
 * Listar productos
 * Buscar producto
 * Buscar Imagen
 * Insertar Imangen
 * Eliminar Producto
 * Actualizar Producto
 */
public class ModeloProducto {
    private Connection  Conexion;
    private Object [][] registro;
    private String[] columnas;
    private String imagen = "imagenes/NoFound.png";
    /**
     * conexion de la base de datos
     * @param con- recibe una connection de la base de datos
     */
    public void conectar(Connection con){
        this.Conexion = con;
    }
     /**
     * agrega una nueva tupla en la base de datos
     * @param p- recibe un objecto de tipo producto
     */
    public String insertProducto(Producto p){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.producto(sku,item,medida,cantidad,precio) values(?,?,?,?,?)");  
                    pstm.setString(1,p.getSku());
                    pstm.setString(2,p.getItem());
                    pstm.setString(3,p.getMedida());
                    pstm.setInt(4,p.getCantidad());
                    pstm.setDouble(5,p.getPrecio());
            pstm.execute();
            pstm.close();  
            
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
        }
        return resulatdo;
    }
    /**
     * elimina una nueva tupla en la base de datos
     * @param sku- recibe un cadena para identificar la tupla
     */
    public String deleteProducto(String sku){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.producto where sku = ?"); 
                pstm.setString(1,sku);                   
                pstm.execute();
                pstm.close(); 
            }catch(SQLException e){
                resulatdo = String.valueOf(e.getMessage());
            }
        return resulatdo;
    }
    /**
     * Actualiza un tupla en la base de datos
     * @param p- recibe un objecto de tipo producto
     * @param s recibe un cadena para identificar la tupla
     */
    public String updateProducto(Producto p, String s){
        String resulatdo = "";
        try {            
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.producto set sku = ?,item = ?,medida = ?,cantidad = ?,precio = ?  where sku = ?"); 
            pstm.setString(1,p.getSku());
            pstm.setString(2,p.getItem());
            pstm.setString(3,p.getMedida());
            pstm.setInt(4,p.getCantidad());
            pstm.setDouble(5,p.getPrecio());
            pstm.setString(6,s);
            pstm.executeUpdate();
            pstm.close();  
         }catch(SQLException e){
             resulatdo = String.valueOf(e.getMessage());
         }
        return resulatdo;
    }
    /**
     * consulta todo el contenido de la tabla
     */
    public DefaultTableModel Products(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.producto");  
        ResultSet resultado = pstm.executeQuery();
        ResultSetMetaData datos = pstm.getMetaData();
    
        int num_Colum= datos.getColumnCount();
    //COLUMNAS
        for (int i = 1; i <= num_Colum; i++ ) {
            dtm.addColumn(datos.getColumnName(i));
        }
    //REGISTROS
        while (resultado.next()) {
            Object [] nuevo =new Object[num_Colum];
                for (int i = 1; i <= num_Colum; i++) {
                    nuevo[i-1] = resultado.getString(i);
                }
                dtm.addRow(nuevo);
        }
            //acrualiza los datas de la tabla
    }catch(Exception e){}
        return dtm;
    }
    /**
     * busca los materiales
     * @param dato- recibe un cadena para buscar productos
     */
    public DefaultTableModel searchProduct(String dato){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
    try{
    PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.producto WHERE UPPER(sku) like ? or UPPER(item) like ? or UPPER(medida like ?");            
    pstm.setString(1,"%"+dato.toUpperCase()+"%");
    pstm.setString(2,"%"+dato.toUpperCase()+"%");
    pstm.setString(3,"%"+dato.toUpperCase()+"%");
    ResultSet resultado = pstm.executeQuery();
    ResultSetMetaData datos = pstm.getMetaData();
    //COLUMNA
    int num_Colum= datos.getColumnCount();
    for (int i = 1; i <= num_Colum; i++ ) {
        dtm.addColumn(datos.getColumnName(i));
    }
    //REGISTROS
    while (resultado.next()) {
        Object [] nuevo =new Object[num_Colum];
                for (int i = 1; i <= num_Colum; i++) {
                    nuevo[i-1] = resultado.getString(i);
                }
                dtm.addRow(nuevo);
        }
    }catch(Exception e){ }
     return dtm;
    }
     /**
     * busca de imagen
     * @param dato- recibe un cadena para buscar imagen correspondiente
     */
    public String searchImage(String dato){
    String Res = imagen;
    try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.imagen_producto where sku = ? limit 1");            
        pstm.setString(1,dato);
        ResultSet resultado = pstm.executeQuery();
        ResultSetMetaData datos = pstm.getMetaData();
    //resultado
        resultado.next();
        Res = resultado.getString(2);
        
    }catch(Exception e){}
     return Res;
    }
    /**
     * inserta imagen
     * @param sku- recibe un cadena para identificar la imagen
     * @param nombre- recibe un cadena para el nombre de la imagen
     */
    public String insertImageProducto(String sku,String nombre){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.imagen_producto(sku,imagen) values(?,?)");  
                pstm.setString(1,sku);
                pstm.setString(2,nombre);
                pstm.execute();
                pstm.close();  
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
        }
        return resulatdo;
    }
    /**
     * elimina imagen
     * @param sku- recibe un cadena para identificar la imagen
     */
    public String deleteImageProducto(String sku){
        String resulatdo = "";
        try {                
                PreparedStatement pstm = Conexion.prepareStatement("DELETE FROM xaquixe.imagen_producto where sku = ?"); 
                pstm.setString(1,sku);                   
                pstm.execute();
                pstm.close(); 
            }catch(SQLException e){
                resulatdo = String.valueOf(e.getMessage());
            }
        return resulatdo;
    }
    /**
     * Actualiza los datos de la imagen
     * @param sku- recibe un cadena para identificar la imagen
     * @param nombre- recibe un cadena para el nombre de la imagen
     */
    public String updateImageProducto(String sku,String nombre, String s){
        String resulatdo = "";
        try {            
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.imagen_producto set sku = ?,imagen = ?  where sku = ?"); 
            pstm.setString(1,sku);
            pstm.setString(2,nombre);
            pstm.setString(3,s);
            pstm.executeUpdate();
            pstm.close();  
         }catch(SQLException e){
             resulatdo = String.valueOf(e.getMessage());
         }
        return resulatdo;
    }
    /**
     * regresa una imagen por defecto
     */
    public String imaDefaul(){
        return imagen;
    }
}