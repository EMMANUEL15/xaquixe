/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bernal Gonzalez Morachel Manic
 */


public class ModeloCotizacion {
    
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
    
   public DefaultTableModel Products(){
        DefaultTableModel dtm= new DefaultTableModel(registro,columnas);
        try{
        PreparedStatement pstm = Conexion.prepareStatement("select sku,item,medida,precio from xaquixe.producto order by sku");  
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
    
    
   public String insertCotizacion(Cotizacion v){
        String resulatdo = "";
        try {          
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.cotizacion(num_cotizacion ,rfc_e ,rfc_c ,fecha,descuento) values(?,?,?,?,?)");  
                    pstm.setString(1,v.getFolio_Cotizacion());
                    pstm.setString(2,v.getRfc_empleado());
                    pstm.setString(3,v.getRfc_cliente());
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
   
    
     public String insertDetalleCotizacion(Cotizacion v, DefaultTableModel dtm){
        String resulatdo = "";
        
        try {          
            int registros=dtm.getRowCount();
            PreparedStatement pstm = Conexion.prepareStatement("insert into xaquixe.detalle_cotizacion(num_cotizacion,sku ,cantidad ,precio, total) values(?,?,?,?,?)"); 
            
            for(int i=0;i<registros;i++){
                
                    pstm.setString(1,v.getFolio_Cotizacion());
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
    
    public String getFolio() {
        java.util.Date date = new java.util.Date();

        DateFormat hourdateFormat = new SimpleDateFormat("yyMMddHHmmss");
        System.out.println("Hora y fecha: " + hourdateFormat.format(date));
        
        String folio=hourdateFormat.format(date).toString();
         System.out.println(folio+"   manic");
         
         return folio;
    }
    
    
}
