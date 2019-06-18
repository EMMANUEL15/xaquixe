/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MORACHEL MANIC BERNAL GONZALEZ 
 */
public class ModeloVenta {
    
    private Connection  Conexion;
    
    private Object [][] registro;
    private String[] columnas;
     
    private String imagen = "imagenes/NoFound.png";
    private Date sqlDate;
    private Calendar currenttime = Calendar.getInstance();
    
    
    /**
     * conexion de la base de datos
     * @param con- recibe una connection de la base de datos
     */
    public void conectar(Connection con){
        this.Conexion = con;
    }
    
    
    /**
     * agrega una nueva venta a la base de datos
     * @param v- recibe el objeto venta 
     */
    public String insertVenta(Venta v){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.venta(folio_v ,rfc_c ,rfc_e ,fecha,descuento) values(?,?,?,?,?)");  
                    pstm.setString(1,v.getFolio());
                    pstm.setString(2,v.getRfc_cliente());
                    pstm.setString(3,v.getRfc_empledo());
                    sqlDate = new Date((currenttime.getTime()).getTime());
                    pstm.setDate(4,sqlDate);
                    pstm.setInt(5,v.getDescuento());
                   
            pstm.execute();
            pstm.close();  
            
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
            System.out.println(e.getMessage());
        }
        return resulatdo;
    }
    
    public String insertDetalleVenta(Venta v, DefaultTableModel dtm){
        String resulatdo = "";
        
        try {          
            int registros=dtm.getRowCount();
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.detalle_venta(folio_v ,sku ,cantidad ,precio_v, total) values(?,?,?,?,?)"); 
            
            for(int i=0;i<registros;i++){
                
                    pstm.setString(1,v.getFolio());
                    pstm.setString(2,String.valueOf(dtm.getValueAt(i,0)));
                    pstm.setInt(3,Integer.valueOf(String.valueOf(dtm.getValueAt(i,2))));
                    pstm.setFloat(4,Float.valueOf(String.valueOf(dtm.getValueAt(i,3))));
                    pstm.setFloat(5,Float.valueOf(String.valueOf(dtm.getValueAt(i,4))));
                   
            pstm.execute();
            
            }
            pstm.close();  
            
        }catch(SQLException e){
            resulatdo =String.valueOf(e.getMessage());
            System.out.println(e.getMessage());
        }
        return resulatdo;
    }
    
    
    public DefaultTableModel Products(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select * from xaquixe.producto order by sku");  
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
     * 
     * @param dtm DefaultTableModel cons los datos de venta
     * @return 
     */
    public String updateProducto(DefaultTableModel dtm){
        String resulatdo = "";
        
        try {  
             int registros=dtm.getRowCount();
            PreparedStatement pstm = Conexion.prepareStatement("UPDATE xaquixe.producto set cantidad=cantidad- ?  where sku = ?");
            for(int i=0;i<registros;i++){
            
                pstm.setInt(1,Integer.valueOf(String.valueOf(dtm.getValueAt(i,2))));
                pstm.setString(2,String.valueOf(dtm.getValueAt(i,0)));
                pstm.executeUpdate();
            }
            pstm.close();  
         }catch(Exception e){
             resulatdo = String.valueOf(e.getMessage());
         }
        return resulatdo;
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
    
    public String getFolio() {
        java.util.Date date = new java.util.Date();

        DateFormat hourdateFormat = new SimpleDateFormat("yyMMddHHmmss");
        System.out.println("Hora y fecha: " + hourdateFormat.format(date));
        
        String folio=hourdateFormat.format(date).toString();
         System.out.println(folio+"   manic");
         
         return folio;
    }
}
